package com.example.demo.scenes.services;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.stage.Stage;

import com.example.demo.core.Game;
import com.example.demo.scenes.GameScene;
import com.example.demo.utils.EnumUtil.SceneType;

public class GameSceneManager implements PropertyChangeListener{

    private static GameSceneManager instance;

    private final Game game;
    private final Stage stage;
    private final GameSceneFactory gameSceneFactory;

    private final int screenWidth;
    private final int screenHeight;
    private GameScene currentGameScene;


    private GameSceneManager(Game game, Stage stage) {
        this.game = game;
        this.game.addGameStatePropChangeListener(this);
        this.stage = stage;
        this.gameSceneFactory = new GameSceneFactory();


        // get state and then show screen
        // then add listener for the observable itself

        this.screenWidth = (int) Math.round(this.stage.getWidth());
        this.screenHeight = (int) Math.round(this.stage.getHeight());

        this.currentGameScene = this.gameSceneFactory.createScene(SceneType.LEVEL1, this.screenWidth, this.screenHeight);
        this.currentGameScene.addPropChangeListener(this);

        this.stage.setScene(this.currentGameScene.getScene());
    }

    public static GameSceneManager getInstance(Game game, Stage stage) {
        if (instance == null) {
            instance = new GameSceneManager(game, stage);
        }

        return instance;
    }

    public void update() { 
        currentGameScene.update();
    }

    private void goToScene(SceneType sceneType) {
        currentGameScene.destroy();

        currentGameScene = gameSceneFactory.createScene(sceneType, screenWidth, screenHeight);
        currentGameScene.addPropChangeListener(instance);

        stage.setScene(currentGameScene.getScene());
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        switch (event.getPropertyName()) {
            case "sceneChange":
                goToScene((SceneType) event.getNewValue());
                break;

            case "stateChange":
                break;
                
            default:
                break;
        }
    }
}