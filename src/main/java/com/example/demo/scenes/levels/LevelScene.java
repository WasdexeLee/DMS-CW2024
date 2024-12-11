package com.example.demo.scenes.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.user.UserPlane;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.core.Game;
import com.example.demo.core.GameLoop;
import com.example.demo.scenes.GameScene;
import com.example.demo.scenes.levels.services.*;
import com.example.demo.scenes.levels.services.managers.*;
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

/**
 * Represents an abstract base class for level scenes in the game.
 * This class extends {@link GameScene} and provides common functionality for managing level-specific
 * game logic, including user input, actor management, collision detection, and game state updates.
 *
 * @author WasdexeLee (Lee Jia Zhe)
 */
public abstract class LevelScene extends GameScene {

    /** The initial health of the player. */
    private final int PLAYER_INITIAL_HEALTH;

    /** The scene to transition to when the player loses the game. */
    private final SceneType LOSE_SCENE;

    /** The scene to transition to when the player exits the game. */
    private final SceneType MENU_SCENE;

    /** The root node of the scene. */
    private final Group root;

    /** The pause menu UI component. */
    private final VBox pauseMenu;

    /** The pause button UI component. */
    private final Button pauseButton;

    /** The levelView component of the level, responsible for displaying level UI elements. */
    private LevelView levelView;

    /** The state component for the level, responsible for managing game state data. */
    private LevelState levelState;

    /** The manager for handling actor-related operations. */
    private ActorManager actorManager;

    /** The manager for handling collision-related operations. */
    private CollisionManager collisionManager;

    /** The manager for handling enemy-related operations. */
    private EnemyManager enemyManager;

    /** The manager for handling kill-related operations. */
    private KillManager killManager;

    /** The manager for handling projectile-related operations. */
    private ProjectileManager projectileManager;

    /** The user's plane. */
    private final UserPlane userUnit;

    /** The list of enemy units in the level. */
    private final List<ActiveActorDestructible> enemyUnits;

    /** The list of user projectiles in the level. */
    private final List<ActiveActorDestructible> userProjectiles;

    /** The list of enemy projectiles in the level. */
    private final List<ActiveActorDestructible> enemyProjectiles;

    /** A frame counter used for user burst fire calculations. */
    private short frameCounter;
   
