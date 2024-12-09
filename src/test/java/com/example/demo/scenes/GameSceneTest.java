package com.example.demo.scenes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameSceneTest extends ApplicationTest {

    private Stage stage;
    private GameScene gameScene;

    // Set up the JavaFX stage with the specified title, dimensions, and properties
    @Override
    public void start(Stage stage) throws Exception {
        int SCREEN_WIDTH = 1366;
        int SCREEN_HEIGHT = 762;
        String TITLE = "Sky Battle";

        this.stage = stage;
        this.stage.setTitle(TITLE);
        this.stage.setResizable(false);
        this.stage.setHeight(SCREEN_HEIGHT);
        this.stage.setWidth(SCREEN_WIDTH);

        this.gameScene = new ConcreteGameScene("/com/example/demo/images/menu/menu_background.jpeg", SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    // Test logic: Ensure the GameScene initializes with the correct background and
    // dimensions.
    @Test
    public void testInitialization() {
        assertNotNull(gameScene.getScene());
        assertNotNull(gameScene.getBackground());
        assertEquals(1366, gameScene.getScreenWidth());
        assertEquals(762, gameScene.getScreenHeight());
    }

    // Test logic: Ensure the GameScene correctly adds and removes
    // PropertyChangeListeners.
    @Test
    public void testAddPropertyChangeListeners() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();

        gameScene.addPropChangeListener(listener);
        gameScene.goToScene("newScene");

        assertTrue(listener.eventFired);
    }

    @Test
    public void testRemovePropertyChangeListeners() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();

        gameScene.addPropChangeListener(listener);
        gameScene.removePropChangeListener(listener);
        gameScene.goToScene("anotherScene");

        assertFalse(listener.eventFired);
    }

    // Test logic: Ensure the GameScene correctly creates buttons with the specified
    // properties.
    @Test
    public void testCreateButton() {
        Button button = gameScene.createButton(100, 50, "/button.png");

        assertNotNull(button);
        assertEquals(100, button.getPrefWidth());
        assertEquals(50, button.getPrefHeight());
        assertEquals(
                "-fx-background-color: transparent;-fx-background-image: url('/button.png');-fx-background-size: cover;-fx-background-position: center;-fx-background-repeat: no-repeat;",
                button.getStyle());
    }

    // Test logic: Ensure the GameScene correctly destroys itself and cleans up
    // resources.
    @Test
    @Order(Integer.MAX_VALUE)
    public void testDestroy() {
        gameScene.destroy();

        assertNull(gameScene.getRoot());
        assertNull(gameScene.getBackground());
        assertNull(gameScene.getScene());
    }

    // Concrete subclass for testing purposes
    private class ConcreteGameScene extends GameScene {
        public ConcreteGameScene(String backgroundImageName, double screenWidth, double screenHeight) {
            super(backgroundImageName, screenWidth, screenHeight);
        }

        @Override
        public void update() {
            // Mock implementation
        }
    }

    // PropertyChangeListener for testing purposes
    private static class TestPropertyChangeListener implements PropertyChangeListener {
        boolean eventFired = false;

        @Override
        public void propertyChange(PropertyChangeEvent event) {
            eventFired = true;
        }
    }
}