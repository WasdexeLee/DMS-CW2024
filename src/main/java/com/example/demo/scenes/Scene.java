package com.example.demo.scenes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Scene {
    private static final int TARGET_FPS = 60;
    private static final Duration FRAME_DURATION = Duration.millis(1000.0 / TARGET_FPS);

    private Timeline timeline;

    public Scene() {
        this.timeline = new Timeline(new KeyFrame(FRAME_DURATION, e -> game.update()));
    }

    public void stop() {
        timeline.stop();
    }
}