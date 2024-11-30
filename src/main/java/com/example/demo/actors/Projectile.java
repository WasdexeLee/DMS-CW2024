package com.example.demo.actors;

import com.example.demo.utils.LoggerUtil;

public abstract class Projectile extends ActiveActorDestructible {

    private final double horizontalVelocity;

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos, double horizontalVelocity) {
		super(imageName, imageHeight, initialXPos, initialYPos);

        this.horizontalVelocity = horizontalVelocity;
	}

	@Override
    public void updateActor() {
        moveHorizontally(horizontalVelocity);
    }

	@Override
	public void takeDamage() {
		this.destroy();
	}

    /**
     * To be called by garbage collector to verify memory release
     */
    @Override
    protected void finalize() throws Throwable {
        LoggerUtil.logger.info(getClass().getName() + " class is garbage collected");
    }
}