package com.example.demo.actors.enemy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.projectile.BossProjectile;
import com.example.demo.actors.props.ShieldImage;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.core.GameLoop;
import com.example.demo.scenes.levels.services.LevelView;
import com.example.demo.utils.EnumUtil.EffectAudioType;

/**
 * Represents the boss enemy in the game.
 * The boss is a fighter plane with specific behaviors, such as moving in a
 * pattern, firing projectiles, and having a shield.
 * This class extends {@link FighterPlane} to inherit fighter plane behavior.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class Boss extends FighterPlane {

    /** The name of the image file used for the boss. */
    private static final String IMAGE_NAME = "bossplane.png";

    /** The height to which the boss image should be scaled. */
    private static final int IMAGE_HEIGHT = 55;

    /** The initial X position of the boss. */
    private static final double INITIAL_X_POSITION = 1000.0;

    /** The initial Y position of the boss. */
    private static final double INITIAL_Y_POSITION = 400;

    /** The upper bound of the Y position for the boss. */
    private static final int Y_POSITION_UPPER_BOUND = 1;

    /** The lower bound of the Y position for the boss. */
    private static final int Y_POSITION_LOWER_BOUND = 580;

    /** The initial X position of the shield image. */
    private static final int SHIELD_X_POSITION = 910;

    /** The initial Y position of the shield image. */
    private static final int SHIELD_Y_POSITION = 350;

    /** The Y position offset for the boss's projectiles. */
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;

    /** The fire rate of the boss, calculated based on the target FPS. */
    private static final double BOSS_FIRE_RATE = 1.35 / GameLoop.getInstance(null).get_TARGET_FPS();

    /** The probability of the boss activating its shield, calculated based on the target FPS. */
    private static final double BOSS_SHIELD_PROBABILITY = .095 / GameLoop.getInstance(null).get_TARGET_FPS();

    /** The initial health of the boss. */
    private static final int HEALTH = 140;

    /** The frequency of moves per cycle. */
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

    /** Constant representing zero. */
    private static final int ZERO = 0;

    /** The vertical velocity of the boss, calculated based on the target FPS. */
    private static final double VERTICAL_VELOCITY = Math.ceil(150.0 / GameLoop.getInstance(null).get_TARGET_FPS());

    /** The maximum number of frames the boss can move in the same direction. */
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 1 * GameLoop.getInstance(null).get_TARGET_FPS();

    /** The maximum number of frames the boss can have its shield activated. */
    private static final int MAX_FRAMES_WITH_SHIELD = 3 * GameLoop.getInstance(null).get_TARGET_FPS();

    private final List<Double> movePattern;
    private final LevelView levelView;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;
    private double shieldNextMove;
    private ShieldImage shieldImage;

    /**
     * Constructs a Boss with the specified level view.
     * 
     * @param levelView The level view where the boss health bar is displayed.
     */
    public Boss(LevelView levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);

        this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
        this.framesWithShieldActivated = 0;
        this.isShielded = false;
        this.shieldNextMove = 0;

        this.levelView = levelView;
        this.levelView.showHealthBarDisplay(HEALTH);

        this.movePattern = new ArrayList<>();
        this.consecutiveMovesInSameDirection = 0;
        this.indexOfCurrentMove = 0;

        initializeMovePattern();
    }

    /**
     * Updates the state of the boss, including its position, shield, and health bar
     * display.
     */
    @Override
    public void updateActor() {
        updatePosition();
        updateShield();
        updateShieldView();
        levelView.updateHealthBarDisplay(getHealth());
    }

    /**
     * Fires a projectile if the firing logic is true for boss to fire in the current frame.
     * 
     * @return The projectile fired by the boss, or null if no projectile is fired.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        if (bossFiresInCurrentFrame()) {
            AudioManager.getInstance().fireEffectAudio(EffectAudioType.ENEMYFIRE);
            return new BossProjectile(getProjectileInitialPosition());
        } else {
            return null;
        }
    }

    /**
     * Method to call to apply damage to boss.
     * Only applies damage to the boss if it is not shielded.
     */
    @Override
    public void takeDamage() {
        if (!isShielded) {
            super.takeDamage();
        }
    }

    /**
     * Retrieves the shield image instance associated with the boss.
     * 
     * @return The shield image.
     */
    public ShieldImage getShieldImage() {
        return shieldImage;
    }

    /**
     * Initializes the move pattern of the boss.
     */
    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add((double) ZERO);
        }
        Collections.shuffle(movePattern);
    }

    /**
     * Randomized logic determination if the boss fires in the current frame.
     * 
     * @return True if the boss fires, false otherwise.
     */
    private boolean bossFiresInCurrentFrame() {
        return Math.random() < BOSS_FIRE_RATE;
    }

    /**
     * Calculates the initial position of the boss's projectile.
     * 
     * @return The initial Y position of the projectile.
     */
    private double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
    }

    /**
     * Updates the position of the boss based on its move pattern.
     */
    private void updatePosition() {
        double initialTranslateY = getTranslateY();
        double nextMove = getNextMove();
        shieldNextMove = nextMove;

        moveVertically(nextMove);
        double currentPosition = getLayoutY() + getTranslateY();

        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
            shieldNextMove = 0;
        }
    }

    /**
     * Retrieves the next move from the boss's move pattern.
     * 
     * @return The next move.
     */
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

    /**
     * Updates the shield state of the boss.
     */
    private void updateShield() {
        if (isShielded) {
            framesWithShieldActivated++;
        } else if (shieldShouldBeActivated()) {
            activateShield();
        }

        if (shieldExhausted()) {
            deactivateShield();
        }
    }

    /**
     * Randomized logic to check if the shield should be activated in the current frame.
     * 
     * @return True if the shield should be activated, false otherwise.
     */
    private boolean shieldShouldBeActivated() {
        return Math.random() < BOSS_SHIELD_PROBABILITY;
    }

    /**
     * Checks if the shield has been exhausted.
     * 
     * @return True if the shield has been exhausted, false otherwise.
     */
    private boolean shieldExhausted() {
        return framesWithShieldActivated >= MAX_FRAMES_WITH_SHIELD;
    }

    /**
     * Activates the shield.
     */
    private void activateShield() {
        isShielded = true;
        shieldImage.showShield();
    }

    /**
     * Deactivates the shield.
     */
    private void deactivateShield() {
        isShielded = false;
        shieldImage.hideShield();
        framesWithShieldActivated = 0;
    }

    /**
     * Updates the position of the shield image.
     */
    private void updateShieldView() {
        shieldImage.moveVertically(shieldNextMove);
    }
}