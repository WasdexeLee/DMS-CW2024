package com.example.demo.scenes;

import com.example.demo.utils.EnumUtil.SceneType;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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
        Button start = createButton(348, 70, getClass().getResource("/com/example/demo/images/menu/start_game.png").toExternalForm());
        Button exit = createButton(185, 70, getClass().getResource("/com/example/demo/images/menu/exit.png").toExternalForm() );
        
        // Button Actions
        start.setOnAction(e -> goToScene(NEXT_SCENE));
        exit.setOnAction(e -> System.exit(0));

        StackPane startButton = new StackPane(start);
        StackPane exitButton = new StackPane(exit);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(65.0); 
        shadow.setOffsetX(5.0); 
        shadow.setOffsetY(10.0);
        shadow.setColor(Color.color(0, 0, 0, 1)); 
        
        // Apply the shadow to the ImageView
        title.setEffect(shadow);
        startButton.setEffect(shadow);
        exitButton.setEffect(shadow);

        // Organize Buttons in a VBox
        VBox buttonBox = new VBox(40, startButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Organize buttonBox and title
        VBox mainLayout = new VBox(140, title, buttonBox);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(90));
        mainLayout.setPrefSize(screenWidth, screenHeight);

        getRoot().getChildren().add(mainLayout);
	}

    @Override
    public void update() {
        // Not needed
    }
}