package com.example.demo.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Represents an audio object in the game, providing functionality to manage audio playback.
 * This class is the base class for all audio-related classes, such as background and effect audio.
 *
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class Audio {

    /**
     * The media player responsible for playing the audio.
     */
    protected MediaPlayer audioPlayer;

    /**
     * Constructs an {@link Audio} object with the specified audio file path.
     *
     * @param audioPath The path to the audio file.
     */
    public Audio(String audioPath) {
        this.audioPlayer = new MediaPlayer(new Media(getClass().getResource(audioPath).toExternalForm()));
    }

    /**
     * Destroys the audio player by setting it to {@code null}.
     */
    public void destroy() {
        audioPlayer = null;
    }
}