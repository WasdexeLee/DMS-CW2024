package com.example.demo.actors.projectile;

import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserProjectileTest extends ApplicationTest {

    private UserProjectile userProjectile;

    @Override
    public void start(Stage stage) throws Exception {
    }

    @BeforeEach
    public void setUp() {
        userProjectile = new UserProjectile(100, 200);
    }

    // Test logic: Ensure the UserProjectile is correctly initialized with the
    // expected properties.
    @Test
    public void testInitialization() {
        assertNotNull(userProjectile.getImage());
        assertEquals(7, userProjectile.getFitHeight());
        assertEquals(100, userProjectile.getLayoutX());
        assertEquals(200, userProjectile.getLayoutY());
    }

    // Test logic: Ensure the UserProjectile moves horizontally with the expected
    // velocity.
    @Test
    public void testMoveHorizontally() {
        double initialXPos = userProjectile.getTranslateX();
        for (int i = 0; i < 100; i++)
            userProjectile.updateActor();

        assertNotEquals(initialXPos, userProjectile.getTranslateX());
    }
}