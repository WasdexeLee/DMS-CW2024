package com.example.demo.scenes.levels.services.managers;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;

import java.util.List;

import javafx.scene.Group;

/**
 * Manages projectiles in the game, including handling projectiles going out of the screen
 * and spawning new projectiles from fighter planes.
 * 
 * This class is a singleton and provides methods to handle projectiles going out of the screen,
 * spawn new projectiles, and manage their lifecycle within the game scene.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class ProjectileManager {

    /** The singleton instance of the ProjectileManager. */
    private static ProjectileManager instance;

    /**
     * Returns the singleton instance of the ProjectileManager.
     * 
     * @return The singleton instance of the ProjectileManager.
     */
    public static ProjectileManager getInstance() {
        if (instance == null) {
            instance = new ProjectileManager();
        }

        return instance;
    }

    /**
     * Handles projectiles that go out of the screen view by destroying them.
     * Checks the position of the projectile against the screenWidth to determine whether it is out of screen view.
     * 
     * @param projectiles The list of projectiles to be checked.
     * @param screenWidth The width of the screen to check whether the projectile is out of screen view.
     */
    public void handleProjectileOutOfScreen(List<ActiveActorDestructible> projectiles, double screenWidth) {
        for (ActiveActorDestructible projectile : projectiles) {
            if (projectile.localToScene(projectile.getBoundsInLocal()).getMaxX() < 0 ||
                    projectile.localToScene(projectile.getBoundsInLocal()).getMinX() > screenWidth) {
                projectile.destroy();
            }
        }
    }

    /**
     * Spawns projectiles from fighter planes and adds them to the list of projectiles and the root group.
     * Calls respective spawn projectile method (fireProjectile) and let the fighter planes to handle the logic 
     * based on the type of fighter planes they are.
     * 
     * @param fighterPlanes The list of fighter planes.
     * @param projectilesList The list of projectiles.
     * @param root The root group to add the projectiles to.
     */
    public void spawnProjectile(List<ActiveActorDestructible> fighterPlanes,
            List<ActiveActorDestructible> projectilesList, Group root) {
        for (ActiveActorDestructible fighterPlane : fighterPlanes) {
            ActiveActorDestructible projectile = ((FighterPlane) fighterPlane).fireProjectile();

            if (projectile != null) {
                root.getChildren().add(projectile);
                projectilesList.add(projectile);
            }
        }
    }
}