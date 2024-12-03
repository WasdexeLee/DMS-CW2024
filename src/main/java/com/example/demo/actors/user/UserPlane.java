package com.example.demo.actors.user;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.projectile.UserProjectile;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.core.GameLoop;
import com.example.demo.utils.EnumUtil.EffectAudioType;

public class UserPlane extends FighterPlane {

    private static final String IMAGE_NAME = "userplane.png";
    private static final int IMAGE_HEIGHT = 39;

    private static final double Y_UPPER_BOUND = 0;
    private static final double Y_LOWER_BOUND = 685;
    private static final double X_LEFT_BOUND = 0;
    private static final double X_RIGHT_BOUND = 400;
    private static final double INITIAL_X_POSITION = 5;
    private static final double INITIAL_Y_POSITION = 300;
    private static final int VERTICAL_VELOCITY = (int) Math.ceil(230.0 / GameLoop.getInstance(null).get_TARGET_FPS());

    private static final int PROJECTILE_X_POSITION = 110;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

    private int xVelocityMultiplier;
    private int yVelocityMultiplier;
    
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;

    private boolean isFiring;

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

    @Override
    public void updateActor() {
        updatePosition();
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        AudioManager.getInstance().fireEffectAudio(EffectAudioType.USERFIRE);
        return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
    }

    public void moveLeft() {
        leftPressed = true;
        xVelocityMultiplier = -1;
    }

    public void moveRight() {
        rightPressed = true;
        xVelocityMultiplier = 1;
    }

    public void moveUp() {
        upPressed = true;
        yVelocityMultiplier = -1;
    }

    public void moveDown() {
        downPressed = true;
        yVelocityMultiplier = 1;
    }

    public void stopLeft() {
        leftPressed = false;

        if (rightPressed)
            xVelocityMultiplier = 1;
        else
            xVelocityMultiplier = 0;
    }

    public void stopRight() {
        rightPressed = false;

        if (leftPressed)
            xVelocityMultiplier = -1;
        else
            xVelocityMultiplier = 0;
    }

    public void stopUp() {
        upPressed = false;

        if (downPressed)
            yVelocityMultiplier = 1;
        else
            yVelocityMultiplier = 0;
    
    }

    public void stopDown() {
        downPressed = false;

        if (upPressed)
            yVelocityMultiplier = -1;
        else
            yVelocityMultiplier = 0;
    }

    public void setIsFiring(boolean isFiring) { 
        this.isFiring = isFiring;
    }

    public boolean getIsFiring() { 
        return isFiring;
    }

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

    private boolean isMovingX() {
        return xVelocityMultiplier != 0;
    }

    private boolean isMovingY() {
        return yVelocityMultiplier != 0;
    }
}