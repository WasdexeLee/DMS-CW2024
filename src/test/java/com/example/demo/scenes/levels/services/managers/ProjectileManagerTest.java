package com.example.demo.scenes.levels.services.managers;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemy.EnemyPlane;
import com.example.demo.actors.projectile.EnemyProjectile;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectileManagerTest extends ApplicationTest {
    private ProjectileManager projectileManager;
    private Stage stage;
    private EnemyProjectile mockProjectile1;
    private EnemyProjectile mockProjectile2;
    private EnemyPlane mockEnemy1;
    private EnemyPlane mockEnemy2;
    private Group mockRoot;

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

        this.projectileManager = ProjectileManager.getInstance();

        this.mockProjectile1 = mock(EnemyProjectile.class,
                withSettings().useConstructor(100.0, 100.0).defaultAnswer(CALLS_REAL_METHODS));
        this.mockProjectile2 = mock(EnemyProjectile.class,
                withSettings().useConstructor(100.0, 100.0).defaultAnswer(CALLS_REAL_METHODS));
        this.mockEnemy1 = mock(EnemyPlane.class,
                withSettings().useConstructor(1366.0, 200.0, "plane/enemyplane1.png", .2).defaultAnswer(CALLS_REAL_METHODS));
        this.mockEnemy2 = mock(EnemyPlane.class,
                withSettings().useConstructor(1366.0, 200.0, "plane/enemyplane1.png", .2).defaultAnswer(CALLS_REAL_METHODS));
        this.mockRoot = spy(new Group());
    }

    // Test logic: Ensure the ProjectileManager correctly handles projectiles going
    // out of the screen.
    @Test
    public void testHandleProjectileOutOfScreenLeft() {
        List<ActiveActorDestructible> projectiles = new ArrayList<>();
        projectiles.add(mockProjectile1);
        Bounds mockBounds1 = mock(Bounds.class);

        when(mockProjectile1.localToScene(mockProjectile1.getBoundsInLocal())).thenReturn(mockBounds1);
        when(mockBounds1.getMaxX()).thenReturn(-10.0);

        projectileManager.handleProjectileOutOfScreen(projectiles, 1366);

        verify(mockProjectile1).destroy();
    }

    @Test
    public void testHandleProjectileOutOfScreenRight() {
        List<ActiveActorDestructible> projectiles = new ArrayList<>();
        projectiles.add(mockProjectile2);
        Bounds mockBounds2 = mock(Bounds.class);

        when(mockProjectile2.localToScene(mockProjectile2.getBoundsInLocal())).thenReturn(mockBounds2);
        when(mockBounds2.getMinX()).thenReturn(1400.0);

        projectileManager.handleProjectileOutOfScreen(projectiles, 1366);

        verify(mockProjectile2).destroy();
    }

    // Test logic: Ensure the ProjectileManager correctly spawns projectiles.
    @Test
    public void testSpawnProjectile() {
        List<ActiveActorDestructible> fighterPlanes = new ArrayList<>();
        fighterPlanes.add(mockEnemy1);
        fighterPlanes.add(mockEnemy2);
        List<ActiveActorDestructible> projectilesList = new ArrayList<>();

        when(mockEnemy1.fireProjectile()).thenReturn(mockProjectile1);
        when(mockEnemy2.fireProjectile()).thenReturn(mockProjectile2);

        projectileManager.spawnProjectile(fighterPlanes, projectilesList, mockRoot);

        assertTrue(projectilesList.contains(mockProjectile1));
        assertTrue(projectilesList.contains(mockProjectile2));
        verify(mockRoot, times(2)).getChildren();
    }
}