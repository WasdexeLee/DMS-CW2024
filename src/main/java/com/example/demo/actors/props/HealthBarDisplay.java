package com.example.demo.actors.props;

import javafx.scene.control.ProgressBar;

public class HealthBarDisplay {
    private static final double BAR_WIDTH = 500;
    private static final double BAR_HEIGHT = 25;
    private final ProgressBar healthBar;
    private int maxHealth;

    public HealthBarDisplay(int maxHealth) {
        this.maxHealth = maxHealth;
        healthBar = new ProgressBar(1.0);
        healthBar.setPrefWidth(BAR_WIDTH);
        healthBar.setPrefHeight(BAR_HEIGHT);
        healthBar.setLayoutX((1366 - 500) / 2);
        healthBar.setLayoutY(25);

        healthBar.setStyle("-fx-accent: red;"); 
    }

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

    public ProgressBar getHealthBar() {
        return healthBar;
    }
}
