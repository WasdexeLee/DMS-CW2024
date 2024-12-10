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

public abstract class GameScene {

    private final double screenWidth;
    private final double screenHeight;

    private Group root;
    private ImageView background;
    private Scene scene;

    private PropertyChangeSupport gameSceneSupport;
    protected ArrayList<Node> topNode;

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

    public abstract void update();

    public void addPropChangeListener(PropertyChangeListener listener) {
        gameSceneSupport.addPropertyChangeListener(listener);
    }

    public void removePropChangeListener(PropertyChangeListener listener) {
        gameSceneSupport.removePropertyChangeListener(listener);
    }

    protected void setPropChange(String propType, Object oldProp, Object newProp) {
        gameSceneSupport.firePropertyChange(propType, oldProp, newProp);
    }

    public void destroy() {
        root = null;
        background = null;
        scene = null;
        gameSceneSupport = null;
    }

    public Scene getScene() {
        return scene;
    }

    protected void goToScene(Object newScene) {
        setPropChange("sceneChange", null, newScene);
    }

    protected Group getRoot() {
        return root;
    }

    protected ImageView getBackground() { 
        return background;
    }

    protected double getScreenWidth() {
        return screenWidth;
    }

    protected double getScreenHeight() {
        return screenHeight;
    }

    protected Button createButton(int buttonWidth, int buttonHeight, String imagePath) {
        Button button = new Button();

        button.setStyle(String.format(
            "-fx-background-color: transparent;" + 
            "-fx-background-image: url('%s');" +
            "-fx-background-size: cover;" + 
            "-fx-background-position: center;" +
            "-fx-background-repeat: no-repeat;" 
            , imagePath)
        );

        button.setPrefWidth(buttonWidth);
        button.setPrefHeight(buttonHeight);

        return button;
    }
      
    protected Button initSoundButton() {
        AudioManager audioManagerInstance = AudioManager.getInstance();
        Button soundButton;
        if (audioManagerInstance.getHasSound())
            soundButton = createButton(58, 57, getClass().getResource("/com/example/demo/images/sound_yes.png").toExternalForm());
        else
            soundButton = createButton(58, 57, getClass().getResource("/com/example/demo/images/sound_no.png").toExternalForm());

        soundButton.setLayoutX(getScreenWidth() - 105);
        soundButton.setLayoutY(25);
        soundButton.setOnAction(e -> { if (audioManagerInstance.getHasSound()) {
                                            audioManagerInstance.fireEffectAudio(EffectAudioType.CLICK);
                                            soundButton.setStyle(String.format("-fx-background-color: transparent;" +
                                                    "-fx-background-image: url('%s');" +
                                                    "-fx-background-size: cover;" +
                                                    "-fx-background-position: center;" +
                                                    "-fx-background-repeat: no-repeat;",
                                                    getClass().getResource("/com/example/demo/images/sound_no.png").toExternalForm()));

                                            audioManagerInstance.setHasSound(false);
                                            audioManagerInstance.pauseBackgroundAudio();
                                       } 
                                       else {
                                            soundButton.setStyle(String.format("-fx-background-color: transparent;" +
                                                   "-fx-background-image: url('%s');" +
                                                   "-fx-background-size: cover;" +
                                                   "-fx-background-position: center;" +
                                                   "-fx-background-repeat: no-repeat;" , getClass().getResource("/com/example/demo/images/sound_yes.png").toExternalForm()));

                                            audioManagerInstance.setHasSound(true);
                                            audioManagerInstance.playBackgroundAudio();
                                            audioManagerInstance.fireEffectAudio(EffectAudioType.CLICK);
                                       }
                                    });
        soundButton.setFocusTraversable(false);

        return soundButton;
    }
}