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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Represents the menu scene of the game.
 * This class extends {@link GameScene} and provides functionality for displaying the main menu,
 * including buttons for starting the game and exiting.
 *
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class MenuScene extends GameScene {

    /** The path to the background image for the menu scene. */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/menu/menu_background.jpeg";

    /** The next scene to transition to when the start button is clicked. */
    private static final SceneType NEXT_SCENE = SceneType.LEVEL1;

    /**
     * Constructs a {@link MenuScene} with the specified screen width and height.
     *
     * @param screenWidth  The width of the screen.
     * @param screenHeight The height of the screen.
     */
    public MenuScene(double screenWidth, double screenHeight) {
        super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight);

        // Title Image
        ImageView title = new ImageView(new Image(getClass().getResource("/com/example/demo/images/menu/sky_battle.png").toExternalForm()));
        title.setFitWidth(960);
        title.setPreserveRatio(true);

        // Buttons
        Button start = createButton(348, 70, getClass().getResource("/com/example/demo/images/menu/start_game.png").toExternalForm());
        Button exit = createButton(185, 70, getClass().getResource("/com/example/demo/images/menu/exit.png").toExternalForm());

        // Button Actions
        start.setOnAction(e -> {
            goToScene(NEXT_SCENE);
            AudioManager.getInstance().fireEffectAudio(EffectAudioType.CLICK);
        });
        exit.setOnAction(e -> {
            AudioManager.getInstance().fireEffectAudio(EffectAudioType.CLICK);
            System.exit(0);
        });

        // StackPane for buttons
        StackPane startButton = new StackPane(start);
        StackPane exitButton = new StackPane(exit);

        // DropShadow effect
        DropShadow shadow = new DropShadow();
        shadow.setRadius(65.0);
        shadow.setOffsetX(5.0);
        shadow.setOffsetY(10.0);
        shadow.setColor(Color.color(0, 0, 0, 1));

        // Apply the shadow to the ImageView and buttons
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

        // Set background audio for the menu scene
        AudioManager.getInstance().changeBackgroundAudio(BackgroundAudioType.MENU);
    }

    /**
     * Updates the state of the menu scene.
     * This method is not needed for the menu scene.
     */
    @Override
    public void update() {
        // Not needed
    }
}