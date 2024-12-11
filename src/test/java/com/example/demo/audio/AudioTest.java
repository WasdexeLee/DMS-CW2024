package com.example.demo.audio;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;

public class AudioTest extends ApplicationTest {

    private Audio audio;

    @Override
    public void start(Stage stage) throws Exception {
        audio = new Audio("/com/example/demo/audio/background/level.mp3");
    }

    // Test logic: Ensure the Audio is correctly initialized with the expected
    // MediaPlayer.
    @Test
    public void testInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                assertNotNull(audio.audioPlayer);
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }

    // Test logic: Ensure the Audio's MediaPlayer is correctly destroyed.
    @Test
    public void testDestroy() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                audio.destroy();

                assertNull(audio.audioPlayer);

            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }
}