package com.example.demo.scenes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import com.example.demo.audio.services.AudioManager;
import com.example.demo.utils.EnumUtil.EffectAudioType;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents an abstract base class for game scenes in the application.
 * This class provides common functionality for managing scene elements,
 * handling property change events, and managing the scene's background and UI components.
 *
 * @author WasdexeLee (Lee Jia Zhe)
 */
public abstract class GameScene {

    private final double screenWidth;
    private final double screenHeight;

    private Group root;
    private ImageView background;
    private Scene scene;

    /** The property change support for handling property change events. */
    private PropertyChangeSupport gameSceneSupport;

    /** A list of top-level nodes that should always remain on top. */
    protected ArrayList<Node> topNode;

    /**
     * Constructs a {@link GameScene} with the specified background image, screen width, and screen height.
     * Initializes necessary components like SoundButton.
     * Added a root.getChildren listener. 
     * To implement updating UI elements in {@code topNode} ArrayList to always stay on top of the screen 
     *
     * @param backgroundImageName The path to the background image.
     * @param screenWidth         The width of the screen.
     * @param screenHeight        The height of the screen.
     */
    public GameScene(String backgroundImageName, double screenWidth, double screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.root = new Group();
        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
        this.scene = new Scene(this.root, this.screenWidth, screenHeight);

        this.gameSceneSupport = new PropertyChangeSupport(this);

        this.background.setFocusTraversable(true);
        this.background.setFitWidth(this.screenWidth);
        this.background.setFitHeight(screenHeight);
        this.root.getChildren().add(this.background);
        this.background.requestFocus();

        this.topNode = new ArrayList<Node>();

        Button tempButton = initSoundButton();
        this.root.getChildren().add(tempButton);
        this.topNode.add(tempButton);

        this.root.getChildren().addListener((ListChangeListener<Node>) change -> {
            while (change.next()) {
                ObservableList<Node> tempChildren = this.root.getChildren();
                if (change.wasAdded()) {
                    if (!(change.getAddedSubList().stream().anyMatch(topNode::contains))) {
                        for (Node node : topNode) {
                            if (tempChildren.contains(node)) {
                                Platform.runLater(() -> {
                                    this.root.getChildren().remove(node);
                                    this.root.getChildren().add(node);
                                });
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Updates the state of the scene.
     * This method must be implemented by subclasses to define specific behavior.
     */
    public abstract void update();

    /**
     * Adds a property change listener to the scene.
     *
     * @param listener The listener to add.
     */
    public void addPropChangeListener(PropertyChangeListener listener) {
        gameSceneSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from the scene.
     *
     * @param listener The listener to remove.
     */
    public void removePropChangeListener(PropertyChangeListener listener) {
        gameSceneSupport.removePropertyChangeListener(listener);
    }

    /**
     * Fires a property change event.
     *
     * @param propType The type of property change.
     * @param oldProp  The old property value.
     * @param newProp  The new property value.
     */
    protected void setPropChange(String propType, Object oldProp, Object newProp) {
        gameSceneSupport.firePropertyChange(propType, oldProp, newProp);
    }

    /**
     * Destroys the scene by releasing resources.
     */
    public void destroy() {
        root = null;
        background = null;
        scene = null;
        gameSceneSupport = null;
    }

    /**
     * Gets the scene object.
     *
     * @return The {@link Scene} object.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Changes the current scene to a new scene.
     *
     * @param newScene The new scene to transition to.
     */
    protected void goToScene(Object newScene) {
        setPropChange("sceneChange", null, newScene);
    }

    /**
     * Gets the root node of the scene.
     *
     * @return The {@link Group} root node.
     */
    protected Group getRoot() {
        return root;
    }

    /**
     * Gets the background image of the scene.
     *
     * @return The {@link ImageView} background.
     */
    protected ImageView getBackground() {
        return background;
    }

    /**
     * Gets the width of the screen.
     *
     * @return The screen width.
     */
    protected double getScreenWidth() {
        return screenWidth;
    }

    /**
     * Gets the height of the screen.
     *
     * @return The screen height.
     */
    protected double getScreenHeight() {
        return screenHeight;
    }

    /**
     * Creates a button with the specified width, height, and background image.
     * To be used by most of subclass.
     * Implemented to comply with pull up method inheritence 
     *
     * @param buttonWidth The width of the button.
     * @param buttonHeight The height of the button.
     * @param imagePath The path to the button's background image.
     * @return The created {@link Button}.
     */
    protected Button createButton(int buttonWidth, int buttonHeight, String imagePath) {
        Button button = new Button();

        button.setStyle(String.format(
                "-fx-background-color: transparent;" +
                "-fx-background-image: url('%s');" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;" +
                "-fx-background-repeat: no-repeat;", imagePath));

        button.setPrefWidth(buttonWidth);
        button.setPrefHeight(buttonHeight);

        return button;
    }

    /**
     * Initializes the sound button, which toggles the sound on and off.
     * Also implemented using pull up method.
     * Implements soundButton actions for calling AudioManager to pause and resume Effect and Background Audio
     *
     * @return The created {@link Button} for sound control.
     */
    protected Button initSoundButton() {
        AudioManager audioManagerInstance = AudioManager.getInstance();
        Button soundButton;
        if (audioManagerInstance.getHasSound())
            soundButton = createButton(58, 57, getClass().getResource("/com/example/demo/images/props/sound_yes.png").toExternalForm());
        else
            soundButton = createButton(58, 57, getClass().getResource("/com/example/demo/images/props/sound_no.png").toExternalForm());

        soundButton.setLayoutX(getScreenWidth() - 105);
        soundButton.setLayoutY(25);
        soundButton.setOnAction(e -> {
            if (audioManagerInstance.getHasSound()) {
                audioManagerInstance.fireEffectAudio(EffectAudioType.CLICK);
                soundButton.setStyle(String.format("-fx-background-color: transparent;" +
                        "-fx-background-image: url('%s');" +
                        "-fx-background-size: cover;" +
                        "-fx-background-position: center;" +
                        "-fx-background-repeat: no-repeat;",
                        getClass().getResource("/com/example/demo/images/props/sound_no.png").toExternalForm()));

                audioManagerInstance.setHasSound(false);
                audioManagerInstance.pauseBackgroundAudio();
            } else {
                soundButton.setStyle(String.format("-fx-background-color: transparent;" +
                        "-fx-background-image: url('%s');" +
                        "-fx-background-size: cover;" +
                        "-fx-background-position: center;" +
                        "-fx-background-repeat: no-repeat;",
                        getClass().getResource("/com/example/demo/images/props/sound_yes.png").toExternalForm()));

                audioManagerInstance.setHasSound(true);
                audioManagerInstance.playBackgroundAudio();
                audioManagerInstance.fireEffectAudio(EffectAudioType.CLICK);
            }
        });
        soundButton.setFocusTraversable(false);

        return soundButton;
    }
}