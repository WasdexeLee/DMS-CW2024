package com.example.demo.scenes.services;

import com.example.demo.scenes.*;
import com.example.demo.scenes.levels.*;
import com.example.demo.utils.EnumUtil.SceneType;

/**
 * A factory class responsible for creating different types of game scenes.
 * This class provides a method to instantiate specific scenes based on the {@link SceneType}.
 *
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class GameSceneFactory {

    /**
     * Creates a game scene based on the specified {@link SceneType}.
     *
     * @param sceneType    The type of scene to create.
     * @param screenWidth  The width of the screen.
     * @param screenHeight The height of the screen.
     * @return A {@link GameScene} instance corresponding to the specified type.
     * @throws IllegalArgumentException If an unknown scene type is provided.
     */
    public GameScene createScene(SceneType sceneType, double screenWidth, double screenHeight) {
        switch (sceneType) {
            case MENU:
                return new MenuScene(screenWidth, screenHeight);
            case LEVEL1:
                return new LevelOne(screenWidth, screenHeight);
            case LEVEL2:
                return new LevelTwo(screenWidth, screenHeight);
            case LEVEL3:
                return new LevelThree(screenWidth, screenHeight);
            case LOSESCENE:
                return new LoseScene(screenWidth, screenHeight);
            case WINSCENE:
                return new WinScene(screenWidth, screenHeight);
            default:
                throw new IllegalArgumentException("Unknown screen type: " + sceneType);
        }
    }
}