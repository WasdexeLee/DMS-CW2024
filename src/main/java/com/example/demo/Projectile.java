package com.example.demo;

import com.example.demo.utils.LoggerUtil;

public abstract class Projectile extends ActiveActorDestructible {

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	@Override
	public void takeDamage() {
		this.destroy();
	}

	@Override
	public abstract void updatePosition();

    /**
     * To be called by garbage collector to verify memory release
     */
    @Override
    protected void finalize() throws Throwable {
        LoggerUtil.logger.info(getClass().getName() + " class is garbage collected");
    }
}
