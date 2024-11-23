package com.example.demo.scenes.levels.services;

import com.example.demo.ActiveActorDestructible;

import java.util.List;

/**
 * Manages collision detection and handling between actors in the game.
 * 
 * This class is a singleton and provides methods to detect and handle collisions
 * between different lists of actors, causing them to take damage upon collision.
 * 
 * @author Wasdexe Lee
 */
public class CollisionManager {
    /** The singleton instance of the CollisionManager. */
    private static CollisionManager instance;

    /**
     * Returns the singleton instance of the CollisionManager.
     * 
     * @return The singleton instance of the CollisionManager.
     */
    public static CollisionManager getInstance() {
        if (instance == null) {
            instance = new CollisionManager();
        }

        return instance;
    }

    /**
     * Handles collisions between two lists of actors by causing each actor involved
     * in the collision to take damage.
     * 
     * @param actors1 The first list of actors.
     * @param actors2 The second list of actors.
     */
    public void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible act1 : actors1) {
            for (ActiveActorDestructible act2 : actors2) {
                if (act1.getBoundsInParent().intersects(act2.getBoundsInParent())) {
                    act1.takeDamage();
                    act2.takeDamage();
                }
            }
        }
    }
}