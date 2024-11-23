package com.example.demo.scenes.levels.services;

import com.example.demo.ActiveActorDestructible;

import java.util.List;

import javafx.scene.Group;

/**
 * Manages enemy units in the game, including handling their penetration of the user's defenses
 * and adding new enemy units to the game scene.
 * 
 * This class is a singleton and provides methods to handle enemy penetration, add new enemy units,
 * and manage their lifecycle within the game scene.
 * 
 * @author Wasdexe Lee
 */
public class EnemyManager {
    /** The singleton instance of the EnemyManager. */
    private static EnemyManager instance;

    /**
     * Returns the singleton instance of the EnemyManager.
     * 
     * @return The singleton instance of the EnemyManager.
     */
    public static EnemyManager getInstance() {
        if (instance == null) {
            instance = new EnemyManager();
        }

        return instance;
    }

    /**
     * Handles enemy penetration by damaging the user unit and destroying the penetrating enemy.
     * 
     * @param userUnit The user unit to take damage.
     * @param enemyUnits The list of enemy units to check for penetration.
     * @param screenWidth The width of the screen.
     */
    public void handleEnemyPenetration(ActiveActorDestructible userUnit, List<ActiveActorDestructible> enemyUnits,
            double screenWidth) {
        for (ActiveActorDestructible enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy, screenWidth)) {
                userUnit.takeDamage();
                enemy.destroy();
            }
        }
    }

    /**
     * Adds a new enemy unit to the list of enemy units and the root group.
     * 
     * @param enemy The enemy unit to add.
     * @param enemyUnits The list of enemy units.
     * @param root The root group to add the enemy unit to.
     */
    public void addEnemyUnit(ActiveActorDestructible enemy, List<ActiveActorDestructible> enemyUnits, Group root) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    /**
     * Checks if an enemy has penetrated the user's defenses.
     * 
     * @param enemy The enemy to check.
     * @param screenWidth The width of the screen.
     * @return {@code true} if the enemy has penetrated the defenses, {@code false} otherwise.
     */
    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy, double screenWidth) {
        return Math.abs(enemy.getTranslateX()) - enemy.getBoundsInParent().getWidth() > screenWidth;
    }
}