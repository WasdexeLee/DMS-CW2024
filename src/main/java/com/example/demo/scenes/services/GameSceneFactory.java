package com.example.demo.scenes.services;

import com.example.demo.scenes.*;
import com.example.demo.scenes.levels.*;
import com.example.demo.utils.EnumUtil.SceneType;

public class GameSceneFactory {

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