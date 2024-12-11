package com.example.demo.scenes.levels.services;

import com.example.demo.actors.props.HealthBarDisplay;
import com.example.demo.actors.props.HeartDisplay;

import javafx.scene.Group;
import javafx.scene.layout.HBox;

/**
 * Manages the visual components of a level, including the heart display and health bar.
 * This class provides methods to show, update, and remove these visual elements.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class LevelView {
	
	/** The X position of the heart display. */
	private static final double HEART_DISPLAY_X_POSITION = 5;

	/** The Y position of the heart display. */
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	/** The root group where all visual elements are added. */
	private final Group root;

	/** The heart display component. */
	private final HeartDisplay heartDisplay;

	/** The health bar display component. */
	private HealthBarDisplay healthBarDisplay;
	
	/**
	 * Constructs a LevelView with the specified root group and number of hearts to display.
	 * 
	 * @param root The root group where visual elements are added.
	 * @param heartsToDisplay The number of hearts to display initially.
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
	}
	
	/**
	 * Adds the heart display to the root group.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Retrieves the heart display container.
	 * 
	 * @return The {@link HBox} container containing the heart images.
	 */
	public HBox getHeartDisplay() {
		return heartDisplay.getContainer();
	}

	/**
	 * Removes hearts from the display based on the remaining hearts.
	 * 
	 * @param heartsRemaining The number of hearts remaining.
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	/**
	 * Adds the health bar display to the root group.
	 * 
	 * @param maxHealth The maximum health value for the health bar.
	 */
	public void showHealthBarDisplay(int maxHealth) {
		healthBarDisplay = new HealthBarDisplay(maxHealth);
		root.getChildren().add(healthBarDisplay.getHealthBar());
	}

	/**
	 * Updates the health bar display with the current health value.
	 * 
	 * @param health The current health value.
	 */
	public void updateHealthBarDisplay(int health) {
		healthBarDisplay.updateHealth(health);
	}
}