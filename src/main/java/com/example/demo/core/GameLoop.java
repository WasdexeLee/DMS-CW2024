package com.example.demo.core;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameLoop {
    private static final int TARGET_FPS = 60;
    private static final Duration FRAME_DURATION = Duration.millis(1000.0 / TARGET_FPS);

    private static GameLoop instance;
    private final Game game;
    private KeyFrame keyframe;
    private Timeline timeline;

    private GameLoop(Game game) {
        this.game = game;
        // this.game.addGameStatePropChangeListener(this);

        this.keyframe = new KeyFrame(FRAME_DURATION, e -> this.game.update());
        this.timeline = new Timeline(this.keyframe);
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public static GameLoop getInstance(Game game) {
        if (instance == null) {
            instance = new GameLoop(game);
        }

        return instance;
    }

    public void start() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    public double get_FRAME_DURATION_Double() {
        return FRAME_DURATION.toMillis();
    }

    // @Override
    // public void propertyChange(PropertyChangeEvent event) {
    // // switch (event) {
    // // case value:

    // // break;

    // // default:
    // // break;
    // // }
    // }
}