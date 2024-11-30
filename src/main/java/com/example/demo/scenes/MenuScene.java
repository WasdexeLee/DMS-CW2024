package com.example.demo.scenes;

import com.example.demo.utils.EnumUtil.SceneType;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuScene extends GameScene {
	
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/menu_background.jpg";
	private static final SceneType NEXT_SCENE = SceneType.LEVEL1;

	public MenuScene(double screenWidth, double screenHeight) {
		super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight);

        Text title = new Text("Sky Battle");
        title.setFill(Color.WHITE);
        title.setFont(Font.font("Monospaced", FontWeight.BOLD, 120));
        title.setFill(Color.web("#D3D3D3")); // Example color (Tomato)
        title.setStroke(Color.BLACK); // Border color
        title.setStrokeWidth(4); // Border width

        // Buttons
        Button startButton = createButton("Start Game", 240);
        Button exitButton = createButton("Exit", 170);
        
        // Button Actions
        startButton.setOnAction(e -> {
            System.out.println("Start Game selected");
            goToScene(NEXT_SCENE);
        });
        exitButton.setOnAction(e -> System.exit(0));

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

    private Button createButton(String text, double buttonWidth) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #404040; -fx-text-fill: #D3D3D3; -fx-font-family: 'Monospaced'; -fx-font-size: 30; -fx-font-weight: bold;");
        button.setPrefWidth(buttonWidth);
        button.setPrefHeight(70);
        return button;
    }
}