package com.example.demo.actors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Abstract class representing an active actor in the game.
 * Active actors are game entities that can move and perform actions.
 * This class extends {@link ImageView} to display the actor's image.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public abstract class ActiveActor extends ImageView {

    /** The base location of the images used by actors. */
    private static final String IMAGE_LOCATION = "/com/example/demo/images/";

    /**
     * Constructs an ActiveActor with the specified image, height, and initial position.
     * 
     * @param imageName The name of the image file to be used for the actor.
     * @param imageHeight The height to which the image should be scaled.
     * @param initialXPos The initial X position of the actor.
     * @param initialYPos The initial Y position of the actor.
     */
    public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
        this.setLayoutX(initialXPos);
        this.setLayoutY(initialYPos);
        this.setFitHeight(imageHeight);
        this.setPreserveRatio(true);
    }

    /**
     * Updates the state of the actor.
     * This method must be implemented by subclasses to define specific behavior.
     */
    public abstract void updateActor();

    /**
     * Moves the actor horizontally by the specified amount.
     * 
     * @param horizontalMove The amount to move the actor horizontally.
     */
    protected void moveHorizontally(double horizontalMove) {
        this.setTranslateX(getTranslateX() + horizontalMove);
    }

    /**
     * Moves the actor vertically by the specified amount.
     * 
     * @param verticalMove The amount to move the actor vertically.
     */
    protected void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }
}