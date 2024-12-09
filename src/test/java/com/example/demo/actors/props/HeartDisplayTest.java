package com.example.demo.actors.props;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeartDisplayTest extends ApplicationTest {

    private HeartDisplay heartDisplay;

    @Override
    public void start(Stage stage) throws Exception {
        this.heartDisplay = new HeartDisplay(100, 200, 3);
    }

    // Test logic: Ensure the heart display is correctly initialized with the expected number of hearts.
    @Test
    public void testInitailization() {
        assertNotNull(heartDisplay.getContainer());
        assertEquals(3, heartDisplay.getContainer().getChildren().size());
    }

    // Test logic: Ensure each heart in the display has the correct image and style properties.
    @Test
    public void testNumberOfHeartsDisplayStyle() {
        for (int i = 0; i < 3; i++) {
            ImageView heart = (ImageView) heartDisplay.getContainer().getChildren().get(i);

            assertNotNull(heart.getImage());
            assertEquals(50, heart.getFitHeight());
            assertTrue(heart.isPreserveRatio());
        }
    }

    // Test logic: Ensure a heart is correctly removed from the display.
    @Test
    public void testRemoveHeart() {
        heartDisplay.removeHeart();

        assertEquals(2, heartDisplay.getContainer().getChildren().size());
    }

    // Test logic: Ensure no hearts are removed when the display is already empty.
    @Test
    public void testRemoveHeartWhenEmpty() {
        // Remove all hearts
        while (!heartDisplay.getContainer().getChildren().isEmpty()) {
            heartDisplay.removeHeart();
        }

        // Attempt to remove another heart
        heartDisplay.removeHeart();
        assertEquals(0, heartDisplay.getContainer().getChildren().size());
    }
}