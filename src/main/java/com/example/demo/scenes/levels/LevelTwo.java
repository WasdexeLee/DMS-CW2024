package com.example.demo.scenes.levels;

import com.example.demo.actors.enemy.Boss;
import com.example.demo.core.Game;
import com.example.demo.scenes.levels.services.managers.EnemyManager;
import com.example.demo.utils.EnumUtil.SceneType;

public class LevelTwo extends LevelScene {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
    private final SceneType WIN_SCENE = SceneType.WINSCENE;

	private final Boss boss;
    private EnemyManager enemyManager;

	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);

		this.boss = new Boss();
        this.enemyManager = EnemyManager.getInstance();
	}

	@Override
    protected boolean userKillTargetLogic() {
        return boss.getIsDestroyed();
    }

    @Override
    protected void userKillTargetReachedAction() {
        // getLevelView().showWinImage();
        Game.getInstance(null).setStateEndGame();
        goToScene(WIN_SCENE);
    }
    
	@Override
	protected void spawnEnemyUnits() {
		if (getLevelState().getCurrentNumberOfEnemies() == 0) {
			enemyManager.addEnemyUnit(boss, getEnemyUnits(), getRoot());
            enemyManager.addEnemyProp(boss.getShieldImage(), getRoot());
		}
	}
}