package com.example.demo.audio.effect;

import com.example.demo.audio.Audio;

/**
 * Represents an audio effect in the game.
 * This class extends {@link Audio} to inherit audio playback functionality.
 * Audio effects are short sounds that play in response to specific events, such as firing or taking damage.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class EffectAudio extends Audio {

    /**
     * Constructs an EffectAudio with the specified audio file path.
     * 
     * @param audioPath The path to the audio file.
     */
    public EffectAudio(String audioPath) {
        super(audioPath);

        // Set the volume of the audio effect
        audioPlayer.setVolume(0.7);

        // Dispose of the audio player when the audio ends
        audioPlayer.setOnEndOfMedia(audioPlayer::dispose);

        // Play the audio effect
        audioPlayer.play();
    }
}