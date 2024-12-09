package com.example.demo.scenes.levels;

import com.example.demo.actors.enemy.Boss;
import com.example.demo.scenes.levels.services.managers.EnemyManager;

import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LevelThreeTest extends ApplicationTest {

    private LevelThree levelThree;

    private Stage stage;
    private EnemyManager mockEnemyManager;
    private Boss mockBoss;

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

        this.levelThree = new LevelThree(SCREEN_WIDTH, SCREEN_HEIGHT);

        this.mockEnemyManager = mock(EnemyManager.class);
        this.mockBoss = mock(Boss.class);

        setPrivateField(null, this.mockEnemyManager, "instance");
        setPrivateField(levelThree, this.mockBoss, "boss");
    }

    // Test logic: Ensure the LevelThree initializes with the correct background and
    // dimensions.
    @Test
    public void testInitialization() {
        assertNotNull(levelThree.getScene());
        assertNotNull(getPrivateMethod(levelThree, "getBackground", null));
        assertEquals(1366.0, getPrivateMethod(levelThree, "getScreenWidth", null));
        assertEquals(762.0, getPrivateMethod(levelThree, "getScreenHeight", null));
    }

    // Test logic: Ensure the LevelThree correctly handles user kill target logic.
    @Test
    public void testUserKillTargetLogicReach() {
        when(mockBoss.getIsDestroyed()).thenReturn(true);

        assertTrue(levelThree.userKillTargetLogic());
    }

    @Test
    public void testUserKillTargetLogicNotReach() {
        when(mockBoss.getIsDestroyed()).thenReturn(false);

        assertFalse(levelThree.userKillTargetLogic());
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

    // Helper method to access and invoke a private method for testing
    private Object getPrivateMethod(Object object, String methodName, Class<?>[] parameterTypes, Object... args) {
        Class<?> currentClass = object.getClass();

        while (currentClass != null) {
            try {
                // Attempt to get the method from the current class
                java.lang.reflect.Method method = currentClass.getDeclaredMethod(methodName, parameterTypes);
                method.setAccessible(true);

                return method.invoke(object, args);
            } catch (NoSuchMethodException e) {
                // If the method is not in the current class, move to the superclass
                currentClass = currentClass.getSuperclass();
            } catch (Exception e) {
                throw new RuntimeException("Failed to invoke method", e);
            }
        }
        throw new RuntimeException("Method not found in class hierarchy: " + methodName);
    }
}