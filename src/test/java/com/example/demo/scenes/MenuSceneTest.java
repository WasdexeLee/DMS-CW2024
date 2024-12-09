package com.example.demo.scenes;

import com.example.demo.utils.EnumUtil.SceneType;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuSceneTest extends ApplicationTest {

    private Stage stage;
    private MenuScene menuScene;

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

        this.menuScene = new MenuScene(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    // Test logic: Ensure the MenuScene initializes with the correct background and dimensions.
    @Test
    public void testInitialization() {
        assertNotNull(menuScene.getScene());
        assertNotNull(menuScene.getBackground());
        assertEquals(1366, menuScene.getScreenWidth());
        assertEquals(762, menuScene.getScreenHeight());
    }

    // Test logic: Ensure the MenuScene correctly adds and removes PropertyChangeListeners.
    @Test
    public void testAddPropertyChangeListeners() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        menuScene.addPropChangeListener(listener);
        menuScene.goToScene(SceneType.LEVEL1);
        assertTrue(listener.eventFired);
    }

@Test
    public void testRemovePropertyChangeListeners() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();

        menuScene.addPropChangeListener(listener);
        menuScene.removePropChangeListener(listener);
        menuScene.goToScene(SceneType.MENU);

        assertFalse(listener.eventFired);
    }

    // Test logic: Ensure the MenuScene correctly creates buttons with the specified properties.
    @Test
    public void testCreateButtons() {
        VBox mainLayout = (VBox) menuScene.getRoot().getChildren().get(1);
        VBox buttonBox = (VBox) mainLayout.getChildren().get(1);

        Button startButton = (Button) ((StackPane) buttonBox.getChildren().get(0)).getChildren().get(0);
        Button exitButton = (Button) ((StackPane) buttonBox.getChildren().get(1)).getChildren().get(0);
        
        assertNotNull(startButton);
        assertNotNull(exitButton);

        assertEquals(348, startButton.getPrefWidth());
        assertEquals(70, startButton.getPrefHeight());

        assertEquals(185, exitButton.getPrefWidth());
        assertEquals(70, exitButton.getPrefHeight());
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