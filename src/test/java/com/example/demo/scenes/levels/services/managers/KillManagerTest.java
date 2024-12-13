package com.example.demo.scenes.levels.services.managers;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemy.EnemyPlane;
import com.example.demo.actors.user.UserPlane;
import com.example.demo.scenes.levels.services.LevelState;
import com.example.demo.scenes.levels.services.LevelView;

import java.util.ArrayList;
import java.util.List;

import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class KillManagerTest extends ApplicationTest {

    private KillManager killManager;
    private Stage stage;
    private LevelState mockLevelState;
    private LevelView mockLevelView;
    private UserPlane mockUserPlane;
    private EnemyPlane mockEnemy1;
    private EnemyPlane mockEnemy2;

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

        this.killManager = KillManager.getInstance();

        this.mockLevelState = mock(LevelState.class);
        this.mockLevelView = mock(LevelView.class);
        this.mockUserPlane = mock(UserPlane.class);
        this.mockEnemy1 = mock(EnemyPlane.class,
                withSettings().useConstructor(1366.0, 200.0, "plane/enemyplane1.png", .2).defaultAnswer(CALLS_REAL_METHODS));
        this.mockEnemy2 = mock(EnemyPlane.class,
                withSettings().useConstructor(1366.0, 200.0, "plane/enemyplane1.png", .2).defaultAnswer(CALLS_REAL_METHODS));

        setPrivateField(null, mockLevelState, "instance");
    }

    // Test logic: Ensure the KillManager correctly updates the kill count.
    @Test
    public void testUpdateKillCount() {
        List<ActiveActorDestructible> enemyUnits = new ArrayList<>();
        enemyUnits.add(mockEnemy1);
        enemyUnits.add(mockEnemy2);

        when(mockLevelState.getCurrentNumberOfEnemies()).thenReturn(5);

        killManager.updateKillCount(mockLevelState, mockLevelView, enemyUnits, mockUserPlane, 100);

        verify(mockLevelState, times(3)).incrementNumberOfKills();
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