package com.example.demo.core;

import javafx.animation.Timeline;

public class G {
    private static final int TARGET_FPS = 60;

    private Timeline timeline;

    public G() {
        // this.timeline = new Timeline(new KeyFrame(FRAME_DURATION, e -> game.update()));
    }

    public void stop() {
        timeline.stop();
    }
}