package com.example.demo.scenes.levels;

import com.example.demo.actors.user.UserPlane;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.core.Game;
import com.example.demo.utils.EnumUtil.*;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LevelSceneTest extends ApplicationTest {

    private ConcreteLevelScene levelScene;
    private Stage stage;
    private UserPlane mockUserUnit;
    private Game mockGame;
    private AudioManager mockAudioManager;

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

        this.levelScene = new ConcreteLevelScene("/com/example/demo/images/menu/menu_background.jpeg", SCREEN_WIDTH,
                SCREEN_HEIGHT, 5);

        this.mockUserUnit = mock(UserPlane.class, withSettings().useConstructor(5).defaultAnswer(CALLS_REAL_METHODS));
        this.mockGame = mock(Game.class);
        this.mockAudioManager = mock(AudioManager.class);

        setPrivateField(this.levelScene, this.mockUserUnit, "userUnit");
        setPrivateField(null, this.mockGame, "instance");
        setPrivateField(null, this.mockAudioManager, "instance");
    }

    // Test logic: Ensure the LevelScene initializes with the correct background and
    // dimensions.
    @Test
    public void testInitialization() {
        assertNotNull(levelScene.getScene());
        assertNotNull(getPrivateMethod(levelScene, "getBackground", null));
        assertEquals(1366.0, getPrivateMethod(levelScene, "getScreenWidth", null));
        assertEquals(762.0, getPrivateMethod(levelScene, "getScreenHeight", null));
    }

    // Test logic: Ensure the LevelScene correctly handles events.
    @Test
    public void testMoveLeft() {
        ((ImageView) getPrivateMethod(levelScene, "getBackground", null))
                .fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.LEFT, false, false, false, false));

        verify(levelScene.getUserUnit()).moveLeft();
    }

    @Test
    public void testMoveRight() {
        ((ImageView) getPrivateMethod(levelScene, "getBackground", null))
                .fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false, false, false, false));

        verify(levelScene.getUserUnit()).moveRight();
    }

    @Test
    public void testMoveUp() {
        ((ImageView) getPrivateMethod(levelScene, "getBackground", null))
                .fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.UP, false, false, false, false));

        verify(levelScene.getUserUnit()).moveUp();
    }

    @Test
    public void testMoveDown() {
        ((ImageView) getPrivateMethod(levelScene, "getBackground", null))
                .fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false));

        verify(levelScene.getUserUnit()).moveDown();

    }

    @Test
    public void testMoveFire() {
        ((ImageView) getPrivateMethod(levelScene, "getBackground", null))
                .fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.SPACE, false, false, false, false));

        verify(levelScene.getUserUnit()).setIsFiring(true);
    }

    // Test logic: Ensure the LevelScene correctly handles game over conditions.
    @Test
    public void testGameOver() {
        when(levelScene.getUserUnit().getIsDestroyed()).thenReturn(true);
        levelScene.checkIfGameOver();

        verify(mockGame).setStateEndGame();
        verify(mockAudioManager).fireEffectAudio(EffectAudioType.GAME_OVER);
    }

    // Test logic: Ensure the LevelScene correctly handles pause menu.
    @Test
    public void testShowPauseMenu() {
        getPrivateMethod(levelScene, "showPauseMenu", null);

        assertTrue(((Group) getPrivateMethod(levelScene, "getRoot", null)).getChildren()
                .contains(getPrivateField(levelScene, "pauseMenu")));
    }

    @Test
    public void testPause() {
        getPrivateMethod(levelScene, "showPauseMenu", null);

        verify(mockGame).setStatePauseGame();
        verify(mockAudioManager).fireEffectAudio(EffectAudioType.PAUSE);
    }

    @Test
    public void testShowResumeMenu() {
        getPrivateMethod(levelScene, "resumeGame", null);

        assertFalse(((Group) getPrivateMethod(levelScene, "getRoot", null)).getChildren()
                .contains(getPrivateField(levelScene, "pauseMenu")));
    }

    @Test
    public void testResume() {
        getPrivateMethod(levelScene, "resumeGame", null);

        verify(mockGame).setStateResumeGame();
    }

    // Concrete subclass for testing purposes
    private static class ConcreteLevelScene extends LevelScene {
        public ConcreteLevelScene(String backgroundImageName, double screenWidth, double screenHeight,
                int playerInitialHealth) {
            super(backgroundImageName, screenWidth, screenHeight, playerInitialHealth);
        }

        @Override
        protected boolean userKillTargetLogic() {
            return false;
        }

        @Override
        protected void userKillTargetReachedAction() {
            // Mock implementation
        }

        @Override
        protected void spawnEnemyUnits() {
            // Mock implementation
        }

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