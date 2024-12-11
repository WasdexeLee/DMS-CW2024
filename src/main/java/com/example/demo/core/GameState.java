package com.example.demo.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.example.demo.utils.EnumUtil.State;

/**
 * Manages the state of the game and notifies listeners of state changes.
 * This class uses the {@link PropertyChangeSupport} mechanism to handle
 * state change events.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class GameState {

    /** The singleton instance of the GameState class. */
    private static GameState instance;

    /** The support object for managing property change listeners. */
    private final PropertyChangeSupport gameStateSupport;

    /** The current state of the game. */
    private State currentState;

    /**
     * Private constructor to enforce the singleton pattern.
     * Initializes the property change support.
     */
    private GameState() {
        this.gameStateSupport = new PropertyChangeSupport(this);
    }

    /**
     * Returns the singleton instance of the GameState class.
     * 
     * @return The singleton instance of the GameState class.
     */
    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }

        return instance;
    }

    /**
     * Adds a property change listener to the game state.
     * 
     * @param listener The listener to be added.
     */
    public void addPropChangeListener(PropertyChangeListener listener) {
        gameStateSupport.addPropertyChangeListener(listener);
    }

    /**
     * Fires a property change event with the specified property type, old value, and new value.
     * 
     * @param propType The type of the property being changed.
     * @param oldProp The old value of the property.
     * @param newProp The new value of the property.
     */
    private void setPropChange(String propType, Object oldProp, Object newProp) {
        gameStateSupport.firePropertyChange(propType, oldProp, newProp);
    }

    /**
     * Retrieves the current state of the game.
     * 
     * @return The current state of the game.
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * Sets the current state of the game and notifies listeners.
     * 
     * @param state The new state to set.
     */
    private void setCurrentState(State state) {
        setPropChange("stateChange", null, state);
        currentState = state;
    }

    /**
     * Sets the game state to (RUNNING) "Start Game".
     */
    public void setStateStartGame() {
        setCurrentState(State.RUNNING);
    }

    /**
     * Sets the game state to (PAUSED) "Pause Game".
     */
    public void setStatePauseGame() {
        setCurrentState(State.PAUSED);
    }

    /**
     * Sets the game state to (RUNNING) "Resume Game".
     */
    public void setStateResumeGame() {
        setCurrentState(State.RUNNING);
    }

    /**
     * Sets the game state to (STOP) "End Game".
     */
    public void setStateEndGame() {
        setCurrentState(State.STOP);
    }
}