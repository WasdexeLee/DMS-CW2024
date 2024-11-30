package com.example.demo.actors.projectile;

import com.example.demo.actors.Projectile;
import com.example.demo.core.GameLoop;

public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 7;
	private static final double HORIZONTAL_VELOCITY = Math.ceil(300.0 / GameLoop.getInstance(null).get_TARGET_FPS());

	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
	}
}