package com.example.demo.actors.user;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.UserProjectile;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.core.GameLoop;
import com.example.demo.utils.EnumUtil.EffectAudioType;

import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserPlaneTest extends ApplicationTest {

    private UserPlane userPlane;

    @Override
    public void start(Stage stage) throws Exception {
        this.userPlane = new UserPlane(100);

        AudioManager mockAudioManager = mock(AudioManager.class);
        GameLoop mockGameLoop = mock(GameLoop.class);

        setPrivateField(null, mockAudioManager, "instance");
        setPrivateField(null, mockGameLoop, "instance");
    }

    // Test logic: Ensure the user plane correctly sets the left movement flag and velocity multiplier.
    @Test
    public void testMoveLeft() throws InterruptedException {
        userPlane.moveLeft();

        assertTrue((boolean) getPrivateField(userPlane, "leftPressed"));
        assertEquals(-1, (int) getPrivateField(userPlane, "xVelocityMultiplier"));
    }

    // Test logic: Ensure the user plane correctly sets the right movement flag and velocity multiplier.
    @Test
    public void testMoveRight() throws InterruptedException {
        userPlane.moveRight();

        assertTrue((boolean) getPrivateField(userPlane, "rightPressed"));
        assertEquals(1, (int) getPrivateField(userPlane, "xVelocityMultiplier"));
    }

    // Test logic: Ensure the user plane correctly sets the up movement flag and velocity multiplier.
    @Test
    public void testMoveUp() throws InterruptedException {
        userPlane.moveUp();

        assertTrue((boolean) getPrivateField(userPlane, "upPressed"));
        assertEquals(-1, (int) getPrivateField(userPlane, "yVelocityMultiplier"));
    }

    // Test logic: Ensure the user plane correctly sets the down movement flag and velocity multiplier.
    @Test
    public void testMoveDown() throws InterruptedException {
        userPlane.moveDown();

        assertTrue((boolean) getPrivateField(userPlane, "downPressed"));
        assertEquals(1, (int) getPrivateField(userPlane, "yVelocityMultiplier"));
    }

    // Test logic: Ensure the user plane correctly fires a projectile and it is of the correct type.
    @Test
    public void testFireProjectile() throws InterruptedException {
        ActiveActorDestructible projectile = userPlane.fireProjectile();

        assertNotNull(projectile);
        assertTrue(projectile instanceof UserProjectile);
    }

    // Test logic: Ensure the user plane correctly triggers the fire projectile sound effect.
    @Test
    public void testFireProjectileSoundEffect() throws InterruptedException {
        userPlane.fireProjectile();

        verify(AudioManager.getInstance()).fireEffectAudio(EffectAudioType.USERFIRE);
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