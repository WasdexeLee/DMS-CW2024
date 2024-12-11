package com.example.demo.scenes.levels.services.managers;

import java.util.List;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.audio.services.AudioManager;
import com.example.demo.utils.EnumUtil.EffectAudioType;

/**
 * Manages collision detection and handling between actors in the game.
 * 
 * This class is a singleton and provides methods to detect and handle collisions
 * between different lists of actors, causing them to take damage upon collision.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
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
     * Given actors collide, both will take damage.
     * 
     * @param actors1          The first list of actors.
     * @param actors2          The second list of actors.
     * @param effectAudioType  The type of effect audio to play upon collision.
     */
    public void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2, EffectAudioType effectAudioType) {
        for (ActiveActorDestructible act1 : actors1) {
            for (ActiveActorDestructible act2 : actors2) {
                // Check if the bounding boxes of the actors intersect
                if (act1.getBoundsInParent().intersects(act2.getBoundsInParent())) {
                    // Cause both actors to take damage
                    act1.takeDamage();
                    act2.takeDamage();

                    // Play the specified effect audio if provided
                    if (effectAudioType != null)
                        AudioManager.getInstance().fireEffectAudio(effectAudioType);
                }
            }
        }
    }
}