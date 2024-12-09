package com.example.demo.scenes;

import com.example.demo.utils.EnumUtil.SceneType;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoseSceneTest extends ApplicationTest {

    private Stage stage;
    private LoseScene loseScene;

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

        this.loseScene = new LoseScene(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    // Test logic: Ensure the LoseScene initializes with the correct background and
    // dimensions.
    @Test
    public void testInitialization() {
        assertNotNull(loseScene.getScene());
        assertNotNull(loseScene.getBackground());
        assertEquals(1366, loseScene.getScreenWidth());
        assertEquals(762, loseScene.getScreenHeight());
    }

    // Test logic: Ensure the LoseScene correctly adds and removes
    // PropertyChangeListeners.
    @Test
    public void testAddPropertyChangeListeners() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();

        loseScene.addPropChangeListener(listener);
        loseScene.goToScene(SceneType.LEVEL1);

        assertTrue(listener.eventFired);
    }

    @Test
    public void testRemovePropertyChangeListeners() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();

        loseScene.addPropChangeListener(listener);
        loseScene.removePropChangeListener(listener);
        loseScene.goToScene(SceneType.MENU);

        assertFalse(listener.eventFired);
    }

    // Test logic: Ensure the LoseScene correctly creates buttons with the specified
    // properties.
    @Test
    public void testCreateButtons() {
        VBox mainLayout = (VBox) loseScene.getRoot().getChildren().get(1);
        HBox buttonBox = (HBox) mainLayout.getChildren().get(3);

        Button yesButton = (Button) buttonBox.getChildren().get(0);
        Button noButton = (Button) buttonBox.getChildren().get(1);

        assertNotNull(yesButton);
        assertNotNull(noButton);

        assertEquals(140, yesButton.getPrefWidth());
        assertEquals(68, yesButton.getPrefHeight());

        assertEquals(140, noButton.getPrefWidth());
        assertEquals(68, noButton.getPrefHeight());
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