package com.example.demo.scenes.levels;

import com.example.demo.actors.enemy.Boss;
import com.example.demo.core.Game;
import com.example.demo.scenes.levels.services.managers.EnemyManager;
import com.example.demo.utils.EnumUtil.SceneType;

/**
 * Represents the third level of the game.
 * This class extends {@link LevelScene} and provides specific functionality for Level Three,
 * including the introduction of a boss enemy and logic for transitioning to the win scene.
 * 
 * The level is designed to feature a single boss enemy that the player must defeat to win the game.
 * Once the boss is destroyed, the player transitions to the win scene.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class LevelThree extends LevelScene {

	/** The path to the background image for Level Three. */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background/background3.jpeg";

	/** The initial health of the player for this level. */
	private static final int PLAYER_INITIAL_HEALTH = 6;

	/** The win scene to transition to after defeating the boss. */
	private final SceneType WIN_SCENE = SceneType.WINSCENE;

	/** The boss enemy in the level. */
	private final Boss boss;

	/** The manager for handling enemy-related operations. */
	private EnemyManager enemyManager;

	/**
	 * Constructs a {@link LevelThree} with the specified screen height and width.
	 * Initializes the boss of the level and the enemyManager instance to deal with
	 * enemy related logic like enemy penetration.
	 *
	 * @param screenHeight The height of the screen.
	 * @param screenWidth  The width of the screen.
	 */
    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        this.boss = new Boss(getLevelView());
        this.enemyManager = EnemyManager.getInstance();
    }

    /**
     * Determines if the player has reached the kill target to win the game.
     * Used by template method in levelScene to check whether the game is over.
     *
     * @return {@code true} if the boss is destroyed, {@code false} otherwise.
     */
    @Override
    protected boolean userKillTargetLogic() {
        return boss.getIsDestroyed();
    }

    /**
     * Defines the action to take when the player defeats the boss.
     * Used by template method in levelScene to check whether the game is over.
     * Transitions to the win scene and ends the game.
     */
    @Override
    protected void userKillTargetReachedAction() {
        Game.getInstance(null).setStateEndGame();
        goToScene(WIN_SCENE);
    }

    /**
     * Spawns the boss enemy and its shield if no enemies are currently present.
	 * Specific to Level Three as it only has 1 enemy and no continouos spawning of enemy.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getLevelState().getCurrentNumberOfEnemies() == 0) {
            enemyManager.addEnemyUnit(boss, getEnemyUnits(), getRoot());
            enemyManager.addEnemyProp(boss.getShieldImage(), getRoot());
        }
    }
}