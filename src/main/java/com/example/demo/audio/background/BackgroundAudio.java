package com.example.demo.audio.background;

import com.example.demo.audio.Audio;

import javafx.scene.media.MediaPlayer;

public class BackgroundAudio extends Audio {

    public BackgroundAudio(String audioPath) {
        super(audioPath);
            
        audioPlayer.setVolume(0.6); 
        audioPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
        playAudio();
    }

    public void playAudio() {
        audioPlayer.play();
    }

    public void pauseAudio() {
        audioPlayer.pause();
    }
}