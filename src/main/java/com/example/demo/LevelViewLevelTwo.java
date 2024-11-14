package com.example.demo;

import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 910;
	private static final int SHIELD_Y_POSITION = 350;
	private final Group root;
	private final ShieldImage shieldImage;
    private final Boss boss;
	
	public LevelViewLevelTwo(Group root, int heartsToDisplay, Boss boss) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
        this.boss = boss;
	}
	
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}
	
	private void showShield() {
		shieldImage.showShield();
	}

	private void hideShield() {
		shieldImage.hideShield();
	}

    @Override
    public void updateShield() {
        if (boss.getIsShielded()) showShield();
        else hideShield();

        shieldImage.moveVertically(boss.getShieldNextMove());
    }
}
