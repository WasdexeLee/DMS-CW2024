package com.example.demo.scenes.levels.services;

import com.example.demo.ActiveActorDestructible;
import com.example.demo.UserPlane;

import java.util.List;

/**
 * The KillManager class is responsible for managing the kill count in the game.
 * It provides methods to update the kill count based on the current state of the game.
 */
public class KillManager {
    private static KillManager instance;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private KillManager() {
        // Private constructor to enforce singleton pattern
    }

    /**
     * Returns the singleton instance of the KillManager.
     *
     * @return The singleton instance of KillManager.
     */
    public static KillManager getInstance() {
        if (instance == null) {
            instance = new KillManager();
        }

        return instance;
    }

    /**
     * Updates the kill count based on the current state of the game.
     *
     * @param levelState The current state of the level.
     * @param levelView The view of the level.
     * @param enemyUnits The list of enemy units currently in the game.
     * @param userUnit The user's unit.
     * @param userHealth The current health of the user's unit.
     */
    public void updateKillCount(LevelState levelState, LevelView levelView, List<ActiveActorDestructible> enemyUnits,
            UserPlane userUnit, int userHealth) {
        int killEnemies = levelState.getCurrentNumberOfEnemies() - enemyUnits.size();
        for (int i = 0; i < killEnemies; i++) {
            userUnit.incrementKillCount();
            levelState.incrementNumberOfKills();
        }
    }
}