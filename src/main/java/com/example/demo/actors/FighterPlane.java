package com.example.demo.actors;

/**
 * Abstract class representing a fighter plane in the game.
 * Fighter planes are active actors that can take damage, be destroyed, and fire
 * projectiles.
 * This class extends {@link ActiveActorDestructible} to inherit active actor
 * and destructible behavior.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public abstract class FighterPlane extends ActiveActorDestructible {

    /** The current health of the fighter plane. */
    private int health;

    /**
     * Constructs a FighterPlane with the specified image, height, initial position,
     * and health.
     * 
     * @param imageName The name of the image file to be used for the fighter plane.
     * @param imageHeight The height to which the image should be scaled.
     * @param initialXPos The initial X position of the fighter plane.
     * @param initialYPos The initial Y position of the fighter plane.
     * @param health The initial health of the fighter plane.
     */
    public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.health = health;
    }

    /**
     * Updates the state of the fighter plane.
     * This method must be implemented by subclasses to define specific behavior.
     */
    @Override
    public abstract void updateActor();

    /**
     * Fires a projectile.
     * This method must be implemented by subclasses to define specific projectile
     * behavior.
     * 
     * @return The projectile fired by the fighter plane.
     */
    public abstract ActiveActorDestructible fireProjectile();

    /**
     * Applies damage to the fighter plane.
     * Reduces the health by 1 and destroys the fighter plane if health reaches zero.
     */
    @Override
    public void takeDamage() {
        health--;
        if (healthAtZero()) {
            this.destroy();
        }
    }

    /**
     * Retrieves the current health of the fighter plane.
     * 
     * @return The current health of the fighter plane.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Calculates the X position for a projectile based on the fighter plane's
     * position and an offset.
     * 
     * @param xPositionOffset The offset to apply to the X position.
     * @return The calculated X position for the projectile.
     */
    protected double getProjectileXPosition(double xPositionOffset) {
        return getLayoutX() + getTranslateX() + xPositionOffset;
    }

    /**
     * Calculates the Y position for a projectile based on the fighter plane's
     * position and an offset.
     * 
     * @param yPositionOffset The offset to apply to the Y position.
     * @return The calculated Y position for the projectile.
     */
    protected double getProjectileYPosition(double yPositionOffset) {
        return getLayoutY() + getTranslateY() + yPositionOffset;
    }

    /**
     * Checks if the fighter plane's health is at zero.
     * 
     * @return True if the health is zero, false otherwise.
     */
    private boolean healthAtZero() {
        return health == 0;
    }
}