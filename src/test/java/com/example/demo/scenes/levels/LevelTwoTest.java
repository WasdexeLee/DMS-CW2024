package com.example.demo.scenes.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.scenes.levels.services.LevelState;
import com.example.demo.scenes.levels.services.managers.EnemyManager;

import java.util.List;

import javafx.scene.Group;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LevelTwoTest extends ApplicationTest {

    private LevelTwo levelTwo;

    private Stage stage;
    private EnemyManager mockEnemyManager;
    private LevelState mockLevelState;

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

        this.mockEnemyManager = mock(EnemyManager.class);
        this.mockLevelState = mock(LevelState.class);

        setPrivateField(null, this.mockEnemyManager, "instance");
        setPrivateField(null, this.mockLevelState, "instance");

        this.levelTwo = new LevelTwo(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    // Test logic: Ensure the LevelTwo initializes with the correct background and
    // dimensions.
    @Test
    public void testInitialization() {
        assertNotNull(levelTwo.getScene());
        assertNotNull(getPrivateMethod(levelTwo, "getBackground", null));
        assertEquals(1366.0, getPrivateMethod(levelTwo, "getScreenWidth", null));
        assertEquals(762.0, getPrivateMethod(levelTwo, "getScreenHeight", null));
    }

    // Test logic: Ensure the LevelTwo correctly handles user kill target logic.
    @Test
    public void testUserKillTargetLogicReach() {
        when(mockLevelState.getNumberOfKills()).thenReturn(50);

        assertTrue(levelTwo.userKillTargetLogic());
    }

    @Test
    public void testUserKillTargetLogicNotReach() {
        when(mockLevelState.getNumberOfKills()).thenReturn(5);

        assertFalse(levelTwo.userKillTargetLogic());
    }

    // Test logic: Ensure the LevelTwo correctly spawns enemy units.
    @Test
    public void testSpawnEnemyUnits() {
        when(mockLevelState.getCurrentNumberOfEnemies()).thenReturn(0);

        for (int i = 0; i < 100; i++)
            levelTwo.spawnEnemyUnits();

        verify(mockEnemyManager, atLeastOnce()).addEnemyUnit(any(ActiveActorDestructible.class), any(List.class),
                any(Group.class));
    }

    // Test logic: Ensure the LevelTwo correctly handles plane clumping.
    @Test
    public void testPlaneClumping() {
        setPrivateField(levelTwo, new double[] { 100.0, 200.0, 300.0 }, "recentSpawnYCoord");

        assertTrue((Boolean) getPrivateMethod(levelTwo, "planeClumping", new Class[] { double.class }, 130.0));
        assertFalse((Boolean) getPrivateMethod(levelTwo, "planeClumping", new Class[] { double.class }, 400.0));
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