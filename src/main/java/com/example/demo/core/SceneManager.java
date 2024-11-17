package com.example.demo.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.stage.Stage;

public class SceneManager implements PropertyChangeListener{
    private static SceneManager instance;
    private final Game game;
    private final Stage stage;

    private SceneManager(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;

        this.game.addGameStatePropChangeListener(this);
        // this.timeline = new Timeline(new KeyFrame(FRAME_DURATION, e ->
        // game.update()));
    }

    public static SceneManager getInstance(Game game, Stage stage) {
        if (instance == null) {
            instance = new SceneManager(game, stage);
        }

        return instance;
    }

    public void update() { 
        // loggiiiccc

    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        // switch (event) {
        //     case value:
                
        //         break;
        
        //     default:
        //         break;
        // }
    }
}