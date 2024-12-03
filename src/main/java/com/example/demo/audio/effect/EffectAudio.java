package com.example.demo.audio.effect;

import com.example.demo.audio.Audio;

public class EffectAudio extends Audio {

    public EffectAudio(String audioPath) {
        super(audioPath);

        audioPlayer.setVolume(0.7);
        audioPlayer.setOnEndOfMedia(audioPlayer::dispose);
        audioPlayer.play();
    }
}