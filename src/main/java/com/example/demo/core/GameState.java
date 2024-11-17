package com.example.demo.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameState {
    public enum State {
        MENU,
        RUNNING,
        PAUSED,
        GAME_OVER
    }

    private static GameState instance;
    private final PropertyChangeSupport gameStateSupport;
    private State currentState;

    private GameState() {
        this.gameStateSupport = new PropertyChangeSupport(this);
        setStateGoToMenu();
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }

        return instance;
    }

    public void addPropChangeListener(PropertyChangeListener listener) {
        gameStateSupport.addPropertyChangeListener(listener);
    }

    // public void removePropChangeListener(PropertyChangeListener listener) {
    //     gameStateSupport.removePropertyChangeListener(listener);
    // }

    private void setPropChange(String propType, Object oldProp, Object newProp) {
        gameStateSupport.firePropertyChange(propType, oldProp, newProp);
    }

    public State getCurrentState() {
        return currentState;
    }

    private void setCurrentState(State state) {
        setPropChange("state", this.currentState, state);
        this.currentState = state;
    }

    public void setStateGoToMenu() {
        setCurrentState(State.MENU);
    }

    public void setStateStartGame() {
        setCurrentState(State.RUNNING);
    }

    public void setStatePauseGame() {
        setCurrentState(State.PAUSED);
    }

    public void setStateResumeGame() {
        setCurrentState(State.RUNNING);
    }

    public void setStateEndGame() {
        setCurrentState(State.GAME_OVER);
    }
}