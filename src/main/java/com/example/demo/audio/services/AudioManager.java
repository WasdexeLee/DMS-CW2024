package com.example.demo.audio.services;

import com.example.demo.audio.background.BackgroundAudio;
import com.example.demo.utils.EnumUtil.BackgroundAudioType;
import com.example.demo.utils.EnumUtil.EffectAudioType;

/**
 * Manages the audio playback in the game, including background music and sound effects.
 * This class provides methods to control the playback of background audio and sound effects.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class AudioManager {

    /** The singleton instance of the AudioManager. */
    private static AudioManager instance;

    /** The factory responsible for creating audio instances. */
    private final AudioFactory audioFactory;

    /** The current background audio being played. */
    private BackgroundAudio currentBackgroundAudio;

    /** Indicates whether sound is enabled in the game. */
    private boolean hasSound;

    /**
     * Private constructor to enforce the singleton pattern.
     * Initializes the audio factory and starts playing the default background audio.
     */
    private AudioManager() {
        this.audioFactory = new AudioFactory();   
        this.hasSound = true;

        // Start with the default background audio (MENU)
        currentBackgroundAudio = audioFactory.createBackgroundAudio(BackgroundAudioType.MENU);
    }

    /**
     * Returns the singleton instance of the AudioManager.
     * 
     * @return The singleton instance of the {@link AudioManager}.
     */
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }

        return instance;
    }

    /**
     * Changes the background audio to the specified type.
     * 
     * @param backgroundAudioType The type of background audio to play.
     */
    public void changeBackgroundAudio(BackgroundAudioType backgroundAudioType) {
        pauseBackgroundAudio();
        currentBackgroundAudio.destroy();

        currentBackgroundAudio = audioFactory.createBackgroundAudio(backgroundAudioType);

        if (!hasSound) {
            pauseBackgroundAudio();
        }
    }

    /**
     * Plays the current background audio.
     */
    public void playBackgroundAudio() { 
        currentBackgroundAudio.playAudio();
    }

    /**
     * Pauses the current background audio.
     */
    public void pauseBackgroundAudio() { 
        currentBackgroundAudio.pauseAudio();
    }
 
    /**
     * Plays a sound effect of the specified type.
     * 
     * @param effectAudioType The type of sound effect to play.
     */
    public void fireEffectAudio(EffectAudioType effectAudioType) { 
        if (hasSound) {
            audioFactory.fireEffectAudio(effectAudioType);
        }
    }

    /**
     * Sets whether sound is enabled in the game.
     * 
     * @param value True if sound is enabled, false otherwise.
     */
    public void setHasSound(boolean value){
        hasSound = value;
    }

    /**
     * Checks and gets whether sound is enabled in the game.
     * 
     * @return True if sound is enabled, false otherwise.
     */
    public boolean getHasSound() {
        return hasSound;
    }
}