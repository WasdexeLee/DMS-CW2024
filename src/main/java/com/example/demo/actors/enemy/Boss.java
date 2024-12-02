package com.example.demo.actors.enemy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.projectile.BossProjectile;
import com.example.demo.actors.props.ShieldImage;
import com.example.demo.core.GameLoop;

public class Boss extends FighterPlane {

    private static final String IMAGE_NAME = "bossplane.png";
    private static final int IMAGE_HEIGHT = 55;

    private static final double INITIAL_X_POSITION = 1000.0;
    private static final double INITIAL_Y_POSITION = 400;
    private static final int Y_POSITION_UPPER_BOUND = 1;
    private static final int Y_POSITION_LOWER_BOUND = 580;
    private static final int SHIELD_X_POSITION = 910;
    private static final int SHIELD_Y_POSITION = 350;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;

    private static final double BOSS_FIRE_RATE = .79 / GameLoop.getInstance(null).get_TARGET_FPS();
    private static final double BOSS_SHIELD_PROBABILITY = .04 / GameLoop.getInstance(null).get_TARGET_FPS();

    private static final double VERTICAL_VELOCITY = Math.ceil(150.0 / GameLoop.getInstance(null).get_TARGET_FPS());
    // private static final int HEALTH = 100;
    private static final int HEALTH = 5;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 1 * GameLoop.getInstance(null).get_TARGET_FPS();
    private static final int MAX_FRAMES_WITH_SHIELD = 25 * GameLoop.getInstance(null).get_TARGET_FPS();

    private final List<Double> movePattern;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;
    private double shieldNextMove;
    private ShieldImage shieldImage;

    public Boss() {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        framesWithShieldActivated = 0;
        isShielded = false;
        shieldNextMove = 0;
        this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
        initializeMovePattern();
    }

    @Override
    public void updateActor() {
        updatePosition();
        updateShield();
        updateShieldView();
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
    }

    @Override
    public void takeDamage() {
        if (!isShielded) {
            super.takeDamage();
        }
    }

    public ShieldImage getShieldImage() {
        return shieldImage;
    }

    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add((double) ZERO);
        }
        Collections.shuffle(movePattern);
    }

    private boolean bossFiresInCurrentFrame() {
        return Math.random() < BOSS_FIRE_RATE;
    }

    private double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
    }

    private void updatePosition() {
        double initialTranslateY = getTranslateY();
        double nextMove = getNextMove();
        moveVertically(nextMove);
        shieldNextMove = nextMove;
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
            shieldNextMove = 0;
        }
    }

    private double getNextMove() {
        double currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;
        if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }

    private void updateShield() {
        if (isShielded)
            framesWithShieldActivated++;

        else if (shieldShouldBeActivated())
            activateShield();

        if (shieldExhausted())
            deactivateShield();
    }

    private boolean shieldShouldBeActivated() {
        return Math.random() < BOSS_SHIELD_PROBABILITY;
    }

    private boolean shieldExhausted() {
        return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
    }

    private void activateShield() {
        isShielded = true;
        shieldImage.showShield();
    }

    private void deactivateShield() {
        isShielded = false;
        shieldImage.hideShield();
        framesWithShieldActivated = 0;
    }

    private void updateShieldView() {
        shieldImage.moveVertically(shieldNextMove);
    }
}