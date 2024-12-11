package com.example.demo.scenes.levels.services;

/**
 * Represents the state of a level in the game.
 * This class is a singleton and provides methods to manage and retrieve level-specific data.
 * It tracks the number of kills, current number of enemies, and screen width.
 * The singleton pattern ensures consistent state data across the game.
 * This information is crucial for game progression and level-specific events.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class LevelState {

    /** The singleton instance of the {@link LevelState}. */
    private static LevelState instance;

    /** The number of kills achieved in the level. */
    private int numberOfKills;

    /** The current number of enemies in the level. */
    private int currentNumberOfEnemies;

    /** The width of the screen. */
    private final double screenWidth;

    /**
     * Private constructor to enforce singleton pattern.
     *
     * @param screenWidth The width of the screen.
     */
    private LevelState(double screenWidth) {
        this.screenWidth = screenWidth;
    }

    /**
     * Gets the singleton instance of the {@link LevelState}.
     *
     * @param screenWidth The width of the screen.
     * @return The singleton instance of {@link LevelState}.
     */
    public static LevelState getInstance(double screenWidth) {
        if (instance == null) {
            instance = new LevelState(screenWidth);
        }

        return instance;
    }

    /**
     * Gets the number of kills achieved in the level.
     *
     * @return The number of kills.
     */
    public int getNumberOfKills() {
        return numberOfKills;
    }

    /**
     * Sets the number of kills achieved in the level.
     *
     * @param numberOfKills The number of kills to set.
     */
    public void setNumberOfKills(int numberOfKills) {
        this.numberOfKills = numberOfKills;
    }

    /**
     * Increments the number of kills by one.
     */
    public void incrementNumberOfKills() {
        this.numberOfKills++;
    }

    /**
     * Gets the current number of enemies in the level.
     *
     * @return The current number of enemies.
     */
    public int getCurrentNumberOfEnemies() {
        return currentNumberOfEnemies;
    }

    /**
     * Sets the current number of enemies in the level.
     *
     * @param currentNumberOfEnemies The current number of enemies to set.
     */
    public void setCurrentNumberOfEnemies(int currentNumberOfEnemies) {
        this.currentNumberOfEnemies = currentNumberOfEnemies;
    }

    /**
     * Gets the width of the screen.
     *
     * @return The screen width.
     */
    public double getScreenWidth() {
        return screenWidth;
    }
}