package com.example.demo.actors.user;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.projectile.UserProjectile;

public class UserPlane extends FighterPlane {

    private static final String IMAGE_NAME = "userplane.png";
    private static final int IMAGE_HEIGHT = 39;

    private static final double Y_UPPER_BOUND = 0;
    private static final double Y_LOWER_BOUND = 685.0;
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final int VERTICAL_VELOCITY = 8;

    private static final int PROJECTILE_X_POSITION = 110;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

    private int velocityMultiplier;

    public UserPlane(int initialHealth) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        velocityMultiplier = 0;
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
    }

    public void moveUp() {
        velocityMultiplier = -1;
    }

    public void moveDown() {
        velocityMultiplier = 1;
    }

    public void stop() {
        velocityMultiplier = 0;
    }

    private void updatePosition() {
        if (isMoving()) {
            double initialTranslateY = getTranslateY();
            this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
            double newPosition = getLayoutY() + getTranslateY();
            if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
                this.setTranslateY(initialTranslateY);
            }
        }
    }

    private boolean isMoving() {
        return velocityMultiplier != 0;
    }
}