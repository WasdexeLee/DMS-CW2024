package com.example.demo.audio.background;

import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BackgroundAudioTest extends ApplicationTest {

    private BackgroundAudio backgroundAudio;

    @Override
    public void start(Stage stage) throws Exception {
        backgroundAudio = new BackgroundAudio("/com/example/demo/audio/background/level.mp3");
    }

    // Test logic: Ensure the BackgroundAudio is correctly initialized with the
    // expected properties.
    @Test
    public void testInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                assertNotNull(getPrivateField(backgroundAudio, "audioPlayer"));
                assertEquals(0.6, ((MediaPlayer) getPrivateField(backgroundAudio, "audioPlayer")).getVolume());
                assertEquals(MediaPlayer.INDEFINITE,
                        ((MediaPlayer) getPrivateField(backgroundAudio, "audioPlayer")).getCycleCount());
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