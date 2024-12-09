package com.example.demo.actors.props;

import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShieldImageTest extends ApplicationTest {

    private ShieldImage shieldImage;

    @Override
    public void start(Stage stage) throws Exception {
        this.shieldImage = new ShieldImage(100, 200);
    }

    // Test logic: Ensure the shield image is correctly initialized with the expected properties.
    @Test
    public void testInitialization() {
        assertNotNull(shieldImage.getImage());
        assertEquals(200, shieldImage.getFitHeight());
        assertTrue(shieldImage.isPreserveRatio());
        assertFalse(shieldImage.isVisible());
    }

    // Test logic: Ensure the shield image becomes visible when the showShield method is called.
    @Test
    public void testShowShield() throws InterruptedException {
        shieldImage.showShield();

        assertTrue(shieldImage.isVisible());
    }

    // Test logic: Ensure the shield image becomes invisible when the hideShield method is called.
    @Test
    public void testHideShield() throws InterruptedException {
        shieldImage.showShield();
        shieldImage.hideShield();

        assertFalse(shieldImage.isVisible());
    }

    // Test logic: Ensure the shield image correctly moves vertically by the specified amount.
    @Test
    public void testMoveVertically() throws InterruptedException {
        double initialTranslateY = shieldImage.getTranslateY();
        shieldImage.moveVertically(50);

        assertEquals(initialTranslateY + 50, shieldImage.getTranslateY());
    }
}