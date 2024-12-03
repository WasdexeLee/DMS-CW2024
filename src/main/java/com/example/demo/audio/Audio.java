package com.example.demo.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Audio {

    protected MediaPlayer audioPlayer;

    public Audio(String audioPath) {
        this.audioPlayer = new MediaPlayer(new Media(getClass().getResource(audioPath).toExternalForm()));
    }

    public void destroy() {
        audioPlayer = null;
    }
}