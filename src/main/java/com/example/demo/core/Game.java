package com.example.demo.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.example.demo.scenes.services.GameSceneManager;
import com.example.demo.utils.EnumUtil.State;

import javafx.stage.Stage;

/**
 * The main game class that manages the game loop, state, and scene management.
 * 
 * This class is a singleton and implements PropertyChangeListener to handle
 * game state changes.
 * It provides methods to initialize, update, and manage the game state and
 * scene transitions.
 * 
 * @author Wasdexe Lee
 */
public class Game implements PropertyChangeListener {

    /** The singleton instance of the Game. */
    private static Game instance;

    /** The primary stage for the game. */
    private Stage stage;

    /** The game state manager. */
    private GameState gameState;

    /** The game loop manager. */
    private GameLoop gameLoop;

    /** The game scene manager. */
    private GameSceneManager gameSceneManager;

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the game state, game loop, and game scene manager.
     * 
     * @param stage The primary stage for the game.
     */
    private Game(Stage stage) {
        this.stage = stage;
        this.gameState = GameState.getInstance();
        this.gameLoop = GameLoop.getInstance(this);

        addGameStatePropChangeListener(this);
        setStateEndGame();

        this.gameSceneManager = GameSceneManager.getInstance(this, this.stage);
    }

    /**
     * Returns the singleton instance of the Game.
     * 
     * @param stage The primary stage for the game.
     * @return The singleton instance of the Game.
     */
    public static Game getInstance(Stage stage) {
        if (instance == null) {
            instance = new Game(stage);
        }

        return instance;
    }

    /**
     * Initializes the game by setting the state to start the game and showing the
     * primary stage.
     */
    public void init() {
        stage.show();
    }

    /**
     * Updates the game scene manager.
     */
    public void update() {
        gameSceneManager.update();
    }

    /**
     * Transitions the game state to RUNNING.
     */
    public void setStateStartGame() {
        gameState.setStateStartGame();
    }

    /**
     * Transitions the game state to PAUSED.
     */
    public void setStatePauseGame() {
        gameState.setStatePauseGame();
    }

    /**
     * Transitions the game state to RUNNING from PAUSED.
     */
    public void setStateResumeGame() {
        gameState.setStateResumeGame();
    }

    /**
     * Transitions the game state to GAME_OVER.
     */
    public void setStateEndGame() {
        gameState.setStateEndGame();
    }

    /**
     * Adds a PropertyChangeListener to the game state.
     * 
     * @param listener The PropertyChangeListener to add.
     */
    public void addGameStatePropChangeListener(PropertyChangeListener listener) {
        gameState.addPropChangeListener(listener);
    }

    /**
     * Handles property change events for the game state.
     * 
     * @param event The PropertyChangeEvent to handle.
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        switch (event.getPropertyName()) {
            case "stateChange":
                switch ((State) event.getNewValue()) {
                    case RUNNING:
                        gameLoop.start();
                        break;
                    case PAUSED:
                        gameLoop.stop();
                        break;
                    case STOP:
                        gameLoop.stop();
                        break;
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown Property Change Name : " + event);
        }
    }
}