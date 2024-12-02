package com.example.demo.scenes;

import com.example.demo.utils.EnumUtil.SceneType;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MenuScene extends GameScene {
	
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/menu/menu_background.jpeg";
	private static final SceneType NEXT_SCENE = SceneType.LEVEL1;

	public MenuScene(double screenWidth, double screenHeight) {
		super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight);

        ImageView title = new ImageView(new Image(getClass().getResource("/com/example/demo/images/menu/sky_battle.png").toExternalForm()));
        title.setFitWidth(960); 
        title.setPreserveRatio(true); 

        // Buttons
        Button startButton = createButton(346, getClass().getResource("/com/example/demo/images/menu/start_game.png").toExternalForm());
        Button exitButton = createButton(185,getClass().getResource("/com/example/demo/images/menu/exit.png").toExternalForm() );
        
        // Button Actions
        startButton.setOnAction(e -> goToScene(NEXT_SCENE));
        exitButton.setOnAction(e -> System.exit(0));

        DropShadow shadow = new DropShadow();
        shadow.setRadius(65.0); // Increase the blur radius to make it thicker
        shadow.setOffsetX(5.0); // Increase horizontal offset
        shadow.setOffsetY(10.0); // Increase vertical offset
        shadow.setColor(Color.color(0.15, 0.15, 0.15, 1)); // Grey shadow with 75% opacity
        
        // Apply the shadow to the ImageView
        startButton.setEffect(shadow);
        exitButton.setEffect(shadow);

        // Organize Buttons in a VBox
        VBox buttonBox = new VBox(40, startButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Organize buttonBox and title
        VBox mainLayout = new VBox(120, title, buttonBox);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(100));
        mainLayout.setPrefSize(screenWidth, screenHeight);

        getRoot().getChildren().add(mainLayout);
	}

    @Override
    public void update() {
        // Not needed
    }

    private Button createButton(double buttonWidth, String imagePath) {
        Button button = new Button();
        // button.setStyle("-fx-background-color: #404040; -fx-text-fill: #D3D3D3; -fx-font-family: 'Monospaced'; -fx-font-size: 30; -fx-font-weight: bold;");
        button.setStyle(String.format(
            "-fx-background-color: transparent;" + 
            "-fx-background-image: url('%s');" +
            "-fx-background-size: cover;" + // Make the image cover the button area
            "-fx-background-position: center;" +
            "-fx-background-repeat: no-repeat;" 
            , imagePath)
        );
        button.setPrefWidth(buttonWidth);
        button.setPrefHeight(70);
        return button;
    }
}