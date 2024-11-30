package com.example.demo.actors.projectile;

import com.example.demo.actors.Projectile;
import com.example.demo.core.GameLoop;

public class BossProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "fireball.png";
	private static final int IMAGE_HEIGHT = 75;
	private static final double HORIZONTAL_VELOCITY = -1 * Math.ceil(300.0 / GameLoop.getInstance(null).get_TARGET_FPS());
	private static final int INITIAL_X_POSITION = 950;

	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos, HORIZONTAL_VELOCITY);
	}
}