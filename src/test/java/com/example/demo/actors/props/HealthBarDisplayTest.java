package com.example.demo.actors.props;

import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HealthBarDisplayTest extends ApplicationTest {

    private HealthBarDisplay healthBarDisplay;

    @Override
    public void start(Stage stage) throws Exception {
        healthBarDisplay = new HealthBarDisplay(100);
    }

    // Test logic: Ensure the HealthBarDisplay is correctly initialized with the expected properties.
    @Test
    public void testInitialization() {
        ProgressBar healthBar = healthBarDisplay.getHealthBar();
        assertNotNull(healthBar);
        assertEquals(500, healthBar.getPrefWidth());
        assertEquals(25, healthBar.getPrefHeight());
        assertEquals(433, healthBar.getLayoutX());
        assertEquals(25, healthBar.getLayoutY());
        assertEquals(1.0, healthBar.getProgress());
        assertEquals("-fx-accent: red;", healthBar.getStyle());
    }

    // Test logic: Ensure the HealthBarDisplay updates the health bar correctly with the expected progress.
    @Test
    public void testUpdateHealthHigh() {
        ProgressBar healthBar = healthBarDisplay.getHealthBar();

        healthBarDisplay.updateHealth(70);
        assertEquals(0.7, healthBar.getProgress());
        assertEquals("-fx-accent: red;", healthBar.getStyle());

        healthBarDisplay.updateHealth(20);
        assertEquals(0.2, healthBar.getProgress());
        assertEquals("-fx-accent: yellow;", healthBar.getStyle());
    }

    @Test
    public void testUpdateHealthLow() {
        ProgressBar healthBar = healthBarDisplay.getHealthBar();

        healthBarDisplay.updateHealth(20);
        assertEquals(0.2, healthBar.getProgress());
        assertEquals("-fx-accent: yellow;", healthBar.getStyle());
    }
}