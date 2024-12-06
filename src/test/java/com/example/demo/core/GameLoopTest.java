package com.example.demo.core;

import javafx.stage.Stage;
import javafx.animation.Timeline;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameLoopTest extends ApplicationTest {

    private GameLoop gameLoop;
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
        this.gameLoop = GameLoop.getInstance(this.game);
    }

    // Test logic: Ensure the game loop correctly starts and transitions to the
    // RUNNING status.
    @Test
    public void testStartGameLoop() {
        gameLoop.start();

        assertEquals(Timeline.Status.RUNNING, getTimeline().getStatus());
    }

    // Test logic: Ensure the game loop correctly stops and transitions to the
    // STOPPED status.
    @Test
    public void testStopGameLoop() {
        gameLoop.start();
        gameLoop.stop();

        assertEquals(Timeline.Status.STOPPED, getTimeline().getStatus());
    }

    // Helper method to access the private timeline field for testing
    private Timeline getTimeline() {
        try {
            java.lang.reflect.Field field = GameLoop.class.getDeclaredField("timeline");
            field.setAccessible(true);

            return (Timeline) field.get(gameLoop);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access timeline field", e);
        }
    }
}