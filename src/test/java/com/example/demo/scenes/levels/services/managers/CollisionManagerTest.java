package com.example.demo.scenes.levels.services.managers;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.user.UserPlane;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.utils.EnumUtil.EffectAudioType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Bounds;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class CollisionManagerTest extends ApplicationTest {

    private CollisionManager collisionManager;
    private Stage stage;
    private UserPlane mockActor1;
    private UserPlane mockActor2;
    private UserPlane mockActor3;
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

        this.collisionManager = CollisionManager.getInstance();

        this.mockActor1 = mock(UserPlane.class, withSettings().useConstructor(5).defaultAnswer(CALLS_REAL_METHODS));
        this.mockActor2 = mock(UserPlane.class, withSettings().useConstructor(5).defaultAnswer(CALLS_REAL_METHODS));
        this.mockActor3 = mock(UserPlane.class, withSettings().useConstructor(5).defaultAnswer(CALLS_REAL_METHODS));
        this.mockAudioManager = mock(AudioManager.class);

        setPrivateField(null, mockAudioManager, "instance");
    }

    // Test logic: Ensure the CollisionManager correctly detects and handles
    // collisions.
    @Test
    public void testTakeDamage() {
        List<ActiveActorDestructible> actors1 = new ArrayList<>(Arrays.asList(mockActor1, mockActor2));
        List<ActiveActorDestructible> actors2 = new ArrayList<>(Arrays.asList(mockActor3));

        Bounds mockBounds1 = mock(Bounds.class);
        Bounds mockBounds2 = mock(Bounds.class);
        Bounds mockBounds3 = mock(Bounds.class);

        when(mockActor1.getBoundsInParent()).thenReturn(mockBounds1);
        when(mockActor2.getBoundsInParent()).thenReturn(mockBounds2);
        when(mockActor3.getBoundsInParent()).thenReturn(mockBounds3);

        when(mockBounds1.intersects(mockBounds3)).thenReturn(true);
        when(mockBounds2.intersects(mockBounds3)).thenReturn(false);

        collisionManager.handleCollisions(actors1, actors2, EffectAudioType.KILL);

        verify(mockActor1).takeDamage();
        verify(mockActor3).takeDamage();
        verify(mockActor2, never()).takeDamage();
    }

    @Test
    public void testSoundEffect() {
        List<ActiveActorDestructible> actors1 = new ArrayList<>(Arrays.asList(mockActor1, mockActor2));
        List<ActiveActorDestructible> actors2 = new ArrayList<>(Arrays.asList(mockActor3));

        Bounds mockBounds1 = mock(Bounds.class);
        Bounds mockBounds2 = mock(Bounds.class);
        Bounds mockBounds3 = mock(Bounds.class);

        when(mockActor1.getBoundsInParent()).thenReturn(mockBounds1);
        when(mockActor2.getBoundsInParent()).thenReturn(mockBounds2);
        when(mockActor3.getBoundsInParent()).thenReturn(mockBounds3);

        when(mockBounds1.intersects(mockBounds3)).thenReturn(true);
        when(mockBounds2.intersects(mockBounds3)).thenReturn(false);

        collisionManager.handleCollisions(actors1, actors2, EffectAudioType.KILL);

        verify(mockAudioManager).fireEffectAudio(EffectAudioType.KILL);
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