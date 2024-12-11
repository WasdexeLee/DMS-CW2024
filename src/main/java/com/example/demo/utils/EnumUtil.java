package com.example.demo.utils;

/**
 * Utility class containing enumerations for various game states, scene types,
 * background audio types, and effect audio types.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class EnumUtil {

    /**
     * Enumeration representing the possible states of the game.
     */
    public enum State {
        /** The game is currently running. */
        RUNNING,

        /** The game is paused. */
        PAUSED,

        /** The game has stopped. */
        STOP,
    }

    /**
     * Enumeration representing the different types of scenes in the game.
     */
    public enum SceneType {
        /** The main menu scene. */
        MENU,

        /** The first level scene. */
        LEVEL1,

        /** The second level scene. */
        LEVEL2,

        /** The third level scene. */
        LEVEL3,

        /** The game over scene when the player loses. */
        LOSESCENE,

        /** The game over scene when the player wins. */
        WINSCENE,
    }

    /**
     * Enumeration representing the different types of background audio in the game.
     */
    public enum BackgroundAudioType {
        /** Background audio for the main menu. */
        MENU,

        /** Background audio for the levels. */
        LEVEL,

        /** Background audio for the lose scene. */
        LOSESCENE,

        /** Background audio for the win scene. */
        WINSCENE,
    }

    /**
     * Enumeration representing the different types of effect audio in the game.
     */
    public enum EffectAudioType {
        /** Audio effect when the user fires. */
        USERFIRE,

        /** Audio effect when the enemy fires. */
        ENEMYFIRE,

        /** Audio effect when an entity is killed. */
        KILL,

        /** Audio effect when an entity takes damage. */
        DAMAGE,

        /** Audio effect for a button click. */
        CLICK,

        /** Audio effect for a pause button click. */
        PAUSE,

        /** Audio effect for scene transitions. */
        TRANSITION,

        /** Audio effect for transition to the game over screen. */
        GAME_OVER,
    }
}