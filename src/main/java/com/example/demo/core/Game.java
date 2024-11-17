package com.example.demo.core;

import java.beans.PropertyChangeListener;

import javafx.stage.Stage;

public class Game {
    private Stage stage;
    private GameState gameState;
    private GameLoop gameLoop;
    private SceneManager sceneManager;

    public Game(Stage stage) {
        this.stage = stage;
        this.gameState = GameState.getInstance();
        this.gameLoop = GameLoop.getInstance(this);
        this.sceneManager = SceneManager.getInstance(this, this.stage);
    }

    public void start() {
        gameLoop.start();
    }

    public void update() {
        sceneManager.update();
    }

    public void stop() {
        gameLoop.stop();
    }

    public void setStateGoToMenu() {
        gameState.setStateGoToMenu();
    }

    public void setStateStartGame() {
        gameState.setStateStartGame();
    }

    public void setStatePauseGame() {
        gameState.setStatePauseGame();
    }

    public void setStateResumeGame() {
        gameState.setStateResumeGame();
    }

    public void setStateEndGame() {
        gameState.setStateEndGame();
    }

    public void addGameStatePropChangeListener(PropertyChangeListener listener) {
        gameState.addPropChangeListener(listener);
    }
}

// package com.example.demo.controller;

// import java.lang.reflect.Constructor;
// import java.lang.reflect.InvocationTargetException;
// import java.util.Observable;
// import java.util.Observer;

// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Alert.AlertType;
// import javafx.stage.Stage;

// import com.example.demo.LevelParent;

// public class Controller implements Observer {

// // private static final String LEVEL_ONE_CLASS_NAME =
// "com.example.demo.LevelTwo";
// private final Stage stage;
// private LevelParent myLevel;

// public Controller(Stage stage) {
// this.stage = stage;
// }

// public void launchGame() throws ClassNotFoundException,
// NoSuchMethodException, SecurityException,
// InstantiationException, IllegalAccessException, IllegalArgumentException,
// InvocationTargetException {

// stage.show();
// goToLevel(LEVEL_ONE_CLASS_NAME);
// }

// private void goToLevel(String className) throws ClassNotFoundException,
// NoSuchMethodException, SecurityException,
// InstantiationException, IllegalAccessException, IllegalArgumentException,
// InvocationTargetException {
// Class<?> myClass = Class.forName(className);
// Constructor<?> constructor = myClass.getConstructor(double.class,
// double.class);
// myLevel = (LevelParent) constructor.newInstance(stage.getHeight(),
// stage.getWidth());
// myLevel.addObserver(this);
// Scene scene = myLevel.initializeScene();
// stage.setScene(scene);
// myLevel.startGame();

// }

// @Override
// public void update(Observable arg0, Object arg1) {
// try {
// // Assign myLevel (previous finished level) to null and suggest garbage
// collection
// myLevel = null;
// System.gc();

// // Move to next level by passing level class path
// goToLevel((String) arg1);
// } catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
// InstantiationException
// | IllegalAccessException | IllegalArgumentException |
// InvocationTargetException e) {
// Alert alert = new Alert(AlertType.ERROR);
// alert.setContentText(e.getClass().toString());
// alert.show();
// }
// }

// }
