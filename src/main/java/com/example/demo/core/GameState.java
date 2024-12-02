package com.example.demo.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.example.demo.utils.EnumUtil.State;

/**
 * Manages the state of the game and provides methods to transition between different states.
 * 
 * This class is a singleton and uses the PropertyChangeSupport mechanism to notify
 * listeners about changes in the game state. The possible states are defined in the
 * {@link State} enum.
 * 
 * @author Wasdexe Lee
 */
public class GameState {

    /** The singleton instance of the GameState. */
    private static GameState instance;
    
    /** The PropertyChangeSupport object to manage property change listeners. */
    private final PropertyChangeSupport gameStateSupport;
    
    /** The current state of the game. */
    private State currentState;

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the PropertyChangeSupport and sets the initial state to RUNNING.
     */
    private GameState() {
        this.gameStateSupport = new PropertyChangeSupport(this);
    }

    /**
     * Returns the singleton instance of the GameState.
     * 
     * @return The singleton instance of the GameState.
     */
    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }

        return instance;
    }

    /**
     * Adds a PropertyChangeListener to the game state.
     * 
     * @param listener The PropertyChangeListener to add.
     */
    public void addPropChangeListener(PropertyChangeListener listener) {
        gameStateSupport.addPropertyChangeListener(listener);
    }

    /**
     * Fires a property change event to notify listeners about a change in the game state.
     * 
     * @param propType The type of property that changed.
     * @param oldProp The old value of the property.
     * @param newProp The new value of the property.
     */
    private void setPropChange(String propType, Object oldProp, Object newProp) {
        gameStateSupport.firePropertyChange(propType, oldProp, newProp);
    }

    /**
     * Returns the current state of the game.
     * 
     * @return The current state of the game.
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * Sets the current state of the game and notifies listeners about the change.
     * 
     * @param state The new state of the game.
     */
    private void setCurrentState(State state) {
        setPropChange("stateChange", null, state);
        currentState = state;
    }

    /**
     * Transitions the game state to RUNNING.
     */
    public void setStateStartGame() {
        setCurrentState(State.RUNNING);
    }

    /**
     * Transitions the game state to PAUSED.
     */
    public void setStatePauseGame() {
        setCurrentState(State.PAUSED);
    }

    /**
     * Transitions the game state to RUNNING from PAUSED.
     */
    public void setStateResumeGame() {
        setCurrentState(State.RUNNING);
    }

    /**
     * Transitions the game state to STOP.
     */
    public void setStateEndGame() {
        setCurrentState(State.STOP);
    }
}