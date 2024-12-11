package com.example.demo.actors.props;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a display of hearts to indicate health or lives in the game.
 * This class manages the creation, display, and removal of heart icons.
 *
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class HeartDisplay {

    /**
     * The name of the image file representing a heart.
     */
    private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";

    /**
     * The height of the heart image.
     */
    private static final int HEART_HEIGHT = 50;

    /**
     * The index of the first item in the container (used for removing hearts).
     */
    private static final int INDEX_OF_FIRST_ITEM = 0;

    /**
     * The container that holds the heart images.
     */
    private HBox container;

    /**
     * The X position of the container.
     */
    private double containerXPosition;

    /**
     * The Y position of the container.
     */
    private double containerYPosition;

    /**
     * The number of hearts to display initially.
     */
    private int numberOfHeartsToDisplay;

    /**
     * Constructs a {@link HeartDisplay} with the specified position and number of hearts to display.
     *
     * @param xPosition          The X position of the container.
     * @param yPosition          The Y position of the container.
     * @param heartsToDisplay    The number of hearts to display initially.
     */
    public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
        this.containerXPosition = xPosition;
        this.containerYPosition = yPosition;
        this.numberOfHeartsToDisplay = heartsToDisplay;
        initializeContainer();
        initializeHearts();
    }

    /**
     * Initializes the container for holding the heart images.
     */
    private void initializeContainer() {
        container = new HBox();
        container.setLayoutX(containerXPosition);
        container.setLayoutY(containerYPosition);
    }

    /**
     * Initializes and adds the specified number of heart images to the container.
     */
    private void initializeHearts() {
        for (int i = 0; i < numberOfHeartsToDisplay; i++) {
            ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));

            heart.setFitHeight(HEART_HEIGHT);
            heart.setPreserveRatio(true);
            container.getChildren().add(heart);
        }
    }

    /**
     * Removes a heart from the display by removing the first heart in the container.
     */
    public void removeHeart() {
        if (!container.getChildren().isEmpty())
            container.getChildren().remove(INDEX_OF_FIRST_ITEM);
    }

    /**
     * Gets the container that holds the heart images.
     *
     * @return The {@link HBox} container containing the heart images.
     */
    public HBox getContainer() {
        return container;
    }
}