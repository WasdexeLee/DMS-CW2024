package com.example.demo.audio.services;

import com.example.demo.audio.background.BackgroundAudio;
import com.example.demo.utils.EnumUtil.BackgroundAudioType;
import com.example.demo.utils.EnumUtil.EffectAudioType;

import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AudioFactoryTest extends ApplicationTest {

    private AudioFactory audioFactory;

    @Override
    public void start(Stage stage) throws Exception {
        audioFactory = new AudioFactory();
    }

    // Test logic: Ensure the AudioFactory creates the correct BackgroundAudio for
    // each type.
    @Test
    public void testCreateBackgroundMenuAudio() {
        BackgroundAudio menuAudio = audioFactory.createBackgroundAudio(BackgroundAudioType.MENU);

        assertNotNull(menuAudio);
        assertEquals(formatURL("/com/example/demo/audio/background/menu.mp3"),
                ((MediaPlayer) getPrivateField(menuAudio, "audioPlayer")).getMedia().getSource());
    }

    @Test
    public void testCreateBackgroundLevelAudio() {
        BackgroundAudio levelAudio = audioFactory.createBackgroundAudio(BackgroundAudioType.LEVEL);

        assertNotNull(levelAudio);
        assertEquals(formatURL("/com/example/demo/audio/background/level.mp3"),
                ((MediaPlayer) getPrivateField(levelAudio, "audioPlayer")).getMedia().getSource());
    }

    @Test
    public void testCreateBackgroundLoseSceneAudio() {
        BackgroundAudio loseSceneAudio = audioFactory.createBackgroundAudio(BackgroundAudioType.LOSESCENE);

        assertNotNull(loseSceneAudio);
        assertEquals(formatURL("/com/example/demo/audio/background/lose_scene.mp3"),
                ((MediaPlayer) getPrivateField(loseSceneAudio, "audioPlayer")).getMedia().getSource());
    }

    @Test
    public void testCreateBackgroundWinSceneAudio() {
        BackgroundAudio winSceneAudio = audioFactory.createBackgroundAudio(BackgroundAudioType.WINSCENE);

        assertNotNull(winSceneAudio);
        assertEquals(formatURL("/com/example/demo/audio/background/win_scene.mp3"),
                ((MediaPlayer) getPrivateField(winSceneAudio, "audioPlayer")).getMedia().getSource());

    }

    // Test logic: Ensure the AudioFactory fires the correct EffectAudio for each
    // type.
    @Test
    public void testFireEffectAudioUserFire() {
        assertDoesNotThrow(() -> audioFactory.fireEffectAudio(EffectAudioType.USERFIRE));
    }

    @Test
    public void testFireEffectAudioEnemyFire() {
        assertDoesNotThrow(() -> audioFactory.fireEffectAudio(EffectAudioType.ENEMYFIRE));
    }

    @Test
    public void testFireEffectAudioKill() {
        assertDoesNotThrow(() -> audioFactory.fireEffectAudio(EffectAudioType.KILL));
    }

    @Test
    public void testFireEffectAudioDamage() {
        assertDoesNotThrow(() -> audioFactory.fireEffectAudio(EffectAudioType.DAMAGE));
    }

    @Test
    public void testFireEffectAudioClick() {
        assertDoesNotThrow(() -> audioFactory.fireEffectAudio(EffectAudioType.CLICK));
    }

    @Test
    public void testFireEffectAudioPause() {
        assertDoesNotThrow(() -> audioFactory.fireEffectAudio(EffectAudioType.PAUSE));
    }

    @Test
    public void testFireEffectAudioTransition() {
        assertDoesNotThrow(() -> audioFactory.fireEffectAudio(EffectAudioType.TRANSITION));
    }

    @Test
    public void testFireEffectAudioGameOver() {
        assertDoesNotThrow(() -> audioFactory.fireEffectAudio(EffectAudioType.GAME_OVER));
    }

    // Helper method to access the private field for testing
    private Object getPrivateField(Object object, String fieldName) {
        Class<?> currentClass = object.getClass();

        while (currentClass != null) {
            try {
                // Attempt to find the field in the current class
                java.lang.reflect.Field field = currentClass.getDeclaredField(fieldName);
                field.setAccessible(true);

                return field.get(object);
            } catch (NoSuchFieldException e) {
                // Move up to the superclass if the field is not in the current class
                currentClass = currentClass.getSuperclass();
            } catch (Exception e) {
                throw new RuntimeException("Failed to access field", e);
            }
        }
        throw new RuntimeException("Field not found in class hierarchy: " + fieldName);
    }

    private String formatURL(String audioPath) {
        return getClass().getResource(audioPath).toExternalForm();
    }
}