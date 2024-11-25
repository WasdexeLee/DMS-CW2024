package com.example.demo.scenes.levels.services.managers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.actors.ActiveActorDestructible;

import javafx.scene.Group;

/**
 * Manages the lifecycle and updates of actors in the game.
 * 
 * This class is a singleton and provides methods to remove destroyed actors,
 * update all actors, and manage their lifecycle within the game scene.
 * 
 * @author Wasdexe Lee
 */
public class ActorManager {
    /** The singleton instance of the ActorManager. */
    private static ActorManager instance;

    /**
     * Returns the singleton instance of the ActorManager.
     * 
     * @return The singleton instance of the ActorManager.
     */
    public static ActorManager getInstance() {
        if (instance == null) {
            instance = new ActorManager();
        }

        return instance;
    }

    /**
     * Removes destroyed actors from the given list and the root group.
     * 
     * @param actors The list of actors to check for destroyed actors.
     * @param root   The root group to remove the destroyed actors from.
     */
    public void removeDestroyedActors(List<ActiveActorDestructible> actors, Group root) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(actor -> actor.getIsDestroyed())
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    /**
     * Updates all actors in the given list of actor lists.
     * 
     * @param actorsList The list of lists of actors to update.
     */
    public void updateAllActors(List<List<ActiveActorDestructible>> actorsList) {
        for (List<ActiveActorDestructible> actors : actorsList) {
            for (ActiveActorDestructible actor : actors)
                actor.updateActor();
        }
    }
}