package com.example.demo.scenes.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemy.EnemyPlane;
import com.example.demo.core.GameLoop;
import com.example.demo.core.GameState;
import com.example.demo.scenes.levels.services.managers.EnemyManager;
import com.example.demo.utils.EnumUtil.SceneType;

/**
 * Represents the second level of the game.
 * This class extends {@link LevelScene} to inherit level-specific functionality.
 * 
 * The level features multiple enemy types and a higher difficulty compared to the first level.
 * The player must achieve a specific number of kills to advance to the next level.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class LevelTwo extends LevelScene {

   /** The path to the background image for this level. */
   private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background/background2.jpg";

   /** The next scene to transition to after completing this level. */
   private static final SceneType NEXT_SCENE = SceneType.LEVEL3;

   /** The maximum Y position for spawning enemies. */
   private static final double ENEMY_MAXIMUM_Y_POS = 648;

   /** The array of enemy plane image names for this level. */
   private static final String[] ENEMY_PLANE_IMAGE_NAME = {"plane/enemyplane2.png", "plane/enemyplane3.png"};

   /** The total number of enemies to spawn in this level. */
   private static final int TOTAL_ENEMIES = 7;

   /** The number of kills required to advance to the next level. */
   private static final int KILLS_TO_ADVANCE = 50;

   /** The probability of spawning an enemy in each frame, calculated based on the target FPS. */
   private static final double ENEMY_SPAWN_PROBABILITY = 7.0 / (GameLoop.getInstance(null).get_TARGET_FPS());

   /** Array to store the recent spawn Y coordinates to prevent enemy clumping. */
   private double[] recentSpawnYCoord;

   /** The initial health of the player for this level. */
   private static final int PLAYER_INITIAL_HEALTH = 6;

   /** The manager responsible for handling enemy units. */
   private EnemyManager enemyManager;

    /**
     * Constructs a LevelTwo with the specified screen dimensions.
     * Initializes gameState, enemyManager, audioManager and other components needed by the level
     *
     * @param screenWidth  The width of the screen.
     * @param screenHeight The height of the screen.
     */
    public LevelTwo(double screenWidth, double screenHeight) {
        super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight, PLAYER_INITIAL_HEALTH);

        recentSpawnYCoord = new double[]{0.0, 0.0, 0.0};
        GameState.getInstance().setStateStartGame();
        this.enemyManager = EnemyManager.getInstance();
    }

    /**
     * Determines if the player has reached the kill target to advance to the next level.
     * Used by template method in levelScene to check whether the game is over.
     *
     * @return True if the player has reached the kill target, false otherwise.
     */
    @Override
    protected boolean userKillTargetLogic() {
        return getLevelState().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

    /**
     * Performs the action when the player reaches the kill target.
     * Used by template method in levelScene to check whether the game is over.
     * Transitions to the next scene.
     */
    @Override
    protected void userKillTargetReachedAction() {
        goToScene(NEXT_SCENE);
    }

    /**
     * Spawns enemy units in the level.
     * Ensures that enemies are not spawned too close to each other to prevent clumping.
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

                // Randomly select an enemy plane image
                int imageIndex = (int) Math.round(Math.random());

                ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, ENEMY_PLANE_IMAGE_NAME[imageIndex], .36);
                enemyManager.addEnemyUnit(newEnemy, getEnemyUnits(), getRoot());

                // Update recent spawn Y coordinates to prevent clumping
                recentSpawnYCoord[0] = recentSpawnYCoord[1];
                recentSpawnYCoord[1] = recentSpawnYCoord[2];
                recentSpawnYCoord[2] = newEnemyInitialYPosition;
            }
        }
    }

    /**
     * Helper method to check if the new enemy spawn position is too close to recently spawned enemies.
     *
     * @param newEnemyInitialYPosition The Y position of the new enemy to be spawned.
     * @return True if the new enemy spawn position is too close to recent spawns, false otherwise.
     */
    private boolean planeClumping(double newEnemyInitialYPosition) {
        for (int i = 0; i < 3; i++) {
            if (Math.abs(recentSpawnYCoord[i] - newEnemyInitialYPosition) < 50)
                return true;
        }
        return false;
    }
}