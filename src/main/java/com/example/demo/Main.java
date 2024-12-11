package com.example.demo;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.core.Game;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point of the JavaFX application.
 * This class initializes the game window and starts the game.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class Main extends Application {

    /** The width of the game screen in pixels. */
    private static final int SCREEN_WIDTH = 1366;

    /** The height of the game screen in pixels. */
    private static final int SCREEN_HEIGHT = 762;

    /** The title displayed on the game window. */
    private static final String TITLE = "Sky Battle";

    /** The game instance managed by this application. */
    private Game game;

    /**
     * Initializes the game stage and starts the game.
     * 
     * @param stage The primary stage for this application.
     * @throws ClassNotFoundException If the class is not found.
     * @throws NoSuchMethodException If a method is not found.
     * @throws SecurityException If a security violation occurs.
     * @throws InstantiationException If instantiation fails.
     * @throws IllegalAccessException If access is illegal.
     * @throws IllegalArgumentException If an argument is invalid.
     * @throws InvocationTargetException If an exception is thrown by the invoked method.
     */
    @Override
    public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);

        game = Game.getInstance(stage);
        game.init();
    }

    /**
     * Launches the JavaFX application.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        launch();
    }
}