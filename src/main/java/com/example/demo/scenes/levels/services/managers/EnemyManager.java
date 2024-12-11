package com.example.demo.scenes.levels.services.managers;

import java.util.List;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.utils.EnumUtil.EffectAudioType;

import javafx.scene.Group;
import javafx.scene.Node;

/**
 * Manages enemy units in the game, including handling their penetration of the user's defenses
 * and adding new enemy units to the game scene.
 * 
 * This class is a singleton and provides methods to handle enemy penetration, add new enemy units,
 * and manage their lifecycle within the game scene.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
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
     * Damaging user unit to deal health decrease.
     * Destroying the enemy directly as they are already out of screen view and will not be making any actions in the future.
     * Beneficial to freeing up memory, keeping game responsive.
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

                AudioManager.getInstance().fireEffectAudio(EffectAudioType.DAMAGE);
            }
        }
    }

    /**
     * Adds a new enemy unit to the list of enemy units and the root group.
     * Universal method to be called to add any type of enemy to the list of enemy units.
     * Segregating the logic of adding into the enemy list and reducing repetitive code.
     * 
     * @param enemy The enemy unit to add.
     * @param enemyUnits The list of enemy units.
     * @param root The root group to add the enemy unit to.
     */
    public void addEnemyUnit(ActiveActorDestructible enemy, List<ActiveActorDestructible> enemyUnits, Group root) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    public void addEnemyProp(Node enemyProp, Group root) {
        root.getChildren().add(enemyProp);
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