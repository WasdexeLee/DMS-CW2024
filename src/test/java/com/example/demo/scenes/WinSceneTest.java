package com.example.demo.scenes;

import com.example.demo.utils.EnumUtil.SceneType;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WinSceneTest extends ApplicationTest {

    private Stage stage;
    private WinScene winScene;

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

        this.winScene = new WinScene(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    // Test logic: Ensure the WinScene initializes with the correct background and dimensions.
    @Test
    public void testInitialization() {
        assertNotNull(winScene.getScene());
        assertNotNull(winScene.getBackground());
        assertEquals(1366, winScene.getScreenWidth());
        assertEquals(762, winScene.getScreenHeight());
    }

    // Test logic: Ensure the WinScene correctly adds and removes PropertyChangeListeners.
    @Test
    public void testAddPropertyChangeListeners() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        winScene.addPropChangeListener(listener);
        winScene.goToScene(SceneType.LEVEL1);
        assertTrue(listener.eventFired);
    }

    @Test
    public void testRemovePropertyChangeListeners() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();

        winScene.addPropChangeListener(listener);
        winScene.removePropChangeListener(listener);
        winScene.goToScene(SceneType.MENU);

        assertFalse(listener.eventFired);
    }

    // Test logic: Ensure the WinScene correctly creates buttons with the specified properties.
    @Test
    public void testCreateButtons() {
        VBox mainLayout = (VBox) winScene.getRoot().getChildren().get(1);
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