package com.example.demo.audio.services;

import com.example.demo.audio.background.BackgroundAudio;
import com.example.demo.audio.effect.EffectAudio;
import com.example.demo.utils.EnumUtil.BackgroundAudioType;
import com.example.demo.utils.EnumUtil.EffectAudioType;

/**
 * A factory class responsible for creating different types of audio.
 * This includes background audio and effect audio.
 *
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class AudioFactory {

    /**
     * Creates a {@link BackgroundAudio} based on the specified {@link BackgroundAudioType}.
     *
     * @param backgroundAudioType The type of background audio to create.
     * @return A {@link BackgroundAudio} instance corresponding to the specified type.
     * @throws IllegalArgumentException If an unknown audio type is provided.
     */
    public BackgroundAudio createBackgroundAudio(BackgroundAudioType backgroundAudioType) {
        switch (backgroundAudioType) {
            case MENU:
                return new BackgroundAudio("/com/example/demo/audio/background/menu.mp3");
            case LEVEL:
                return new BackgroundAudio("/com/example/demo/audio/background/level.mp3");
            case LOSESCENE:
                return new BackgroundAudio("/com/example/demo/audio/background/lose_scene.mp3");
            case WINSCENE:
                return new BackgroundAudio("/com/example/demo/audio/background/win_scene.mp3");
            default:
                throw new IllegalArgumentException("Unknown audio type: " + backgroundAudioType);
        }
    }

    /**
     * Plays an {@link EffectAudio} based on the specified {@link EffectAudioType}.
     *
     * @param effectAudioType The type of effect audio to play.
     * @throws IllegalArgumentException If an unknown audio type is provided.
     */
    public void fireEffectAudio(EffectAudioType effectAudioType) {
        switch (effectAudioType) {
            case USERFIRE:
                new EffectAudio("/com/example/demo/audio/effect/user_fire.mp3");
                break;
            case ENEMYFIRE:
                new EffectAudio("/com/example/demo/audio/effect/enemy_fire.mp3");
                break;
            case KILL:
                new EffectAudio("/com/example/demo/audio/effect/kill.mp3");
                break;
            case DAMAGE:
                new EffectAudio("/com/example/demo/audio/effect/damage.mp3");
                break;
            case CLICK:
                new EffectAudio("/com/example/demo/audio/effect/click.mp3");
                break;
            case PAUSE:
                new EffectAudio("/com/example/demo/audio/effect/pause.mp3");
                break;
            case TRANSITION:
                new EffectAudio("/com/example/demo/audio/effect/transition.mp3");
                break;
            case GAME_OVER:
                new EffectAudio("/com/example/demo/audio/effect/game_over.mp3");
                break;
            default:
                throw new IllegalArgumentException("Unknown audio type: " + effectAudioType);
        }
    }
}