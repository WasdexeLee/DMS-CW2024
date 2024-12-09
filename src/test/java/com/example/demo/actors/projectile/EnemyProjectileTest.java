package com.example.demo.actors.projectile;

import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyProjectileTest extends ApplicationTest {

    private EnemyProjectile enemyProjectile;

    @Override
    public void start(Stage stage) throws Exception {
    }

    @BeforeEach
    public void setUp() {
        enemyProjectile = new EnemyProjectile(1000, 400);
    }

    // Test logic: Ensure the EnemyProjectile is correctly initialized with the
    // expected properties.
    @Test
    public void testInitialization() {
        assertNotNull(enemyProjectile.getImage());
        assertEquals(31, enemyProjectile.getFitHeight());
        assertEquals(1000, enemyProjectile.getLayoutX());
        assertEquals(400, enemyProjectile.getLayoutY());
    }

    // Test logic: Ensure the EnemyProjectile moves horizontally with the expected
    // velocity.
    @Test
    public void testMoveHorizontally() {
        double initialXPos = enemyProjectile.getTranslateX();
        for (int i = 0; i < 100; i++)
            enemyProjectile.updateActor();

        assertNotEquals(initialXPos, enemyProjectile.getTranslateX());
    }
}