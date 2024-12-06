package com.example.demo.core;

import com.example.demo.utils.EnumUtil.State;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameStateTest {

    private GameState gameState;

    @BeforeAll
    public void setUp() {
        this.gameState = GameState.getInstance();
    }

    // Test logic: Ensure the game correctly transitions to the RUNNING state when
    // started.
    @Test
    public void testStartGame() {
        gameState.setStateStartGame();

        assertEquals(State.RUNNING, gameState.getCurrentState());
    }

    // Test logic: Ensure the game correctly transitions to the PAUSED state when
    // paused.
    @Test
    public void testPauseGame() {
        gameState.setStateStartGame();
        gameState.setStatePauseGame();

        assertEquals(State.PAUSED, gameState.getCurrentState());
    }

    // Test logic: Ensure the game correctly transitions back to the RUNNING state
    // when resumed.
    @Test
    public void testResumeGame() {
        gameState.setStateStartGame();
        gameState.setStatePauseGame();
        gameState.setStateResumeGame();

        assertEquals(State.RUNNING, gameState.getCurrentState());
    }

    // Test logic: Ensure the game correctly transitions to the STOP state when
    // ended.
    @Test
    public void testEndGame() {
        gameState.setStateStartGame();
        gameState.setStateEndGame();

        assertEquals(State.STOP, gameState.getCurrentState());
    }

    // Test logic: Ensure the PropertyChangeListener correctly handles the RUNNING
    // state.
    @Test
    public void testPropertyChangeStartGame() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        gameState.addPropChangeListener(listener);

        gameState.setStateStartGame();

        assertTrue(listener.isRunning);
    }

    // Test logic: Ensure the PropertyChangeListener correctly handles the PAUSED
    // state.
    @Test
    public void testPropertyChangePauseGame() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        gameState.addPropChangeListener(listener);

        gameState.setStateStartGame();
        gameState.setStatePauseGame();

        assertTrue(listener.isPaused);
    }

    // Test logic: Ensure the PropertyChangeListener correctly handles the STOP
    // state.
    @Test
    public void testPropertyChangeEndGame() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        gameState.addPropChangeListener(listener);

        gameState.setStateStartGame();
        gameState.setStateEndGame();

        assertTrue(listener.isStopped);
    }

    private static class TestPropertyChangeListener implements PropertyChangeListener {
        boolean isRunning = false;
        boolean isPaused = false;
        boolean isStopped = false;

        @Override
        public void propertyChange(PropertyChangeEvent event) {
            switch ((State) event.getNewValue()) {
                case RUNNING:
                    isRunning = true;
                    break;
                case PAUSED:
                    isPaused = true;
                    break;
                case STOP:
                    isStopped = true;
                    break;
            }
        }
    }
}