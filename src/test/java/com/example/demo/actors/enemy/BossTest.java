package com.example.demo.actors.enemy;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.BossProjectile;
import com.example.demo.actors.props.ShieldImage;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.scenes.levels.services.LevelView;
import com.example.demo.utils.EnumUtil.EffectAudioType;

import javafx.scene.Group;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BossTest extends ApplicationTest {

    private Boss boss;
    private AudioManager mockAudioManager;

    @Override
    public void start(Stage stage) throws Exception {
        mockAudioManager = mock(AudioManager.class);
        setPrivateField(null, mockAudioManager, "instance");
    }

    @BeforeEach
    public void setUp() {
        boss = new Boss(new LevelView(new Group(), 5));
    }

    // Test logic: Ensure the Boss is correctly initialized with the expected
    // properties.
    @Test
    public void testInitialization() {
        assertNotNull(boss.getImage());
        assertEquals(55, boss.getFitHeight());
        assertEquals(1000.0, boss.getLayoutX());
        assertEquals(400.0, boss.getLayoutY());
        assertEquals(140, boss.getHealth());
    }

    // Test logic: Ensure the Boss fires a projectile of the right type.
    @Test
    public void testFireProjectile() {
        ActiveActorDestructible projectile;
        do {
            projectile = boss.fireProjectile();
        } while (projectile == null);

        assertNotNull(projectile);
        assertTrue(projectile instanceof BossProjectile);
    }

    @Test
    public void testFireProjectileSoundEffect() {
        ActiveActorDestructible projectile;
        do {
            projectile = boss.fireProjectile();
        } while (projectile == null);

        verify(mockAudioManager).fireEffectAudio(EffectAudioType.ENEMYFIRE);
    }

    // Test logic: Ensure the Boss takes damage only when not shielded.
    @Test
    public void testTakeDamageShieldUp() {
        getPrivateMethod(boss, "deactivateShield", null);
        getPrivateMethod(boss, "activateShield", null);
        boss.takeDamage();

        assertEquals(140, boss.getHealth());
    }

    @Test
    public void testTakeDamageShieldDown() {
        getPrivateMethod(boss, "activateShield", null);
        getPrivateMethod(boss, "deactivateShield", null);
        boss.takeDamage();

        assertEquals(139, boss.getHealth());
    }

    // Test logic: Ensure the Boss's shield is correctly activated and deactivated.
    @Test
    public void testShieldActivation() {
        getPrivateMethod(boss, "deactivateShield", null);
        getPrivateMethod(boss, "activateShield", null);

        assertTrue((boolean) getPrivateField(boss, "isShielded"));
    }

    @Test
    public void testShieldDeactivation() {
        getPrivateMethod(boss, "deactivateShield", null);
        getPrivateMethod(boss, "activateShield", null);
        getPrivateMethod(boss, "deactivateShield", null);

        assertFalse((boolean) getPrivateField(boss, "isShielded"));
    }

    // Test logic: Ensure the Boss's shield image is correctly updated with the
    // Boss's movement.
    @Test
    public void testShieldImageUpdate() {
        ShieldImage shieldImage = boss.getShieldImage();
        double initialTranslateY = shieldImage.getTranslateY();
        boss.updateActor();

        assertEquals(initialTranslateY + boss.getTranslateY(), shieldImage.getTranslateY());
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