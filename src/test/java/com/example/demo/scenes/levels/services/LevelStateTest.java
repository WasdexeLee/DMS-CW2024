package com.example.demo.scenes.levels.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class LevelStateTest extends ApplicationTest {

    private LevelState levelState;

    // Set up the JavaFX stage with the specified title, dimensions, and properties
    @Override
    public void start(Stage stage) throws Exception {
        this.levelState = LevelState.getInstance(1366);
    }

    // Test logic: Ensure the LevelState initializes with the correct screen width.
    @Test
    public void testInitialization() throws InterruptedException {
        assertEquals(1366, levelState.getScreenWidth());
    }

    // Test logic: Ensure the LevelState correctly manages the number of kills.
    @Test
    public void testSetNumberOfKills() throws InterruptedException {
        levelState.setNumberOfKills(10);

        assertEquals(10, levelState.getNumberOfKills());
    }

    @Test
    public void testIncrementNumberOfKills() throws InterruptedException {
        levelState.setNumberOfKills(10);
        levelState.incrementNumberOfKills();

        assertEquals(11, levelState.getNumberOfKills());
    }

    // Test logic: Ensure the LevelState correctly manages the current number of
    // enemies.
    @Test
    public void testCurrentNumberOfEnemies() throws InterruptedException {
        levelState.setCurrentNumberOfEnemies(5);

        assertEquals(5, levelState.getCurrentNumberOfEnemies());
    }
}