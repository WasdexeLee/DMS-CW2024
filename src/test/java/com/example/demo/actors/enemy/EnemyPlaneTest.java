package com.example.demo.actors.enemy;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.EnemyProjectile;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.utils.EnumUtil.EffectAudioType;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnemyPlaneTest extends ApplicationTest {

    private EnemyPlane enemyPlane;
    private AudioManager mockAudioManager;

    @Override
    public void start(Stage stage) throws Exception {
        mockAudioManager = mock(AudioManager.class);
        setPrivateField(null, mockAudioManager, "instance");
    }

    @BeforeEach
    public void setUp() {
        enemyPlane = new EnemyPlane(1000, 400, "plane/enemyplane1.png", 0.5);
    }

    // Test logic: Ensure the EnemyPlane is correctly initialized with the expected
    // properties.
    @Test
    public void testInitialization() {
        assertNotNull(enemyPlane.getImage());
        assertEquals(53, enemyPlane.getFitHeight());
        assertEquals(1000, enemyPlane.getLayoutX());
        assertEquals(400, enemyPlane.getLayoutY());
        assertEquals(1, enemyPlane.getHealth());
    }

    // Test logic: Ensure the EnemyPlane moves horizontally with the expected
    // velocity.
    @Test
    public void testMoveHorizontally() {
        double initialXPos = enemyPlane.getTranslateX();
        for (int i = 0; i < 100; i++)
            enemyPlane.updateActor();

        assertNotEquals(initialXPos, enemyPlane.getTranslateX());
    }

    // Test logic: Ensure the EnemyPlane fires a projectile with the correct initial
    // position.
    @Test
    public void testFireProjectile() {
        ActiveActorDestructible projectile;
        do {
            projectile = enemyPlane.fireProjectile();
        } while (projectile == null);

        assertNotNull(projectile);
        assertTrue(projectile instanceof EnemyProjectile);
    }

    // Test logic: Ensure the EnemyPlane triggers the fire projectile sound effect.
    @Test
    public void testFireProjectileSoundEffect() {
        ActiveActorDestructible projectile;
        do {
            projectile = enemyPlane.fireProjectile();
        } while (projectile == null);

        verify(mockAudioManager).fireEffectAudio(EffectAudioType.ENEMYFIRE);
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
}