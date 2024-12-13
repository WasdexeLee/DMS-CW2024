package com.example.demo.scenes.levels.services.managers;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemy.EnemyPlane;
import com.example.demo.actors.props.ShieldImage;
import com.example.demo.actors.user.UserPlane;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.utils.EnumUtil.EffectAudioType;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnemyManagerTest extends ApplicationTest {

    private EnemyManager enemyManager;
    private Stage stage;
    private UserPlane mockUserUnit;
    private EnemyPlane mockEnemy1;
    private EnemyPlane mockEnemy2;
    private Group mockRoot;
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

        this.enemyManager = EnemyManager.getInstance();

        this.mockUserUnit = mock(UserPlane.class, withSettings().useConstructor(5).defaultAnswer(CALLS_REAL_METHODS));
        this.mockEnemy1 = mock(EnemyPlane.class,
                withSettings().useConstructor(1366.0, 200.0, "plane/enemyplane1.png", .2).defaultAnswer(CALLS_REAL_METHODS));
        this.mockEnemy2 = mock(EnemyPlane.class,
                withSettings().useConstructor(1366.0, 200.0, "plane/enemyplane1.png", .2).defaultAnswer(CALLS_REAL_METHODS));
        this.mockRoot = spy(new Group());
        this.mockAudioManager = mock(AudioManager.class);

        setPrivateField(null, mockAudioManager, "instance");
    }

    // Test logic: Ensure the EnemyManager correctly handles enemy penetration.
    @Test
    public void testHandleEnemyPenetration() {
        List<ActiveActorDestructible> enemyUnits = new ArrayList<>();
        enemyUnits.add(mockEnemy1);
        enemyUnits.add(mockEnemy2);

        Bounds mockBounds1 = mock(Bounds.class);
        Bounds mockBounds2 = mock(Bounds.class);

        when(mockEnemy1.getBoundsInParent()).thenReturn(mockBounds1);
        when(mockEnemy2.getBoundsInParent()).thenReturn(mockBounds2);

        when(mockEnemy1.getTranslateX()).thenReturn(1500.0);
        when(mockEnemy2.getTranslateX()).thenReturn(500.0);

        when(mockBounds1.getWidth()).thenReturn(100.0);
        when(mockBounds2.getWidth()).thenReturn(100.0);

        enemyManager.handleEnemyPenetration(mockUserUnit, enemyUnits, 1366);

        verify(mockUserUnit).takeDamage();
        verify(mockEnemy1).destroy();
        verify(mockEnemy2, never()).destroy();
    }

    @Test
    public void testHandleEnemyPenetrationSoundEffect() {
        List<ActiveActorDestructible> enemyUnits = new ArrayList<>();
        enemyUnits.add(mockEnemy1);
        enemyUnits.add(mockEnemy2);

        Bounds mockBounds1 = mock(Bounds.class);
        Bounds mockBounds2 = mock(Bounds.class);

        when(mockEnemy1.getBoundsInParent()).thenReturn(mockBounds1);
        when(mockEnemy2.getBoundsInParent()).thenReturn(mockBounds2);

        when(mockEnemy1.getTranslateX()).thenReturn(1500.0);
        when(mockEnemy2.getTranslateX()).thenReturn(500.0);

        when(mockBounds1.getWidth()).thenReturn(100.0);
        when(mockBounds2.getWidth()).thenReturn(100.0);

        enemyManager.handleEnemyPenetration(mockUserUnit, enemyUnits, 1366);

        verify(mockAudioManager).fireEffectAudio(EffectAudioType.DAMAGE);
    }

    // Test logic: Ensure the EnemyManager correctly adds a new enemy unit.
    @Test
    public void testAddEnemyUnit() {
        List<ActiveActorDestructible> enemyUnits = new ArrayList<>();
        enemyManager.addEnemyUnit(mockEnemy1, enemyUnits, mockRoot);

        assertTrue(enemyUnits.contains(mockEnemy1));
        verify(mockRoot).getChildren();
    }

    // Test logic: Ensure the EnemyManager correctly adds an enemy prop.
    @Test
    public void testAddEnemyProp() {
        ShieldImage mockEnemyProp = new ShieldImage(0.0, 0.0);
        enemyManager.addEnemyProp(mockEnemyProp, mockRoot);

        verify(mockRoot).getChildren();
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