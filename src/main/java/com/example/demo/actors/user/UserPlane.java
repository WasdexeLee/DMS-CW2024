package com.example.demo.actors.user;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.projectile.UserProjectile;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.core.GameLoop;
import com.example.demo.utils.EnumUtil.EffectAudioType;

/**
 * Represents the user's fighter plane in the game.
 * This class extends {@link FighterPlane} to inherit fighter plane behavior.
 * The user plane can move in all directions and fire projectiles.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class UserPlane extends FighterPlane {

    /** The name of the image file used for the user plane. */
    private static final String IMAGE_NAME = "plane/userplane.png";

    /** The height to which the user plane image should be scaled. */
    private static final int IMAGE_HEIGHT = 39;

    /** The upper bound of the Y position for the user plane. */
    private static final double Y_UPPER_BOUND = 0;

    /** The lower bound of the Y position for the user plane. */
    private static final double Y_LOWER_BOUND = 685;

    /** The left bound of the X position for the user plane. */
    private static final double X_LEFT_BOUND = 0;

    /** The right bound of the X position for the user plane. */
    private static final double X_RIGHT_BOUND = 400;

    /** The initial X position of the user plane. */
    private static final double INITIAL_X_POSITION = 5;

    /** The initial Y position of the user plane. */
    private static final double INITIAL_Y_POSITION = 300;

    /** The vertical velocity of the user plane, calculated based on the target FPS. */
    private static final int VERTICAL_VELOCITY = (int) Math.ceil(230.0 / GameLoop.getInstance(null).get_TARGET_FPS());

    /** The X position offset for the user's projectiles. */
    private static final int PROJECTILE_X_POSITION = 110;

    /** The Y position offset for the user's projectiles. */
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

    private int xVelocityMultiplier;
    private int yVelocityMultiplier;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean isFiring;

    /**
     * Constructs a UserPlane with the specified initial health.
     * 
     * @param initialHealth The initial health of the user plane.
     */
    public UserPlane(int initialHealth) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        this.xVelocityMultiplier = 0;
        this.yVelocityMultiplier = 0;

        this.leftPressed = false;
        this.rightPressed = false;
        this.upPressed = false;
        this.downPressed = false;

        this.isFiring = false;
    }

    /**
     * Updates the state of the user plane by updating its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Fires a projectile from the user plane.
     * 
     * @return The {@link UserProjectile} fired by the user plane.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        AudioManager.getInstance().fireEffectAudio(EffectAudioType.USERFIRE);
        return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
    }

    /**
     * Sets the user plane to move left.
     */
    public void moveLeft() {
        leftPressed = true;
        xVelocityMultiplier = -1;
    }

    /**
     * Sets the user plane to move right.
     */
    public void moveRight() {
        rightPressed = true;
        xVelocityMultiplier = 1;
    }

    /**
     * Sets the user plane to move up.
     */
    public void moveUp() {
        upPressed = true;
        yVelocityMultiplier = -1;
    }

    /**
     * Sets the user plane to move down.
     */
    public void moveDown() {
        downPressed = true;
        yVelocityMultiplier = 1;
    }

    /**
     * Stops the user plane from moving left.
     */
    public void stopLeft() {
        leftPressed = false;

        if (rightPressed)
            xVelocityMultiplier = 1;
        else
            xVelocityMultiplier = 0;
    }

    /**
     * Stops the user plane from moving right.
     */
    public void stopRight() {
        rightPressed = false;

        if (leftPressed)
            xVelocityMultiplier = -1;
        else
            xVelocityMultiplier = 0;
    }

    /**
     * Stops the user plane from moving up.
     */
    public void stopUp() {
        upPressed = false;

        if (downPressed)
            yVelocityMultiplier = 1;
        else
            yVelocityMultiplier = 0;
    }

    /**
     * Stops the user plane from moving down.
     */
    public void stopDown() {
        downPressed = false;

        if (upPressed)
            yVelocityMultiplier = -1;
        else
            yVelocityMultiplier = 0;
    }

    /**
     * Sets whether the user plane is firing.
     * 
     * @param isFiring True if the user plane is firing, false otherwise.
     */
    public void setIsFiring(boolean isFiring) { 
        this.isFiring = isFiring;
    }

    /**
     * Checks whether the user plane is firing.
     * 
     * @return True if the user plane is firing, false otherwise.
     */
    public boolean getIsFiring() { 
        return isFiring;
    }

    /**
     * Updates the position of the user plane based on its velocity.
     */
    private void updatePosition() {
        if (isMovingY()) {
            double initialTranslateY = getTranslateY();
            this.moveVertically(VERTICAL_VELOCITY * yVelocityMultiplier);
            double newPosition = getLayoutY() + getTranslateY();
            if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
                this.setTranslateY(initialTranslateY);
            }
        }

        if (isMovingX()){
            double initialTranslateX = getTranslateX();
            this.moveHorizontally(VERTICAL_VELOCITY * xVelocityMultiplier);
            double newPosition = getLayoutX() + getTranslateX();
            if (newPosition < X_LEFT_BOUND || newPosition > X_RIGHT_BOUND) {
                this.setTranslateX(initialTranslateX);
            }
        }
    }

    /**
     * Checks whether the user plane is moving horizontally.
     * 
     * @return True if the user plane is moving horizontally, false otherwise.
     */
    private boolean isMovingX() {
        return xVelocityMultiplier != 0;
    }

    /**
     * Checks whether the user plane is moving vertically.
     * 
     * @return True if the user plane is moving vertically, false otherwise.
     */
    private boolean isMovingY() {
        return yVelocityMultiplier != 0;
    }
}