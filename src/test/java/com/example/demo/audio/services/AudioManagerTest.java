package com.example.demo.audio.services;

import com.example.demo.audio.background.BackgroundAudio;
import com.example.demo.utils.EnumUtil.BackgroundAudioType;
import com.example.demo.utils.EnumUtil.EffectAudioType;

import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AudioManagerTest extends ApplicationTest {

    private AudioManager audioManager;

    @Override
    public void start(Stage stage) throws Exception {
        audioManager = AudioManager.getInstance();
    }

    // Test logic: Ensure the AudioManager is a singleton and correctly initializes
    // with the default background audio.
    @Test
    @Order(1)
    public void testSingletonInitialization() {
        AudioManager instance1 = AudioManager.getInstance();
        AudioManager instance2 = AudioManager.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    @Order(2)
    public void testInitialization() {
        assertNotNull(getPrivateField(audioManager, "currentBackgroundAudio"));
        assertEquals(BackgroundAudioType.MENU,
                getBackgroundAudioType((BackgroundAudio) getPrivateField(audioManager, "currentBackgroundAudio")));
    }

    // Test logic: Ensure the AudioManager correctly changes the background audio.
    @Test
    public void testChangeBackgroundAudioLevel() {
        audioManager.changeBackgroundAudio(BackgroundAudioType.LEVEL);

        assertNotNull(getPrivateField(audioManager, "currentBackgroundAudio"));
        assertEquals(BackgroundAudioType.LEVEL,
                getBackgroundAudioType(((BackgroundAudio) getPrivateField(audioManager, "currentBackgroundAudio"))));
    }

    @Test
    public void testChangeBackgroundAudioLoseScene() {
        audioManager.changeBackgroundAudio(BackgroundAudioType.LEVEL);
        audioManager.changeBackgroundAudio(BackgroundAudioType.LOSESCENE);

        assertNotNull(getPrivateField(audioManager, "currentBackgroundAudio"));
        assertEquals(BackgroundAudioType.LOSESCENE,
                getBackgroundAudioType(((BackgroundAudio) getPrivateField(audioManager, "currentBackgroundAudio"))));
    }

    // Test logic: Ensure the AudioManager correctly fires effect audio when sound
    // is enabled.
    @Test
    public void testFireEffectAudioWithSoundEnabled() {
        audioManager.setHasSound(true);

        assertDoesNotThrow(() -> audioManager.fireEffectAudio(EffectAudioType.USERFIRE));
    }

    // Test logic: Ensure the AudioManager does not fire effect audio when sound is
    // disabled.
    @Test
    public void testFireEffectAudioWithSoundDisabled() {
        audioManager.setHasSound(false);

        assertDoesNotThrow(() -> audioManager.fireEffectAudio(EffectAudioType.USERFIRE));
    }

    // Helper method to determine the background audio type based on the media
    // source.
    private BackgroundAudioType getBackgroundAudioType(BackgroundAudio backgroundAudio) {
        String source = ((MediaPlayer) getPrivateField(backgroundAudio, "audioPlayer")).getMedia().getSource();

        if (source.contains("menu.mp3")) {
            return BackgroundAudioType.MENU;
        } else if (source.contains("level.mp3")) {
            return BackgroundAudioType.LEVEL;
        } else if (source.contains("lose_scene.mp3")) {
            return BackgroundAudioType.LOSESCENE;
        } else if (source.contains("win_scene.mp3")) {
            return BackgroundAudioType.WINSCENE;
        } else {
            throw new IllegalArgumentException("Unknown background audio type");
        }
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
}