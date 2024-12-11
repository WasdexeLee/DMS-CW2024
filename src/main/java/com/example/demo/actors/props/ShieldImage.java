package com.example.demo.actors.props;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a shield image that can be displayed or hidden.
 * This class extends {@link ImageView} to display the shield image.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class ShieldImage extends ImageView {

    /** The name of the image file used for the shield. */
    private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";

    /** The size of the shield image. */
    private static final int SHIELD_SIZE = 200;

    /**
     * Constructs a ShieldImage with the specified initial position.
     * 
     * @param xPosition The initial X position of the shield image.
     * @param yPosition The initial Y position of the shield image.
     */
    public ShieldImage(double xPosition, double yPosition) {
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.setVisible(false);
        this.setFitHeight(SHIELD_SIZE);
        this.setPreserveRatio(true);
    }

    /**
     * Shows the shield image.
     */
    public void showShield() {
        this.setVisible(true);
    }

    /**
     * Hides the shield image.
     */
    public void hideShield() {
        this.setVisible(false);
    }

    /**
     * Moves the shield image vertically by the specified amount.
     * 
     * @param verticalMove The amount to move the shield image vertically.
     */
    public void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }
}