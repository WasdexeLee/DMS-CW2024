package com.example.demo.core;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Manages the game loop, ensuring the game updates at a consistent frame rate.
 * This class uses JavaFX's {@link Timeline} and {@link KeyFrame} to achieve the
 * desired frame rate.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class GameLoop {

    /** The target frames per second (FPS) for the game loop. */
    private static final int TARGET_FPS = 144;

    /** The duration of each frame in milliseconds, calculated based on the target FPS. */
    private static final Duration FRAME_DURATION = Duration.millis(1000.0 / TARGET_FPS);

    /** The singleton instance of the GameLoop class. */
    private static GameLoop instance;

    /** The game instance that this game loop updates. */
    private final Game game;

    /** The keyframe used to define the frame duration and update action. */
    private KeyFrame keyframe;

    /** The timeline that manages the game loop. */
    private Timeline timeline;

    /**
     * Private constructor to enforce the singleton pattern.
     * Initializes the keyframe and timeline for the game loop.
     * 
     * @param game The game instance that this game loop updates.
     */
    private GameLoop(Game game) {
        this.game = game;

        this.keyframe = new KeyFrame(FRAME_DURATION, e -> this.game.update());
        this.timeline = new Timeline(this.keyframe);
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Returns the singleton instance of the GameLoop class.
     * 
     * @param game The game instance that this game loop updates.
     * @return The singleton instance of the GameLoop class.
     */
    public static GameLoop getInstance(Game game) {
        if (instance == null) {
            instance = new GameLoop(game);
        }

        return instance;
    }

    /**
     * Starts the game loop.
     */
    public void start() {
        timeline.play();
    }

    /**
     * Stops the game loop.
     */
    public void stop() {
        timeline.stop();
    }

    /**
     * Retrieves the target frames per second (FPS) for the game loop.
     * 
     * @return The target FPS.
     */
    public int get_TARGET_FPS() {
        return TARGET_FPS;
    }
}