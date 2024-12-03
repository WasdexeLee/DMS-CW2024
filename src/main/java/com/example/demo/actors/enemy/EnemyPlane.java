package com.example.demo.actors.enemy;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.projectile.EnemyProjectile;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.core.GameLoop;
import com.example.demo.utils.EnumUtil.EffectAudioType;

public class EnemyPlane extends FighterPlane {

    private static final int IMAGE_HEIGHT = 53;
    private static final int HORIZONTAL_VELOCITY = (int) (-1 * Math.ceil(120.0 / GameLoop.getInstance(null).get_TARGET_FPS()));
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 1;
    private final double FIRE_RATE;

    public EnemyPlane(double initialXPos, double initialYPos, String imageName, double fireRate) {
        super(imageName, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);

        this.FIRE_RATE = fireRate / GameLoop.getInstance(null).get_TARGET_FPS();
    }

    @Override
    public void updateActor() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);

            AudioManager.getInstance().fireEffectAudio(EffectAudioType.ENEMYFIRE);

            return new EnemyProjectile(projectileXPosition, projectileYPostion);
        }
        return null;
    }
}