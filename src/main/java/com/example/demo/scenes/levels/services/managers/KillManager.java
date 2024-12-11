package com.example.demo.scenes.levels.services.managers;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.user.UserPlane;
import com.example.demo.scenes.levels.services.LevelState;
import com.example.demo.scenes.levels.services.LevelView;

import java.util.List;

/**
 * The KillManager class is responsible for managing the kill count in the game.
 * It provides methods to update the kill count based on the current state of the game.
 * 
 * This class is a singleton and provides methods to handle projectiles going out of the screen,
 * spawn new projectiles, and manage their lifecycle within the game scene.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class KillManager {

    /** The singleton instance of the KillManager. */
    private static KillManager instance;

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
     * Takes in all the different parameter to run logic to determine the increase in kill count. 
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
            levelState.incrementNumberOfKills();
        }
    }
}