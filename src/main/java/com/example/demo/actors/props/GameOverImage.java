package com.example.demo.actors.props;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOverImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";
    private static final int WIDTH = 600;

	public GameOverImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()) );
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setFitWidth(WIDTH);
        this.setPreserveRatio(true);
	}
}