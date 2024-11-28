package com.example.demo;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.core.Game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1366;
	private static final int SCREEN_HEIGHT = 762;
	private static final String TITLE = "Sky Battle";
    private Game game;

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

	public static void main(String[] args) {
		launch();
	}
}