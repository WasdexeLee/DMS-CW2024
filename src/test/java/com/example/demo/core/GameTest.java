package com.example.demo.core;

import com.example.demo.utils.EnumUtil.State;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest extends ApplicationTest {

    private Game game;
    private Stage stage;

    // Set up the JavaFX stage with the specified title, dimensions, and properties,
    // and initialize the game instance with the configured stage
    @Override
    public void start(Stage stage) throws Exception {
        int SCREEN_WIDTH = 1366;
        int SCREEN_HEIGHT = 762;
        String TITLE = "Sky Battle";

        this.stage = stage;
        this.stage.setTitle(TITLE);
        this.stage.setResizable(false);
        this.stage.setHeight(SCREEN_HEIGHT);
        this.stage.setWidth(SCREEN_WIDTH);

        this.game = Game.getInstance(this.stage);
    }

    // Test logic: Ensure the game initializes with the correct state (STOP) and the
    // stage is shown.
    @Test
    public void testInit() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                this.game.init();

                assertTrue(this.stage.isShowing());
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test logic: Ensure the game correctly transitions to the RUNNING state when
    // started.
    @Test
    public void testStartGame() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                game.setStateStartGame();

                assertEquals(State.RUNNING, game.getCurrentState());
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test logic: Ensure the game correctly transitions to the PAUSED state when
    // paused.
    @Test
    public void testPauseGame() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                game.setStateStartGame();
                game.setStatePauseGame();

                assertEquals(State.PAUSED, game.getCurrentState());
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test logic: Ensure the game correctly transitions back to the RUNNING state
    // when resumed.
    @Test
    public void testResumeGame() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                game.setStateStartGame();
                game.setStatePauseGame();
                game.setStateResumeGame();

                assertEquals(State.RUNNING, game.getCurrentState());
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test logic: Ensure the game correctly transitions to the STOP state when
    // ended.
    @Test
    public void testPropertyChangeEndGame() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                game.setStateStartGame();
                game.setStateEndGame();

                assertEquals(State.STOP, game.getCurrentState());
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test logic: Ensure the PropertyChangeListener correctly handles the RUNNING
    // state.
    @Test
    public void testPropertyChangeStartGame() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                TestPropertyChangeListener listener = new TestPropertyChangeListener();
                this.game.addGameStatePropChangeListener(listener);

                this.game.setStateStartGame();

                assertTrue(listener.isRunning);
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test logic: Ensure the PropertyChangeListener correctly handles the PAUSED
    // state.
    @Test
    public void testPropertyChangePauseGame() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                TestPropertyChangeListener listener = new TestPropertyChangeListener();
                this.game.addGameStatePropChangeListener(listener);

                this.game.setStateStartGame();
                this.game.setStatePauseGame();

                assertTrue(listener.isPaused);
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test logic: Ensure the PropertyChangeListener correctly handles the STOP
    // state.
    @Test
    public void testEndGame() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                TestPropertyChangeListener listener = new TestPropertyChangeListener();
                this.game.addGameStatePropChangeListener(listener);

                this.game.setStateStartGame();
                this.game.setStateEndGame();

                assertTrue(listener.isStopped);
            } finally {
                latch.countDown();
            }
        });

        latch.await();
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