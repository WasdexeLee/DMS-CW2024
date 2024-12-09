package com.example.demo.scenes.levels.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LevelStateTest {

    private LevelState levelState;

    @BeforeAll
    public void setUp() {
        this.levelState = LevelState.getInstance(1366);
    }

    // Test logic: Ensure the LevelState initializes with the correct screen width.
    @Test
    public void testInitialization() {
        assertEquals(1366, levelState.getScreenWidth());
    }

    // Test logic: Ensure the LevelState correctly manages the number of kills.
    @Test
    public void testSetNumberOfKills() {
        levelState.setNumberOfKills(10);

        assertEquals(10, levelState.getNumberOfKills());
    }

    @Test
    public void testIncrementNumberOfKills() {
        levelState.incrementNumberOfKills();

        assertEquals(11, levelState.getNumberOfKills());
    }

    // Test logic: Ensure the LevelState correctly manages the current number of
    // enemies.
    @Test
    public void testCurrentNumberOfEnemies() {
        levelState.setCurrentNumberOfEnemies(5);

        assertEquals(5, levelState.getCurrentNumberOfEnemies());
    }
}