package com.example.demo.actors.projectile;

import com.example.demo.actors.Projectile;
import com.example.demo.core.GameLoop;

/**
 * Represents a projectile fired by the user in the game.
 * This class extends {@link Projectile} and provides specific behavior for user projectiles,
 * including their image, initial position, and horizontal velocity.
 *
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class UserProjectile extends Projectile {

    /**
     * The name of the image file representing the user projectile.
     */
    private static final String IMAGE_NAME = "userfire.png";

    /**
     * The height of the user projectile's image.
     */
    private static final int IMAGE_HEIGHT = 7;

    /**
     * The horizontal velocity of the user projectile, calculated based on the game's target FPS.
     */
    private static final double HORIZONTAL_VELOCITY = Math.ceil(300.0 / GameLoop.getInstance(null).get_TARGET_FPS());

    /**
     * Constructs a {@link UserProjectile} with the specified initial position.
     *
     * @param initialXPos The initial X position of the user projectile.
     * @param initialYPos The initial Y position of the user projectile.
     */
    public UserProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
    }
}