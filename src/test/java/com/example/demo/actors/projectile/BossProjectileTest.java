package com.example.demo.actors.projectile;

import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BossProjectileTest extends ApplicationTest {

    private BossProjectile bossProjectile;

    @Override
    public void start(Stage stage) throws Exception {
    }

    @BeforeEach
    public void setUp() {
        bossProjectile = new BossProjectile(100);
    }

    // Test logic: Ensure the BossProjectile is correctly initialized with the expected properties.
    @Test
    public void testInitialization() {
        assertNotNull(bossProjectile.getImage());
        assertEquals(75, bossProjectile.getFitHeight());
        assertEquals(950, bossProjectile.getLayoutX());
        assertEquals(100, bossProjectile.getLayoutY());
    }

    // Test logic: Ensure the BossProjectile moves horizontally with the expected velocity.
    @Test
    public void testMoveHorizontally() {
        double initialXPos = bossProjectile.getTranslateX();
        for (int i = 0; i < 100; i++)
            bossProjectile.updateActor();

        assertNotEquals(initialXPos, bossProjectile.getTranslateX());
    }
}