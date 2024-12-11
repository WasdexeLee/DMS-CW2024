package com.example.demo.scenes.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemy.EnemyPlane;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.core.GameLoop;
import com.example.demo.core.GameState;
import com.example.demo.scenes.levels.services.managers.EnemyManager;
import com.example.demo.utils.EnumUtil.BackgroundAudioType;
import com.example.demo.utils.EnumUtil.SceneType;

/**
 * Represents the first level of the game.
 * This class extends {@link LevelScene} and provides specific functionality for Level One,
 * including enemy spawning logic, kill target conditions, and scene transitions.
 * 
 * The level is designed to spawn enemies with a specific probability and ensures that enemies do not spawn too close to each other to avoid clumping.
 * The player must achieve a certain number of kills to advance to the next level.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class LevelOne extends LevelScene {

    /** The path to the background image for Level One. */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpeg";

    /** The next scene to transition to after completing Level One. */
    private static final SceneType NEXT_SCENE = SceneType.LEVEL2;

    /** The maximum Y position for spawning enemies. */
    private static final double ENEMY_MAXIMUM_Y_POS = 648;

    /** The total number of enemies to spawn in the level. */
    private static final int TOTAL_ENEMIES = 5;

    /** The number of kills required to advance to the next level. */
    private static final int KILLS_TO_ADVANCE = 30;

    /** The probability of spawning an enemy per frame, calculated based on the game's target FPS. */
    private static final double ENEMY_SPAWN_PROBABILITY = 4.0 / (GameLoop.getInstance(null).get_TARGET_FPS());

    /** An array to store the Y coordinates of the most recently spawned enemies to prevent clumping. */
    private double[] recentSpawnYCoord;

    /** The initial health of the player for this level. */
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /** The manager for handling enemy-related operations. */
    private EnemyManager enemyManager; 

    /**
     * Constructs a {@link LevelOne} with the specified screen width and height.
     * Initializes gameState, enemyManager, audioManager and other components needed by the level
     *
     * @param screenWidth  The width of the screen.
     * @param screenHeight The height of the screen.
     */
    public LevelOne(double screenWidth, double screenHeight) {
        super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight, PLAYER_INITIAL_HEALTH);

        recentSpawnYCoord = new double[]{0.0, 0.0, 0.0};
        GameState.getInstance().setStateStartGame();
        this.enemyManager = EnemyManager.getInstance();
        AudioManager.getInstance().changeBackgroundAudio(BackgroundAudioType.LEVEL);
    }

    /**
     * Logic to determines if the player has reached the kill target to advance to the next level.
     * Used by template method in levelScene to check whether the game is over.
     *
     * @return {@code true} if the player has achieved the required number of kills, {@code false} otherwise.
     */
    @Override
    protected boolean userKillTargetLogic() {
        return getLevelState().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

    /**
     * Defines the action to take when the player reaches the kill target.
     * Used by template method in levelScene to check whether the game is over.
     * Transitions to the next level.
     */
    @Override
    protected void userKillTargetReachedAction() {
        goToScene(NEXT_SCENE);
    }

    /**
     * Spawns enemy units for the level based on the spawn probability.
     * Ensures that enemies do not spawn too close to each other to avoid clumping.
     * Logic is implemented with randamization of spawning to make better gameplay.
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getLevelState().getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition;
                do {
                    newEnemyInitialYPosition = Math.random() * ENEMY_MAXIMUM_Y_POS;
                } while (planeClumping(newEnemyInitialYPosition));

                ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, "enemyplane1.png", .27);
                enemyManager.addEnemyUnit(newEnemy, getEnemyUnits(), getRoot());
                recentSpawnYCoord[0] = recentSpawnYCoord[1];
                recentSpawnYCoord[1] = recentSpawnYCoord[2];
                recentSpawnYCoord[2] = newEnemyInitialYPosition;
            }
        }
    }

    /**
     * Helper method to check if the new enemy's Y position is too close to the recently spawned enemies to avoid clumping.
     *
     * @param newEnemyInitialYPosition The Y position of the new enemy to be spawned.
     * @return {@code true} if the new enemy's Y position is too close to the recent spawns, {@code false} otherwise.
     */
    private boolean planeClumping(double newEnemyInitialYPosition) {
        for (int i = 0; i < 3; i++) {
            if (Math.abs(recentSpawnYCoord[i] - newEnemyInitialYPosition) < 50)
                return true;
        }
        return false;
    }
}