    /**
     * Constructs a {@link LevelScene} with the specified background image, screen dimensions, and player health.
     * Initializes all necessary components including levelView, levelState, managers, pause menu and pause button.
     * Implemented Key Listeners to control the movement of the user plane. 
     * Key trigger logic and fire trigger logic are also implemented as it is the same across subclasses(Levels).
     *
     * @param backgroundImageName The path to the background image.
     * @param screenWidth         The width of the screen.
     * @param screenHeight        The height of the screen.
     * @param playerInitialHealth The initial health of the player.
     */
    public LevelScene(String backgroundImageName, double screenWidth, double screenHeight, int playerInitialHealth) {
        super(backgroundImageName, screenWidth, screenHeight);

        this.PLAYER_INITIAL_HEALTH = playerInitialHealth;
        this.LOSE_SCENE = SceneType.LOSESCENE;
        this.MENU_SCENE = SceneType.MENU;

        this.root = getRoot();
        this.pauseMenu = initPauseMenu();
        this.pauseButton = initPauseButton();
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
                if (kc == KeyCode.SPACE) {
                    userUnit.setIsFiring(true);
                    projectileManager.spawnProjectile(Arrays.asList(userUnit), userProjectiles, root);
                }
                if (kc == KeyCode.ESCAPE) {
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

        this.root.getChildren().addAll(userUnit, pauseButton);
        topNode.add(pauseButton);
        topNode.add(levelView.getHeartDisplay());
        topNode.add(pauseMenu);
    }

    /**
     * Updates the state of the level scene.
     * This method handles collision detection, actor updates, and game state checks.
     * Uses Managers(CollisionManager, ActorManager, EnemeyManager, ProjectileManager, KillManager) to update level and actors state.
     * Continuously update game state to ensure smooth and responsive gameplay.
     * Checks for collision of user projectile and enemy to consider kill count.
     * Checks for user and enemy/enemy projectile collision consider taking damage.
     * Checks for enemy penetrating user defense line consider taking damage.
     * Clearing out destroyed and unused projectiles and enemy planes.
     * Updating movement of enemy and projectiles.
     * Spawning in new enemy and enemy projectiles randomly.
     * Updating heart display for user through the levelView component previously initialized.
     */
    @Override
    public void update() {
        // Update kill count
        levelState.setCurrentNumberOfEnemies(enemyUnits.size());
        collisionManager.handleCollisions(userProjectiles, enemyUnits, EffectAudioType.KILL); // UserProjectile collide with EnemyUnits
        actorManager.removeDestroyedActors(enemyUnits, root);
        killManager.updateKillCount(levelState, levelView, enemyUnits, userUnit, userUnit.getHealth());

        // Handle actor collision
        collisionManager.handleCollisions(enemyProjectiles, Arrays.asList(userUnit), EffectAudioType.DAMAGE); // EnemyProjectile collide with UserUnit
        collisionManager.handleCollisions(Arrays.asList(userUnit), enemyUnits, EffectAudioType.DAMAGE); // UserUnit collide with EnemyUnits

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

    /**
     * Checks if the game is over based on the player's state and the level's logic.
     * Implements a skeleton method using abstract methods that must be implemented in the subclasses.
     * Ensure high reusability of code and low repetitive code.
     */
    protected void checkIfGameOver() {
        if (userUnit.getIsDestroyed()) {
            Game.getInstance(null).setStateEndGame();
            AudioManager.getInstance().fireEffectAudio(EffectAudioType.GAME_OVER);
            goToScene(LOSE_SCENE);
        } else if (userKillTargetLogic()) {
            AudioManager.getInstance().fireEffectAudio(EffectAudioType.TRANSITION);
            userKillTargetReachedAction();
        }
    }

    /**
     * Determines if the player has reached the kill target for the level.
     * Abstract method that must be implemented by subclass due to specific to subclass nature.
     *
     * @return {@code true} if the player has reached the kill target, {@code false} otherwise.
     */
    protected abstract boolean userKillTargetLogic();

    /**
     * Defines the action to take when the player reaches the kill target.
     * Abstract method that must be implemented by subclass due to specific to subclass nature.
     */
    protected abstract void userKillTargetReachedAction();

    /**
     * Spawns enemy units logic for the level.
     * Abstract method that must be implemented by subclass due to specific to subclass nature.
     */
    protected abstract void spawnEnemyUnits();

    /**
     * Gets the level view component.
     *
     * @return The {@link LevelView} instance.
     */
    protected LevelView getLevelView() {
        return levelView;
    }

    /**
     * Gets the level state component.
     *
     * @return The {@link LevelState} instance.
     */
    protected LevelState getLevelState() {
        return levelState;
    }

    /**
     * Gets the user's plane.
     *
     * @return The {@link UserPlane} instance.
     */
    protected UserPlane getUserUnit() {
        return userUnit;
    }

    /**
     * Gets the list of enemy units in the level.
     *
     * @return The list of {@link ActiveActorDestructible} enemy units.
     */
    protected List<ActiveActorDestructible> getEnemyUnits() {
        return enemyUnits;
    }

    /**
     * Initializes the pause menu UI component.
     * Creates Title and Buttons needed to create a Pause Menu that is user friendly 
     *
     * @return The {@link VBox} representing the pause menu.
     */
    private VBox initPauseMenu() {
        // Create the pause label as an ImageView
        ImageView title = new ImageView(new Image(getClass().getResource("/com/example/demo/images/pause/paused.png").toExternalForm()));
        title.setFitWidth(410);
        title.setPreserveRatio(true);

        // Buttons
        Button continueButton = createButton(247, 63, getClass().getResource("/com/example/demo/images/pause/continue.png").toExternalForm());
        Button exitButton = createButton(166, 63, getClass().getResource("/com/example/demo/images/pause/exit.png").toExternalForm());

        // Button Actions
        continueButton.setOnAction(e -> {
            resumeGame();
            AudioManager.getInstance().fireEffectAudio(EffectAudioType.CLICK);
        });
        exitButton.setOnAction(e -> {
            goToScene(MENU_SCENE);
            AudioManager.getInstance().fireEffectAudio(EffectAudioType.CLICK);
        });

        // Organize buttons in a VBox
        VBox buttonBox = new VBox(35, continueButton, exitButton); // 20px spacing between buttons
        buttonBox.setAlignment(Pos.CENTER);

        // Organize the entire layout in a VBox
        VBox mainLayout = new VBox(100, title, buttonBox); // 50px spacing between the image and buttons
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(50, 30, 100, 30));

        // Set a semi-transparent gray background
        mainLayout.setBackground(new Background(new BackgroundFill(Color.rgb(70, 70, 70, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));

        // Position the mainLayout at the center of the Group (or Scene)
        mainLayout.setLayoutX((getScreenWidth() - 470) / 2); // Center horizontally
        mainLayout.setLayoutY((getScreenHeight() - 618) / 2); // Center vertically

        return mainLayout;
    }

    /**
     * Shows the pause menu and pauses the game.
     * Called to display the pause menu above the screen giving user choice to rest.
     */
    private void showPauseMenu() {
        Game.getInstance(null).setStatePauseGame();
        root.getChildren().add(pauseMenu);
        AudioManager.getInstance().fireEffectAudio(EffectAudioType.PAUSE);

        userUnit.stopLeft();
        userUnit.stopRight();
        userUnit.stopUp();
        userUnit.stopDown();
        userUnit.setIsFiring(false);
    }

    /**
     * Resumes the game by resuming the game state.
     * Resumes also hides the pause menu from the user to give unobstructed view and good gameplay experience.
     */
    private void resumeGame() {
        Platform.runLater(() -> {
            root.getChildren().removeLast();
            root.requestLayout();
        });
        Game.getInstance(null).setStateResumeGame();
    }

    /**
     * Initializes the pause button UI component.
     * Implement on click pause actions of the particular button. 
     * Uses the inherited createButton() method from {@link GameScene} as mentioned before.
     *
     * @return The {@link Button} representing the pause button.
     */
    private Button initPauseButton() {
        Button pauseButton = createButton(60, 56, getClass().getResource("/com/example/demo/images/pause/pause_button.png").toExternalForm());

        pauseButton.setLayoutX(getScreenWidth() - 185);
        pauseButton.setLayoutY(25);
        pauseButton.setOnAction(e -> {
            if (Game.getInstance(null).getCurrentState() == State.RUNNING) showPauseMenu();
        });

        return pauseButton;
    }
}