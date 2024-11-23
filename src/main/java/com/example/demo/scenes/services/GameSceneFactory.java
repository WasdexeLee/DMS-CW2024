package com.example.demo.scenes.services;

import com.example.demo.scenes.GameScene;
import com.example.demo.scenes.levels.LevelOne;
import com.example.demo.scenes.levels.LevelTwo;
import com.example.demo.utils.EnumUtil.SceneType;

public class GameSceneFactory {

    public GameScene createScene(SceneType sceneType, double screenWidth, double screenHeight) {
        switch (sceneType) {
            case LEVEL1:
                return new LevelOne(screenWidth, screenHeight);
            case LEVEL2:
                return new LevelTwo(screenWidth, screenHeight);
            default:
                throw new IllegalArgumentException("Unknown screen type: " + sceneType);
        }
    }
}