package com.example.demo.actors.props;

import javafx.scene.control.ProgressBar;

/**
 * Represents a health bar display for visualizing the health of an entity.
 * This class uses a {@link ProgressBar} to display the health ratio dynamically.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class HealthBarDisplay {

    /** The width of the health bar. */
    private static final double BAR_WIDTH = 500;

    /** The height of the health bar. */
    private static final double BAR_HEIGHT = 25;

    /** The progress bar used to display the health. */
    private final ProgressBar healthBar;

    /** The maximum health value of the entity. */
    private int maxHealth;

    /**
     * Constructs a HealthBarDisplay with the specified maximum health.
     * 
     * @param maxHealth The maximum health of the entity.
     */
    public HealthBarDisplay(int maxHealth) {
        this.maxHealth = maxHealth;

        healthBar = new ProgressBar(1.0);
        healthBar.setPrefWidth(BAR_WIDTH);
        healthBar.setPrefHeight(BAR_HEIGHT);
        healthBar.setLayoutX((1366 - 500) / 2);
        healthBar.setLayoutY(25);

        // Set the initial style of the health bar
        healthBar.setStyle("-fx-accent: red;");
    }

    /**
     * Updates the health bar display based on the current health.
     * The color of the health bar changes dynamically based on the health ratio.
     * 
     * @param currentHealth The current health of the entity.
     */
    public void updateHealth(int currentHealth) {
        double healthRatio = (double) currentHealth / maxHealth;
        healthBar.setProgress(healthRatio);

        // Change the color dynamically based on health ratio
        if (healthRatio > 0.3) {
            healthBar.setStyle("-fx-accent: red;");
        } else {
            healthBar.setStyle("-fx-accent: yellow;");
        }
    }

    /**
     * Retrieves the progress bar used to display the health.
     * 
     * @return The {@link ProgressBar} used to display the health.
     */
    public ProgressBar getHealthBar() {
        return healthBar;
    }
}