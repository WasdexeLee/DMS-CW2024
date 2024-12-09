package com.example.demo.scenes.levels.services;

import com.example.demo.actors.props.HeartDisplay;

import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LevelViewTest extends ApplicationTest {

    private Stage stage;

    private LevelView levelView;
    private Group mockRoot;
    private HeartDisplay mockHeartDisplay;

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

        this.mockRoot = (Group) spy(new Group());
        this.mockHeartDisplay = mock(HeartDisplay.class,
                withSettings().useConstructor(5.0, 25.0, 5).defaultAnswer(CALLS_REAL_METHODS));

        this.levelView = new LevelView(mockRoot, 5);

        setPrivateField(this.levelView, this.mockHeartDisplay, "heartDisplay");
    }

    // Test logic: Ensure the LevelView initializes with the correct number of
    // hearts.
    @Test
    public void testInitialization() {
        assertEquals(5, ((HBox) getPrivateField(mockHeartDisplay, "container")).getChildren().toArray().length);
    }

    // Test logic: Ensure the LevelView correctly shows the heart display.
    @Test
    public void testShowHeartDisplay() {
        levelView.showHeartDisplay();

        verify(mockRoot).getChildren();
    }

    // Test logic: Ensure the LevelView correctly removes hearts.
    @Test
    public void testRemoveHearts() {
        levelView.removeHearts(2);

        verify(mockHeartDisplay, times(3)).removeHeart();
    }

    // Helper method to inject private field for testing
    private void setPrivateField(Object object, Object injectedField, String fieldName) {
        Class<?> currentClass;
        if (object == null)
            currentClass = injectedField.getClass();
        else
            currentClass = object.getClass();

        while (currentClass != null) {
            try {
                // Attempt to find the field in the current class
                java.lang.reflect.Field field = currentClass.getDeclaredField(fieldName);
                field.setAccessible(true);

                field.set(object, injectedField);
                return;
            } catch (NoSuchFieldException e) {
                // Move up to the superclass if the field is not in the current class
                currentClass = currentClass.getSuperclass();
            } catch (Exception e) {
                throw new RuntimeException("Failed to inject field", e);
            }
        }
        throw new RuntimeException("Field not found in class hierarchy: " + fieldName);
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