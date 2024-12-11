package com.example.demo.scenes.services;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.example.demo.core.Game;
import com.example.demo.scenes.GameScene;
import com.example.demo.utils.EnumUtil.SceneType;

import javafx.stage.Stage;

/**
 * Manages the game scenes and transitions between them.
 * This class implements {@link PropertyChangeListener} to listen for scene change events.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class GameSceneManager implements PropertyChangeListener {

    /** The singleton instance of the GameSceneManager. */
    private static GameSceneManager instance;

    /** The game instance that this manager is associated with. */
    private final Game game;

    /** The JavaFX stage where the scenes are displayed. */
    private final Stage stage;

    /** The factory responsible for creating game scenes. */
    private final GameSceneFactory gameSceneFactory;

    /** The width of the screen. */
    private final int screenWidth;

    /** The height of the screen. */
    private final int screenHeight;

    /** The current game scene being displayed. */
    private GameScene currentGameScene;

    /**
     * Private constructor to enforce the singleton pattern.
     * Initializes the game scene manager with the specified game and stage.
     * 
     * @param game The game instance.
     * @param stage The JavaFX stage.
     */
    private GameSceneManager(Game game, Stage stage) {
        this.game = game;
        this.game.addGameStatePropChangeListener(this);
        this.stage = stage;
        this.gameSceneFactory = new GameSceneFactory();

        this.screenWidth = (int) Math.round(this.stage.getWidth());
        this.screenHeight = (int) Math.round(this.stage.getHeight());

        // Initialize with the MENU scene
        this.currentGameScene = this.gameSceneFactory.createScene(SceneType.MENU, this.screenWidth, this.screenHeight);
        this.currentGameScene.addPropChangeListener(this);

        this.stage.setScene(this.currentGameScene.getScene());
    }

    /**
     * Returns the singleton instance of the GameSceneManager.
     * 
     * @param game The game instance.
     * @param stage The JavaFX stage.
     * @return The singleton instance of the GameSceneManager.
     */
    public static GameSceneManager getInstance(Game game, Stage stage) {
        if (instance == null) {
            instance = new GameSceneManager(game, stage);
        }

        return instance;
    }

    /**
     * Updates the current game scene.
     */
    public void update() { 
        currentGameScene.update();
    }

    /**
     * Transitions to the specified scene.
     * Transitions based on events thrown by the currentGameScene.
     * Adding {@code this} instance to the currentGameScene Listeners to listen for events thrown
     * 
     * @param sceneType The type of scene to transition to.
     */
    private void goToScene(SceneType sceneType) {
        currentGameScene.removePropChangeListener(this);
        currentGameScene.destroy();

        currentGameScene = gameSceneFactory.createScene(sceneType, screenWidth, screenHeight);
        currentGameScene.addPropChangeListener(this);

        stage.setScene(currentGameScene.getScene());
    }

    /**
     * Handles property change events thrown by game scenes.
     * Handle sceneChange event by transitioning to the new scene defined by the property change event 
     * 
     * @param event The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        switch (event.getPropertyName()) {
            case "sceneChange":
                goToScene((SceneType) event.getNewValue());
                break;
            case "stateChange":
                break;  
            default:
                throw new IllegalArgumentException("Unknown Property Change Name: " + event);
        }
    }
}