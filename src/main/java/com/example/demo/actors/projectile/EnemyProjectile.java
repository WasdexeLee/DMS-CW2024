package com.example.demo.actors.projectile;

import com.example.demo.actors.Projectile;
import com.example.demo.core.GameLoop;

/**
 * Represents a projectile fired by an enemy in the game.
 * This class extends {@link Projectile} to inherit projectile behavior.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class EnemyProjectile extends Projectile {

    /** The name of the image file used for the enemy projectile. */
    private static final String IMAGE_NAME = "enemyFire.png";

    /** The height to which the enemy projectile image should be scaled. */
    private static final int IMAGE_HEIGHT = 31;

    /** The horizontal velocity of the enemy projectile, calculated based on the target FPS. */
    private static final double HORIZONTAL_VELOCITY = -1 * Math.ceil(200.0 / GameLoop.getInstance(null).get_TARGET_FPS());

    /**
     * Constructs an EnemyProjectile with the specified initial position.
     * 
     * @param initialXPos The initial X position of the projectile.
     * @param initialYPos The initial Y position of the projectile.
     */
    public EnemyProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
    }
}