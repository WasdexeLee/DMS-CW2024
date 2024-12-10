package com.example.demo.scenes.levels.services;

import com.example.demo.actors.props.HealthBarDisplay;
import com.example.demo.actors.props.HeartDisplay;

import javafx.scene.Group;
import javafx.scene.layout.HBox;

public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	private final Group root;
	private final HeartDisplay heartDisplay;
	private HealthBarDisplay healthBarDisplay;
	
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
	}
	
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public HBox getHeartDisplay() {
		return heartDisplay.getContainer();
	}

	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	public void showHealthBarDisplay(int maxHealth) {
		healthBarDisplay = new HealthBarDisplay(maxHealth);
		root.getChildren().add(healthBarDisplay.getHealthBar());
	}

	public void updateHealthBarDisplay(int health) {
		healthBarDisplay.updateHealth(health);
	}
}