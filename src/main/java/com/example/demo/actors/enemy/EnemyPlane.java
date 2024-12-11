package com.example.demo.actors.enemy;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.projectile.EnemyProjectile;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.core.GameLoop;
import com.example.demo.utils.EnumUtil.EffectAudioType;

/**
 * Represents an enemy plane in the game.
 * This class extends {@link FighterPlane} and provides specific behavior for enemy planes,
 * including movement, firing projectiles, and audio effects.
 *
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class EnemyPlane extends FighterPlane {

    /**
     * The height of the enemy plane's image.
     */
    private static final int IMAGE_HEIGHT = 53;

    /**
     * The horizontal velocity of the enemy plane, calculated based on the game's target FPS.
     */
    private static final int HORIZONTAL_VELOCITY = (int) (-1 * Math.ceil(120.0 / GameLoop.getInstance(null).get_TARGET_FPS()));

    /**
     * The X position offset for spawning projectiles.
     */
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;

    /**
     * The Y position offset for spawning projectiles.
     */
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;

    /**
     * The initial health of the enemy plane.
     */
    private static final int INITIAL_HEALTH = 1;

    /**
     * The fire rate of the enemy plane, adjusted based on the game's target FPS.
     */
    private final double FIRE_RATE;

    /**
     * Constructs an {@link EnemyPlane} with the specified initial position, image name, and fire rate.
     *
     * @param initialXPos The initial X position of the enemy plane.
     * @param initialYPos The initial Y position of the enemy plane.
     * @param imageName   The name of the image file representing the enemy plane.
     * @param fireRate    The fire rate of the enemy plane.
     */
    public EnemyPlane(double initialXPos, double initialYPos, String imageName, double fireRate) {
        super(imageName, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
        this.FIRE_RATE = fireRate / GameLoop.getInstance(null).get_TARGET_FPS();
    }

    /**
     * Updates the state of the enemy plane by moving it horizontally.
     */
    @Override
    public void updateActor() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Fires a projectile if the randomized logic probability is lesser than the fire rate.
     *
     * @return An {@link EnemyProjectile} which is a subclass of {@link ActiveActorDestructible} representing the fired projectile, or {@code null} if no projectile is fired.
     */
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