package com.example.demo.actors;

import com.example.demo.utils.LoggerUtil;

/**
 * Represents an abstract class for active actors that are destructible.
 * This class extends {@code ActiveActor} and implements the {@code Destructible} interface.
 * It includes functionality for managing the destruction state of the actor.
 *
 * @author WasdexeLee (Lee Jia Zhe)
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

    /**
     * Indicates whether the actor has been destroyed.
     */
    private boolean isDestroyed;

    /**
     * Constructs an {@code ActiveActorDestructible} with the specified image details and initial position.
     * The actor is initially not destroyed.
     *
     * @param imageName    The name of the image file representing the actor.
     * @param imageHeight  The height of the image.
     * @param initialXPos  The initial X position of the actor.
     * @param initialYPos  The initial Y position of the actor.
     */
    public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        isDestroyed = false;
    }

    /**
     * Updates the state of the actor.
     * This method must be implemented by subclasses to define specific behavior.
     */
    @Override
    public abstract void updateActor();

    /**
     * Handles the actor taking damage.
     * This method must be implemented by subclasses to define specific behavior.
     */
    @Override
    public abstract void takeDamage();

    /**
     * Marks the actor as destroyed.
     */
    @Override
    public void destroy() {
        this.isDestroyed = true;
    }

    /**
     * Checks if the actor is destroyed.
     *
     * @return {@code true} if the actor is destroyed, {@code false} otherwise.
     */
    public boolean getIsDestroyed() {
        return isDestroyed;
    }
    
    /**
     * Finalizes the object and logs its garbage collection.
     *
     * @throws Throwable if an error occurs during finalization.
     */
    @Override
    protected void finalize() throws Throwable {
        LoggerUtil.logger.info(getClass().getName() + " class is garbage collected");
    }
}