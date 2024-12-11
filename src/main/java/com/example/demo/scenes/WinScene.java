package com.example.demo.scenes;

import com.example.demo.audio.services.AudioManager;
import com.example.demo.utils.EnumUtil.BackgroundAudioType;
import com.example.demo.utils.EnumUtil.EffectAudioType;
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

/**
 * Represents the win scene when the player successfully completes the game.
 * This class extends {@link GameScene} to inherit scene element management functionality.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class WinScene extends GameScene {
	
	/** The path to the background image for the win scene. */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/end/end_background.jpeg";

	/** The scene to transition to when the player chooses to play again. */
	private static final SceneType YES_SCENE = SceneType.LEVEL1;

	/** The scene to transition to when the player chooses not to play again. */
	private static final SceneType NO_SCENE = SceneType.MENU;

	/**
	 * Constructs a WinScene with the specified screen dimensions.
	 * 
	 * @param screenWidth The width of the screen.
	 * @param screenHeight The height of the screen.
	 */
	public WinScene(double screenWidth, double screenHeight) {
		super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight);

		// Create the title image
		ImageView title = new ImageView(new Image(getClass().getResource("/com/example/demo/images/end/you_win.png").toExternalForm()));
		title.setFitWidth(750); 
		title.setPreserveRatio(true); 

		// Create the "play again" image
		ImageView playAgain = new ImageView(new Image(getClass().getResource("/com/example/demo/images/end/play_again.png").toExternalForm()));
		playAgain.setFitWidth(450); 
		playAgain.setPreserveRatio(true); 

		// Create a drop shadow effect
		DropShadow shadow = new DropShadow();
		shadow.setRadius(35.0); // Increase the blur radius to make it thicker
		shadow.setOffsetX(10.0); // Increase horizontal offset
		shadow.setOffsetY(10.0); // Increase vertical offset
		shadow.setColor(Color.color(0.15, 0.15, 0.15, 1)); // Grey shadow with 75% opacity
		
		// Apply the shadow to the ImageView
		title.setEffect(shadow);
		playAgain.setEffect(shadow);

		// Create buttons
		Button yesButton = createButton(140, 68, getClass().getResource("/com/example/demo/images/end/yes.png").toExternalForm());
		Button noButton = createButton(140, 68, getClass().getResource("/com/example/demo/images/end/no.png").toExternalForm());
		
		// Set button actions
		yesButton.setOnAction(e -> {
			goToScene(YES_SCENE);
			AudioManager.getInstance().fireEffectAudio(EffectAudioType.CLICK);
		});
		noButton.setOnAction(e -> {
			goToScene(NO_SCENE);
			AudioManager.getInstance().fireEffectAudio(EffectAudioType.CLICK);
		});

		// Organize buttons in an HBox
		HBox buttonBox = new HBox(70, yesButton, noButton);
		buttonBox.setAlignment(Pos.CENTER);

		// Create a spacer
		Region spacer = new Region();
		spacer.setPrefHeight(50);

		// Organize buttonBox and title in a VBox
		VBox mainLayout = new VBox(40, title, spacer, playAgain, buttonBox);
		mainLayout.setAlignment(Pos.TOP_CENTER);
		mainLayout.setPadding(new Insets(100));
		mainLayout.setPrefSize(screenWidth, screenHeight);

		getRoot().getChildren().add(mainLayout);

		// Change the background audio to the win scene audio
		AudioManager.getInstance().changeBackgroundAudio(BackgroundAudioType.WINSCENE);
	}

	/**
	 * Updates the state of the scene. 
     * This method is not needed for the win scene.
	 */
    @Override
    public void update() {
        // Not needed
    }
}