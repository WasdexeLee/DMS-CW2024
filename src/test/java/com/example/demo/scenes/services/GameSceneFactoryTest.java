package com.example.demo.scenes.services;

import com.example.demo.scenes.*;
import com.example.demo.scenes.levels.*;
import com.example.demo.utils.EnumUtil.SceneType;

import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameSceneFactoryTest extends ApplicationTest {

    private Stage stage;
    private GameSceneFactory gameSceneFactory;

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

        this.gameSceneFactory = new GameSceneFactory();
    }

    // Test logic: Ensure the GameSceneFactory creates the correct GameScene
    // instance for MENU.
    @Test
    public void testCreateMenuScene() {
        GameScene scene = gameSceneFactory.createScene(SceneType.MENU, 1366, 762);

        assertTrue(scene instanceof MenuScene);
    }

    // Test logic: Ensure the GameSceneFactory creates the correct GameScene
    // instance for LEVEL1.
    @Test
    public void testCreateLevelOneScene() {
        GameScene scene = gameSceneFactory.createScene(SceneType.LEVEL1, 1366, 762);

        assertTrue(scene instanceof LevelOne);
    }

    // Test logic: Ensure the GameSceneFactory creates the correct GameScene
    // instance for LEVEL2.
    @Test
    public void testCreateLevelTwoScene() {
        GameScene scene = gameSceneFactory.createScene(SceneType.LEVEL2, 1366, 762);

        assertTrue(scene instanceof LevelTwo);
    }

    // Test logic: Ensure the GameSceneFactory creates the correct GameScene
    // instance for LEVEL3.
    @Test
    public void testCreateLevelThreeScene() {
        GameScene scene = gameSceneFactory.createScene(SceneType.LEVEL3, 1366, 762);

        assertTrue(scene instanceof LevelThree);
    }

    // Test logic: Ensure the GameSceneFactory creates the correct GameScene
    // instance for LOSESCENE.
    @Test
    public void testCreateLoseScene() {
        GameScene scene = gameSceneFactory.createScene(SceneType.LOSESCENE, 1366, 762);

        assertTrue(scene instanceof LoseScene);
    }

    // Test logic: Ensure the GameSceneFactory creates the correct GameScene
    // instance for WINSCENE.
    @Test
    public void testCreateWinScene() {
        GameScene scene = gameSceneFactory.createScene(SceneType.WINSCENE, 1366, 762);

        assertTrue(scene instanceof WinScene);
    }
}