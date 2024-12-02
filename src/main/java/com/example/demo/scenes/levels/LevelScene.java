package com.example.demo.scenes.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.user.UserPlane;
import com.example.demo.core.Game;
import com.example.demo.core.GameLoop;
import com.example.demo.scenes.GameScene;
import com.example.demo.scenes.levels.services.*;
import com.example.demo.scenes.levels.services.managers.*;
import com.example.demo.utils.LoggerUtil;
import com.example.demo.utils.EnumUtil.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class LevelScene extends GameScene {

    private final int PLAYER_INITIAL_HEALTH;
    private final SceneType LOSE_SCENE;
    private final SceneType MENU_SCENE;

    private final Group root;
    private final VBox pauseMenu;
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

    private short frameCounter;

    public LevelScene(String backgroundImageName, double screenWidth, double screenHeight, int playerInitialHealth) {
        super(backgroundImageName, screenWidth, screenHeight);

        this.PLAYER_INITIAL_HEALTH = playerInitialHealth;
        this.LOSE_SCENE = SceneType.LOSESCENE;
        this.MENU_SCENE = SceneType.MENU;

        this.root = getRoot();
        this.pauseMenu = initPauseMenu();
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

        this.frameCounter = 0;

        getBackground().setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.LEFT || kc == KeyCode.A)
                    userUnit.moveLeft();
                if (kc == KeyCode.RIGHT || kc == KeyCode.D)
                    userUnit.moveRight();
                if (kc == KeyCode.UP || kc == KeyCode.W)
                    userUnit.moveUp();
                if (kc == KeyCode.DOWN || kc == KeyCode.S)
                    userUnit.moveDown();
                if (kc == KeyCode.SPACE){
                    userUnit.setIsFiring(true);
                    projectileManager.spawnProjectile(Arrays.asList(userUnit), userProjectiles, root);
                }
                if (kc == KeyCode.ESCAPE){
                    if (Game.getInstance(null).getCurrentState() == State.PAUSED)
                        resumeGame();
                    else if (Game.getInstance(null).getCurrentState() == State.RUNNING)
                        showPauseMenu();
                }
            }
        });

        getBackground().setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.LEFT || kc == KeyCode.A)
                    userUnit.stopLeft();
                if (kc == KeyCode.RIGHT || kc == KeyCode.D)
                    userUnit.stopRight();
                if (kc == KeyCode.UP || kc == KeyCode.W)
                    userUnit.stopUp();
                if (kc == KeyCode.DOWN || kc == KeyCode.S)
                    userUnit.stopDown();
                if (kc == KeyCode.SPACE)
                    userUnit.setIsFiring(false);
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

        frameCounter++;
        if (frameCounter == (int) GameLoop.getInstance(null).get_TARGET_FPS() / 10) {
            if (userUnit.getIsFiring() && frameCounter == (int) GameLoop.getInstance(null).get_TARGET_FPS() / 10) {
                projectileManager.spawnProjectile(Arrays.asList(userUnit), userProjectiles, root);
            }
            frameCounter = 0;
        }

        // Update LevelView heart display
        levelView.removeHearts(userUnit.getHealth());

        // Update all actors
        actorManager.updateAllActors(Arrays.asList(Arrays.asList(userUnit), enemyUnits, userProjectiles, enemyProjectiles));

        // Check for game end
        checkIfGameOver();
    }

    protected void checkIfGameOver() {
        if (userUnit.getIsDestroyed()) {
            Game.getInstance(null).setStateEndGame();
            goToScene(LOSE_SCENE);
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

    private VBox initPauseMenu() {
        // Create the pause label as an ImageView
        ImageView title = new ImageView(new Image(getClass().getResource("/com/example/demo/images/pause/paused.png").toExternalForm()));
        title.setFitWidth(410);
        title.setPreserveRatio(true);

        // Buttons
        Button continueButton = createButton(247, getClass().getResource("/com/example/demo/images/pause/continue.png").toExternalForm());
        Button exitButton = createButton(166, getClass().getResource("/com/example/demo/images/pause/exit.png").toExternalForm());

        // Button Actions
        continueButton.setOnAction(e -> resumeGame());
        exitButton.setOnAction(e -> goToScene(MENU_SCENE));

        // Organize buttons in a VBox
        VBox buttonBox = new VBox(35, continueButton, exitButton); // 20px spacing between buttons
        buttonBox.setAlignment(Pos.CENTER);

        // Organize the entire layout in a VBox
        VBox mainLayout = new VBox(100, title, buttonBox); // 50px spacing between the image and buttons
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(50, 30, 100, 30));

        // Set a semi-transparent gray background
        mainLayout.setBackground(new Background(new BackgroundFill(Color.rgb(70, 70, 70, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

        // // Position the mainLayout at the center of the Group (or Scene)
        mainLayout.setLayoutX((getScreenWidth() - 470) / 2); // Center horizontally
        mainLayout.setLayoutY((getScreenHeight() - 618) / 2); // Center vertically

        return mainLayout;
    }

    private void showPauseMenu() {
        Game.getInstance(null).setStatePauseGame();
        root.getChildren().add(pauseMenu);
    }

    private void resumeGame() {
        Platform.runLater(() -> {
            root.getChildren().removeLast();
            root.requestLayout();
        });
        Game.getInstance(null).setStateResumeGame();
    }

    private Button createButton(double buttonWidth, String imagePath) {
        Button button = new Button();

        button.setStyle(String.format(
            "-fx-background-color: transparent;" + 
            "-fx-background-image: url('%s');" +
            "-fx-background-size: cover;" + // Make the image cover the button area
            "-fx-background-position: center;" +
            "-fx-background-repeat: no-repeat;" 
            , imagePath)
        );
        button.setPrefWidth(buttonWidth);
        button.setPrefHeight(63);

        return button;
    }
}