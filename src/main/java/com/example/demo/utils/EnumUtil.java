package com.example.demo.utils;

public class EnumUtil {
    public enum State {
        RUNNING,
        PAUSED,
        STOP,
    }

    public enum SceneType {
        MENU,
        LEVEL1,
        LEVEL2,
        LEVEL3,
        LOSESCENE,
        WINSCENE,
    }

    public enum BackgroundAudioType {
        MENU,
        LEVEL,
        LOSESCENE,
        WINSCENE,
    }

    public enum EffectAudioType {
        USERFIRE,
        ENEMYFIRE,
        KILL,
        DAMAGE,
        CLICK,
        PAUSE,
        TRANSITION,
        GAME_OVER,
    }
}