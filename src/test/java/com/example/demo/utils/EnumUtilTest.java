package com.example.demo.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnumUtilTest {

    // Test logic: Ensure the State enum contains the correct values.
    @Test
    public void testStateEnum() {
        assertEquals(3, EnumUtil.State.values().length);
        assertEquals(EnumUtil.State.RUNNING, EnumUtil.State.valueOf("RUNNING"));
        assertEquals(EnumUtil.State.PAUSED, EnumUtil.State.valueOf("PAUSED"));
        assertEquals(EnumUtil.State.STOP, EnumUtil.State.valueOf("STOP"));
    }

    // Test logic: Ensure the SceneType enum contains the correct values.
    @Test
    public void testSceneTypeEnum() {
        assertEquals(6, EnumUtil.SceneType.values().length);
        assertEquals(EnumUtil.SceneType.MENU, EnumUtil.SceneType.valueOf("MENU"));
        assertEquals(EnumUtil.SceneType.LEVEL1, EnumUtil.SceneType.valueOf("LEVEL1"));
        assertEquals(EnumUtil.SceneType.LEVEL2, EnumUtil.SceneType.valueOf("LEVEL2"));
        assertEquals(EnumUtil.SceneType.LEVEL3, EnumUtil.SceneType.valueOf("LEVEL3"));
        assertEquals(EnumUtil.SceneType.LOSESCENE, EnumUtil.SceneType.valueOf("LOSESCENE"));
        assertEquals(EnumUtil.SceneType.WINSCENE, EnumUtil.SceneType.valueOf("WINSCENE"));
    }

    // Test logic: Ensure the BackgroundAudioType enum contains the correct values.
    @Test
    public void testBackgroundAudioTypeEnum() {
        assertEquals(4, EnumUtil.BackgroundAudioType.values().length);
        assertEquals(EnumUtil.BackgroundAudioType.MENU, EnumUtil.BackgroundAudioType.valueOf("MENU"));
        assertEquals(EnumUtil.BackgroundAudioType.LEVEL, EnumUtil.BackgroundAudioType.valueOf("LEVEL"));
        assertEquals(EnumUtil.BackgroundAudioType.LOSESCENE, EnumUtil.BackgroundAudioType.valueOf("LOSESCENE"));
        assertEquals(EnumUtil.BackgroundAudioType.WINSCENE, EnumUtil.BackgroundAudioType.valueOf("WINSCENE"));
    }

    // Test logic: Ensure the EffectAudioType enum contains the correct values.
    @Test
    public void testEffectAudioTypeEnum() {
        assertEquals(8, EnumUtil.EffectAudioType.values().length);
        assertEquals(EnumUtil.EffectAudioType.USERFIRE, EnumUtil.EffectAudioType.valueOf("USERFIRE"));
        assertEquals(EnumUtil.EffectAudioType.ENEMYFIRE, EnumUtil.EffectAudioType.valueOf("ENEMYFIRE"));
        assertEquals(EnumUtil.EffectAudioType.KILL, EnumUtil.EffectAudioType.valueOf("KILL"));
        assertEquals(EnumUtil.EffectAudioType.DAMAGE, EnumUtil.EffectAudioType.valueOf("DAMAGE"));
        assertEquals(EnumUtil.EffectAudioType.CLICK, EnumUtil.EffectAudioType.valueOf("CLICK"));
        assertEquals(EnumUtil.EffectAudioType.PAUSE, EnumUtil.EffectAudioType.valueOf("PAUSE"));
        assertEquals(EnumUtil.EffectAudioType.TRANSITION, EnumUtil.EffectAudioType.valueOf("TRANSITION"));
        assertEquals(EnumUtil.EffectAudioType.GAME_OVER, EnumUtil.EffectAudioType.valueOf("GAME_OVER"));
    }
}