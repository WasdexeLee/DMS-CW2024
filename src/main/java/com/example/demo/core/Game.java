package com.example.demo.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.example.demo.scenes.services.GameSceneManager;
import com.example.demo.utils.EnumUtil.State;

import javafx.stage.Stage;

/**
 * The core game manager that handles the game's lifecycle, state management,
 * and scene updates. This class implements {@link PropertyChangeListener} to
 * listen for state changes and react accordingly.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class Game implements PropertyChangeListener {

    /** The singleton instance of the Game class. */
    private static Game instance;

    /** The JavaFX stage where the game is displayed. */
    private Stage stage;

    /** The current state of the game. */
    private GameState gameState;

    /** The game loop responsible for updating the game. */
    private GameLoop gameLoop;

    /** The manager for handling game scenes. */
    private GameSceneManager gameSceneManager;

    /**
     * Private constructor to enforce the singleton pattern.
     * Initializes the game state, game loop, and scene manager.
     * 
     * @param stage The JavaFX stage where the game is displayed.
     */
    private Game(Stage stage) {
        this.stage = stage;
        this.gameState = GameState.getInstance();
        this.gameLoop = GameLoop.getInstance(this);

        // Add this instance as a listener for game state changes
        addGameStatePropChangeListener(this);
        setStateEndGame();

        this.gameSceneManager = GameSceneManager.getInstance(this, this.stage);
    }

    /**
     * Returns the singleton instance of the Game class.
     * 
     * @param stage The JavaFX stage where the game is displayed.
     * @return The singleton instance of the Game class.
     */
    public static Game getInstance(Stage stage) {
        if (instance == null) {
            instance = new Game(stage);
        }

        return instance;
    }

    /**
     * Initializes and displays the game stage.
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
     * Sets the game state to "Start Game".
     */
    public void setStateStartGame() {
        gameState.setStateStartGame();
    }

    /**
     * Sets the game state to "Pause Game".
     */
    public void setStatePauseGame() {
        gameState.setStatePauseGame();
    }

    /**
     * Sets the game state to "Resume Game".
     */
    public void setStateResumeGame() {
        gameState.setStateResumeGame();
    }

    /**
     * Sets the game state to "End Game".
     */
    public void setStateEndGame() {
        gameState.setStateEndGame();
    }

    /**
     * Retrieves the current state of the game.
     * 
     * @return The current state of the game.
     */
    public State getCurrentState() {
        return gameState.getCurrentState();
    }

    /**
     * Adds a property change listener to the game state.
     * 
     * @param listener The listener to be added.
     */
    public void addGameStatePropChangeListener(PropertyChangeListener listener) {
        gameState.addPropChangeListener(listener);
    }

    /**
     * Handles property change events from the game state.
     * 
     * @param event The property change event.
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
                throw new IllegalArgumentException("Unknown Property Change Name: " + event);
        }
    }
}