package com.example.demo.scenes.services;

import com.example.demo.core.Game;
import com.example.demo.scenes.GameScene;
import com.example.demo.scenes.MenuScene;
import com.example.demo.scenes.levels.LevelOne;
import com.example.demo.utils.EnumUtil.SceneType;

import java.util.concurrent.CountDownLatch;
import java.beans.PropertyChangeEvent;

import javafx.stage.Stage;
import javafx.application.Platform;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameSceneManagerTest extends ApplicationTest {

    private GameSceneManager gameSceneManager;
    private Game game;
    private Stage stage;

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

        this.game = Game.getInstance(this.stage);
        this.gameSceneManager = GameSceneManager.getInstance(this.game, this.stage);
    }

    // Test logic: Ensure the GameSceneManager initializes with the correct initial
    // scene (MENU).
    @Test
    @Order(1)
    public void testInitialScene() {
        assertTrue(getCurrentGameScene() instanceof MenuScene);
    }

    @Test
    @Order(2)
    public void testStageInitialScene() {
        assertEquals(getCurrentGameScene().getScene(), stage.getScene());
    }

    // Test logic: Ensure the GameSceneManager correctly transitions between scenes.
    @Test
    public void testSceneTransition() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                PropertyChangeEvent event = new PropertyChangeEvent(this, "sceneChange", null, SceneType.LEVEL1);
                gameSceneManager.propertyChange(event);

                assertTrue(getCurrentGameScene() instanceof LevelOne);
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

    @Test
    public void testStageSceneTransition() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                PropertyChangeEvent event = new PropertyChangeEvent(this, "sceneChange", null, SceneType.LEVEL2);
                gameSceneManager.propertyChange(event);

                assertEquals(getCurrentGameScene().getScene(), stage.getScene());
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

    // Test logic: Ensure the GameSceneManager handles unknown property change
    // events correctly.
    @Test
    public void testUnknownPropertyChangeEvent() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                PropertyChangeEvent event = new PropertyChangeEvent(this, "unknownChange", null, null);

                assertThrows(IllegalArgumentException.class, () -> gameSceneManager.propertyChange(event));
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

    // Helper method to access the private currentGameScene field for testing
    private GameScene getCurrentGameScene() {
        try {
            java.lang.reflect.Field field = GameSceneManager.class.getDeclaredField("currentGameScene");
            field.setAccessible(true);

            return (GameScene) field.get(gameSceneManager);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access GameScene field", e);
        }
    }
}