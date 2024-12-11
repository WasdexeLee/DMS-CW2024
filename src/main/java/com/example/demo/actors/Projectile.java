package com.example.demo.actors;

/**
 * Represents an abstract class for projectiles in the game.
 * This class extends {@code ActiveActorDestructible} and provides functionality
 * for projectiles with horizontal movement and destruction behavior.
 *
 * @author WasdexeLee (Lee Jia Zhe)
 */
public abstract class Projectile extends ActiveActorDestructible {

    /**
     * The horizontal velocity of the projectile.
     */
    private final double horizontalVelocity;

    /**
     * Constructs a {@code Projectile} with the specified image details, initial position, and horizontal velocity.
     *
     * @param imageName         The name of the image file representing the projectile.
     * @param imageHeight       The height of the image.
     * @param initialXPos       The initial X position of the projectile.
     * @param initialYPos       The initial Y position of the projectile.
     * @param horizontalVelocity The horizontal velocity of the projectile.
     */
    public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos, double horizontalVelocity) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.horizontalVelocity = horizontalVelocity;
    }

    /**
     * Updates the state of the projectile by moving it horizontally based on its velocity.
     */
    @Override
    public void updateActor() {
        moveHorizontally(horizontalVelocity);
    }

    /**
     * Handles the projectile taking damage, which results in calling destroy method and its destruction.
     */
    @Override
    public void takeDamage() {
        this.destroy();
    }
}