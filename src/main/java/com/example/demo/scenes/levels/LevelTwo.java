package com.example.demo.scenes.levels;

import com.example.demo.Boss;
import com.example.demo.core.Game;
import com.example.demo.scenes.levels.services.EnemyManager;

public class LevelTwo extends LevelScene {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;

	private final Boss boss;
    private EnemyManager enemyManager;

	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);

		this.boss = new Boss();
        this.enemyManager = EnemyManager.getInstance();
	}

	@Override
    protected boolean userKillTargetLogic() {
        return boss.isDestroyed();
    }

    @Override
    protected void userKillTargetReachedAction() {
        getLevelView().showWinImage();
        Game.getInstance(null).setStateEndGame();
    }
    
	@Override
	protected void spawnEnemyUnits() {
		if (getLevelState().getCurrentNumberOfEnemies() == 0) {
			enemyManager.addEnemyUnit(boss, getEnemyUnits(), getRoot());
		}
	}
}