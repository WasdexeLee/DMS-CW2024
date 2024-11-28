package com.example.demo.scenes.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.user.UserPlane;
import com.example.demo.core.Game;
import com.example.demo.scenes.GameScene;
import com.example.demo.scenes.levels.services.*;
import com.example.demo.scenes.levels.services.managers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class LevelScene extends GameScene {

    private final int PLAYER_INITIAL_HEALTH;

    private final Group root;
    private LevelView levelView;
    private LevelState levelState;
    private ActorManager actorManager;
    private CollisionManager collisionManager;
    private EnemyManager enemyManager;
    private KillManager killManager;
    private ProjectileManager projectileManager;

    private final UserPlane userUnit;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    public LevelScene(String backgroundImageName, double screenWidth, double screenHeight, int playerInitialHealth) {
        super(backgroundImageName, screenWidth, screenHeight);

        this.PLAYER_INITIAL_HEALTH = playerInitialHealth;

        this.root = getRoot();
        this.levelView = new LevelView(this.root, PLAYER_INITIAL_HEALTH);
        this.levelView.showHeartDisplay();
        this.levelState = LevelState.getInstance(screenWidth);
        this.levelState.setNumberOfKills(0);
        this.actorManager = ActorManager.getInstance();
        this.collisionManager = CollisionManager.getInstance();
        this.enemyManager = EnemyManager.getInstance();
        this.killManager = KillManager.getInstance();
        this.projectileManager = ProjectileManager.getInstance();

        this.userUnit = new UserPlane(PLAYER_INITIAL_HEALTH);
        this.root.getChildren().add(userUnit);
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();

        getBackground().setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP)
                    userUnit.moveUp();
                if (kc == KeyCode.DOWN)
                    userUnit.moveDown();
                if (kc == KeyCode.SPACE)
                    projectileManager.spawnProjectile(Arrays.asList(userUnit), userProjectiles, root);
            }
        });

        getBackground().setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP || kc == KeyCode.DOWN)
                    userUnit.stop();
            }
        });
    }

    @Override
    public void update() {
        // Update kill count
        // Handle for user projectile and enemy plane collision as kill
        levelState.setCurrentNumberOfEnemies(enemyUnits.size());
        collisionManager.handleCollisions(userProjectiles, enemyUnits); // UserProjectile collide with EnemyUnits 
        actorManager.removeDestroyedActors(enemyUnits, root);
        killManager.updateKillCount(levelState, levelView, enemyUnits, userUnit, userUnit.getHealth());

        // Handle actor collision
        collisionManager.handleCollisions(enemyProjectiles, Arrays.asList(userUnit)); // EnemyProjectile collide with UserUnit
        collisionManager.handleCollisions(Arrays.asList(userUnit), enemyUnits); // UserUnit collide with EnemyUnits
        collisionManager.handleCollisions(userProjectiles, enemyUnits); // UserProjectile collide with EnemyUnits

        // Handle for EnemyPlane and Projectile out of screen
        enemyManager.handleEnemyPenetration(userUnit, enemyUnits, levelState.getScreenWidth());
        projectileManager.handleProjectileOutOfScreen(userProjectiles, levelState.getScreenWidth());
        projectileManager.handleProjectileOutOfScreen(enemyProjectiles, levelState.getScreenWidth());

        // Call removal of all destroyed actors on screen
        actorManager.removeDestroyedActors(enemyUnits, root);
        actorManager.removeDestroyedActors(userProjectiles, root);
        actorManager.removeDestroyedActors(enemyProjectiles, root);

        // Then finally spawn in enemy and projectiles
        spawnEnemyUnits();
        projectileManager.spawnProjectile(enemyUnits, enemyProjectiles, root);

        // Update LevelView heart display
        levelView.removeHearts(userUnit.getHealth());

        // Update all actors
        actorManager.updateAllActors(Arrays.asList(Arrays.asList(userUnit), enemyUnits, userProjectiles, enemyProjectiles));

        // Check for game end
        checkIfGameOver();
    }

    protected void checkIfGameOver() {
        if (userUnit.getIsDestroyed()) {
            levelView.showGameOverImage();
            Game.getInstance(null).setStateEndGame();
        }
        else if (userKillTargetLogic()) {
            userKillTargetReachedAction();
        }
    };

    protected abstract boolean userKillTargetLogic();

    protected abstract void userKillTargetReachedAction();

    protected abstract void spawnEnemyUnits();

    protected LevelView getLevelView() {
        return levelView;
    }

    protected LevelState getLevelState() {
        return levelState;
    }

    protected UserPlane getUserUnit() {
        return userUnit;
    }

    protected List<ActiveActorDestructible> getEnemyUnits() {
        return enemyUnits;
    }
}