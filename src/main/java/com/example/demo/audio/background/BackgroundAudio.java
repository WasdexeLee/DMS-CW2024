package com.example.demo.audio.background;

import com.example.demo.audio.Audio;
import javafx.scene.media.MediaPlayer;

/**
 * Represents background audio in the game, extending the {@link Audio} class.
 * This class provides functionality to play and pause background music,
 * with looping and volume settings.
 *
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class BackgroundAudio extends Audio {

    /**
     * Constructs a {@link BackgroundAudio} with the specified audio file path.
     * The audio is set to loop indefinitely and has a volume of 0.6.
     *
     * @param audioPath The path to the audio file.
     */
    public BackgroundAudio(String audioPath) {
        super(audioPath);
        audioPlayer.setVolume(0.6);
        audioPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
        playAudio();
    }

    /**
     * Plays the background audio.
     */
    public void playAudio() {
        audioPlayer.play();
    }

    /**
     * Pauses the background audio.
     */
    public void pauseAudio() {
        audioPlayer.pause();
    }
}