package com.example.demo.actors.projectile;

import com.example.demo.actors.Projectile;
import com.example.demo.core.GameLoop;

/**
 * Represents a projectile fired by a boss in the game.
 * This class extends {@link Projectile} and provides specific behavior for boss projectiles,
 * including their image, initial position, and horizontal velocity.
 *
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class BossProjectile extends Projectile {

    /**
     * The name of the image file representing the boss projectile.
     */
    private static final String IMAGE_NAME = "projectile/fireball.png";

    /**
     * The height of the boss projectile's image.
     */
    private static final int IMAGE_HEIGHT = 75;

    /**
     * The horizontal velocity of the boss projectile, calculated based on the game's target FPS.
     */
    private static final double HORIZONTAL_VELOCITY = -1 * Math.ceil(300.0 / GameLoop.getInstance(null).get_TARGET_FPS());

    /**
     * The initial X position of the boss projectile.
     */
    private static final int INITIAL_X_POSITION = 950;

    /**
     * Constructs a {@link BossProjectile} with the specified initial Y position.
     *
     * @param initialYPos The initial Y position of the boss projectile.
     */
    public BossProjectile(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos, HORIZONTAL_VELOCITY);
    }
}