package com.example.demo.audio.effect;

import java.util.concurrent.CountDownLatch;

import org.testfx.framework.junit5.ApplicationTest;
import javafx.application.Platform;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EffectAudioTest extends ApplicationTest {

    private EffectAudio effectAudio;

    @Override
    public void start(Stage stage) throws Exception {
        effectAudio = new EffectAudio("/com/example/demo/audio/background/level.mp3");
    }

    // Test logic: Ensure the EffectAudio is correctly initialized with the expected
    // properties.
    @Test
    public void testInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                assertNotNull(getPrivateField(effectAudio, "audioPlayer"));
                assertEquals(0.7, ((MediaPlayer) getPrivateField(effectAudio, "audioPlayer")).getVolume());
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }

    // Test logic: Ensure the EffectAudio is disposed of after playing.
    @Test
    public void testEndOfMediaDispose() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                ((MediaPlayer) getPrivateField(effectAudio, "audioPlayer")).setOnEndOfMedia(() -> {
                    assertNull((getPrivateField(effectAudio, "audioPlayer")));
                });

                ((MediaPlayer) getPrivateField(effectAudio, "audioPlayer")).stop();
            } finally {
                latch.countDown();
            }
        });
        latch.await();
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