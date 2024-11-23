package com.example.demo.scenes.levels;

import com.example.demo.ActiveActorDestructible;
import com.example.demo.EnemyPlane;
import com.example.demo.scenes.levels.services.EnemyManager;
import com.example.demo.utils.EnumUtil.SceneType;

public class LevelOne extends LevelScene {
	
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	// private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";
	private static final SceneType NEXT_LEVEL = SceneType.LEVEL2;
	private static final int TOTAL_ENEMIES = 1;
	private static final int KILLS_TO_ADVANCE = 1;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

    private EnemyManager enemyManager;

	public LevelOne(double screenWidth, double screenHeight) {
		super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight, PLAYER_INITIAL_HEALTH);

        this.enemyManager = EnemyManager.getInstance();
	}

	@Override
    protected boolean userKillTargetLogic() {
        return getUserUnit().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

    @Override
    protected void userKillTargetReachedAction() {
        goToScene(NEXT_LEVEL);
    }
    
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getLevelState().getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				enemyManager.addEnemyUnit(newEnemy, getEnemyUnits(), getRoot());
			}
		}
	}
}