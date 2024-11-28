package com.example.demo.core;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Manages the game loop and updates the game state at a fixed frame rate.
 * 
 * This class is a singleton and provides methods to start, stop, and retrieve the frame duration
 * of the game loop. The game loop is responsible for updating the game state at a fixed frame rate.
 * 
 * @author Your Name
 * @version 1.0
 */
public class GameLoop {

    /** The target frames per second for the game loop. */
    // private static final int TARGET_FPS = 60;
    private static final int TARGET_FPS = 360;

    /** The duration of each frame in milliseconds. */
    private static final Duration FRAME_DURATION = Duration.millis(1000.0 / TARGET_FPS);

    /** The singleton instance of the GameLoop. */
    private static GameLoop instance;

    /** The game instance associated with this game loop. */
    private final Game game;

    /** The keyframe used in the timeline. */
    private KeyFrame keyframe;
    
    /** The timeline that manages the game loop. */
    private Timeline timeline;

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the keyframe and timeline for the game loop.
     * 
     * @param game The game instance associated with this game loop.
     */
    private GameLoop(Game game) {
        this.game = game;

        this.keyframe = new KeyFrame(FRAME_DURATION, e -> this.game.update());
        this.timeline = new Timeline(this.keyframe);
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Returns the singleton instance of the GameLoop.
     * 
     * @param game The game instance associated with this game loop.
     * @return The singleton instance of the GameLoop.
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
     * Returns the duration of each frame in milliseconds.
     * 
     * @return The duration of each frame in milliseconds.
     */
    public double get_FRAME_DURATION_Double() {
        return FRAME_DURATION.toMillis();
    }
}