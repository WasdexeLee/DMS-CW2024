package com.example.demo.scenes.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemy.EnemyPlane;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.core.GameLoop;
import com.example.demo.core.GameState;
import com.example.demo.scenes.levels.services.managers.EnemyManager;
import com.example.demo.utils.EnumUtil.BackgroundAudioType;
import com.example.demo.utils.EnumUtil.SceneType;

public class LevelOne extends LevelScene {
	
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpeg";
	private static final SceneType NEXT_SCENE = SceneType.LEVEL2;
    private static final double ENEMY_MAXIMUM_Y_POS = 648;

	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = 4.0 / (GameLoop.getInstance(null).get_TARGET_FPS());
    private double[] recentSpawnYCoord;

	// private static final int PLAYER_INITIAL_HEALTH = 5;
	private static final int PLAYER_INITIAL_HEALTH = 50;

    private EnemyManager enemyManager;

	public LevelOne(double screenWidth, double screenHeight) {
		super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight, PLAYER_INITIAL_HEALTH);

        recentSpawnYCoord = new double[]{0.0, 0.0, 0.0};
        GameState.getInstance().setStateStartGame();
        this.enemyManager = EnemyManager.getInstance();

        AudioManager.getInstance().changeBackgroundAudio(BackgroundAudioType.LEVEL);
	}

	@Override
    protected boolean userKillTargetLogic() {
        return getLevelState().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

    @Override
    protected void userKillTargetReachedAction() {
        goToScene(NEXT_SCENE);
    }
    
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getLevelState().getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition;
                do {
                    newEnemyInitialYPosition = Math.random() * ENEMY_MAXIMUM_Y_POS;
                } while(planeClumping(newEnemyInitialYPosition));

				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, "enemyplane1.png", .2);
				enemyManager.addEnemyUnit(newEnemy, getEnemyUnits(), getRoot());

                recentSpawnYCoord[0] = recentSpawnYCoord[1];
                recentSpawnYCoord[1] = recentSpawnYCoord[2];
                recentSpawnYCoord[2] = newEnemyInitialYPosition;
			}
		}
	}

    private boolean planeClumping(double newEnemyInitialYPosition) {
        for (int i = 0; i < 3; i++){
            if (Math.abs(recentSpawnYCoord[i] - newEnemyInitialYPosition) < 50)
                return true;
        }
        return false;
    }
}