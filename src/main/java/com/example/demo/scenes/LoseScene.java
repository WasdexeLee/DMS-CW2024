package com.example.demo.scenes;

import com.example.demo.utils.EnumUtil.SceneType;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LoseScene extends GameScene {
	
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/end/end_background.jpeg";
	private static final SceneType YES_SCENE = SceneType.LEVEL1;
	private static final SceneType NO_SCENE = SceneType.MENU;

	public LoseScene(double screenWidth, double screenHeight) {
		super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight);

        ImageView title = new ImageView(new Image(getClass().getResource("/com/example/demo/images/end/game_over.png").toExternalForm()));
        title.setFitWidth(750); 
        title.setPreserveRatio(true); 

        ImageView playAgain = new ImageView(new Image(getClass().getResource("/com/example/demo/images/end/play_again.png").toExternalForm()));
        playAgain.setFitWidth(450); 
        playAgain.setPreserveRatio(true); 

        DropShadow shadow = new DropShadow();
        shadow.setRadius(35.0); // Increase the blur radius to make it thicker
        shadow.setOffsetX(10.0); // Increase horizontal offset
        shadow.setOffsetY(10.0); // Increase vertical offset
        shadow.setColor(Color.color(0.15, 0.15, 0.15, 1)); // Grey shadow with 75% opacity
        
        // Apply the shadow to the ImageView
        title.setEffect(shadow);
        playAgain.setEffect(shadow);

        // Buttons
        Button yesButton = createButton(getClass().getResource("/com/example/demo/images/end/yes.png").toExternalForm());
        Button noButton = createButton(getClass().getResource("/com/example/demo/images/end/no.png").toExternalForm());
        
        // Button Actions
        yesButton.setOnAction(e -> goToScene(YES_SCENE));
        noButton.setOnAction(e -> goToScene(NO_SCENE));

        // Organize Buttons in a VBox
        HBox buttonBox = new HBox(70, yesButton, noButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Create a spacer
        Region spacer = new Region();
        spacer.setPrefHeight(50);

        // Organize buttonBox and title
        VBox mainLayout = new VBox(40, title, spacer, playAgain, buttonBox);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(100));
        mainLayout.setPrefSize(screenWidth, screenHeight);

        getRoot().getChildren().add(mainLayout);
	}

    @Override
    public void update() {
        // Not needed
    }

    private Button createButton(String imagePath) {
        Button button = new Button();
        button.setStyle(String.format(
            "-fx-background-color: transparent;" + 
            "-fx-background-image: url('%s');" +
            "-fx-background-size: cover;" + // Make the image cover the button area
            "-fx-background-position: center;" +
            "-fx-background-repeat: no-repeat;" 
            , imagePath)
        );
        button.setPrefWidth(140);
        button.setPrefHeight(68);
        return button;
    }
}