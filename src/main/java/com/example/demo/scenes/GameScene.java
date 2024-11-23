package com.example.demo.scenes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameScene {

    private final double screenWidth;

    private Group root;
    private ImageView background;
    private Scene scene;

    private PropertyChangeSupport gameSceneSupport;

    public GameScene(String backgroundImageName, double screenWidth, double screenHeight) {
        this.screenWidth = screenWidth;

        this.root = new Group();
        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
        this.scene = new Scene(this.root, this.screenWidth, screenHeight);

        this.gameSceneSupport = new PropertyChangeSupport(this);

        this.background.setFocusTraversable(true);
        this.background.setFitWidth(this.screenWidth);
        this.background.setFitHeight(screenHeight);
        this.root.getChildren().add(this.background);
        this.background.requestFocus();
    }

    public void addPropChangeListener(PropertyChangeListener listener) {
        gameSceneSupport.addPropertyChangeListener(listener);
    }

    private void setPropChange(String propType, Object oldProp, Object newProp) {
        gameSceneSupport.firePropertyChange(propType, oldProp, newProp);
    }

    public abstract void update();

    public void destroy() {
        root = null;
        background = null;
        scene = null;
        gameSceneSupport = null;
    }

    public void goToScene(Object newScene) {
        setPropChange("sceneChange", null, newScene);
    }

    public Scene getScene() {
        return scene;
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
}