package com.example.demo.actors;

/**
 * Interface representing a destructible entity in the game.
 * Destructible entities can take damage and be destroyed.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public interface Destructible {

    /**
     * Applies damage to the entity.
     * This method should handle the logic for reducing the entity's health or
     * other damage-related effects.
     */
    void takeDamage();

    /**
     * Destroys the entity.
     * This method should handle the logic for removing the entity from the game
     * or any other destruction-related effects.
     */
    void destroy();
}