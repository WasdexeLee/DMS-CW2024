package com.example.demo.actors.props;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShieldImage extends ImageView {

    private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
    private static final int SHIELD_SIZE = 200;

    public ShieldImage(double xPosition, double yPosition) {
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.setVisible(false);
        this.setFitHeight(SHIELD_SIZE);
        this.setPreserveRatio(true);
    }

    public void showShield() {
        this.setVisible(true);
    }

    public void hideShield() {
        this.setVisible(false);
    }

    /**
     * Moves the shield image vertically based on boss plane movement.
     * 
     * @param verticalMove The amount to setTranslateY of shieldImage (move vertically).
     */
    public void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }
}