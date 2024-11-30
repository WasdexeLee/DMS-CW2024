package com.example.demo.actors.projectile;

import com.example.demo.actors.Projectile;
import com.example.demo.core.GameLoop;

public class EnemyProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 31;
	private static final double HORIZONTAL_VELOCITY = -1 * Math.ceil(200.0 / GameLoop.getInstance(null).get_TARGET_FPS());

	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
	}
}