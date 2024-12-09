package com.example.demo.scenes.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.core.GameLoop;
import com.example.demo.scenes.levels.services.LevelState;
import com.example.demo.scenes.levels.services.managers.EnemyManager;
import com.example.demo.utils.EnumUtil.BackgroundAudioType;

import java.util.List;

import javafx.scene.Group;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LevelOneTest extends ApplicationTest {

    private LevelOne levelOne;

    private Stage stage;
    private AudioManager mockAudioManager;
    private GameLoop mockGameLoop;
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

        this.mockAudioManager = mock(AudioManager.class);
        this.mockGameLoop = mock(GameLoop.class);
        this.mockEnemyManager = mock(EnemyManager.class);
        this.mockLevelState = mock(LevelState.class);

        setPrivateField(null, this.mockAudioManager, "instance");
        setPrivateField(null, this.mockGameLoop, "instance");
        setPrivateField(null, this.mockEnemyManager, "instance");
        setPrivateField(null, this.mockLevelState, "instance");

        this.levelOne = new LevelOne(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    // Test logic: Ensure the LevelOne initializes with the correct background and
    // dimensions.
    @Test
    public void testInitialization() {
        assertNotNull(levelOne.getScene());
        assertNotNull(getPrivateMethod(levelOne, "getBackground", null));
        assertEquals(1366.0, getPrivateMethod(levelOne, "getScreenWidth", null));
        assertEquals(762.0, getPrivateMethod(levelOne, "getScreenHeight", null));
    }

    // Test logic: Ensure the LevelOne correctly changes the background audio.
    @Test
    public void testBackgroundAudioChange() {
        verify(mockAudioManager).changeBackgroundAudio(BackgroundAudioType.LEVEL);
    }

    // Test logic: Ensure the LevelOne correctly handles user kill target logic.
    @Test
    public void testUserKillTargetLogicReach() {
        when(mockLevelState.getNumberOfKills()).thenReturn(10);

        assertTrue(levelOne.userKillTargetLogic());
    }

    @Test
    public void testUserKillTargetLogicNotReach() {
        when(mockLevelState.getNumberOfKills()).thenReturn(5);

        assertFalse(levelOne.userKillTargetLogic());
    }

    // Test logic: Ensure the LevelOne correctly spawns enemy units.
    @Test
    public void testSpawnEnemyUnits() {
        when(mockLevelState.getCurrentNumberOfEnemies()).thenReturn(0);
        levelOne.spawnEnemyUnits();

        verify(mockEnemyManager, atLeastOnce()).addEnemyUnit(any(ActiveActorDestructible.class), any(List.class),
                any(Group.class));
    }

    // Test logic: Ensure the LevelOne correctly handles plane clumping.
    @Test
    public void testPlaneClumping() {
        setPrivateField(levelOne, new double[] { 100.0, 200.0, 300.0 }, "recentSpawnYCoord");

        assertTrue((Boolean) getPrivateMethod(levelOne, "planeClumping", new Class[] { double.class }, 130.0));
        assertFalse((Boolean) getPrivateMethod(levelOne, "planeClumping", new Class[] { double.class }, 400.0));
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