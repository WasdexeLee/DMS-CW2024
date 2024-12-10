package com.example.demo.audio.services;

import com.example.demo.audio.background.BackgroundAudio;
import com.example.demo.utils.EnumUtil.BackgroundAudioType;
import com.example.demo.utils.EnumUtil.EffectAudioType;

public class AudioManager {

    private static AudioManager instance;
    private final AudioFactory audioFactory;

    private BackgroundAudio currentBackgroundAudio;

    private boolean hasSound;

    private AudioManager() {
        this.audioFactory = new AudioFactory();   
        this.hasSound = true;

        currentBackgroundAudio = audioFactory.createBackgroundAudio(BackgroundAudioType.MENU);
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }

        return instance;
    }

    public void changeBackgroundAudio(BackgroundAudioType backgroundAudioType) {
        pauseBackgroundAudio();
        currentBackgroundAudio.destroy();

        currentBackgroundAudio = audioFactory.createBackgroundAudio(backgroundAudioType);

        if (!hasSound)
            pauseBackgroundAudio();
    }

    public void playBackgroundAudio() { 
        currentBackgroundAudio.playAudio();
    }

    public void pauseBackgroundAudio() { 
        currentBackgroundAudio.pauseAudio();
    }
 
    public void fireEffectAudio(EffectAudioType effectAudioType) { 
        if (hasSound)
            audioFactory.fireEffectAudio(effectAudioType);
    }

    public void setHasSound(boolean value){
        hasSound = value;
    }

    public boolean getHasSound() {
        return hasSound;
    }
}