package com.example.demo.scenes.levels.services;

import com.example.demo.ActiveActorDestructible;
import com.example.demo.FighterPlane;
import com.example.demo.UserPlane;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.Group;

public class LevelLogic {
    private static LevelLogic instance;

    public static LevelLogic getInstance() {
        if (instance == null) {
            instance = new LevelLogic();
        }

        return instance;
    }

    public void removeDestroyedActors(List<ActiveActorDestructible> actors, Group root) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(actor -> actor.isDestroyed())
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    public void handleProjectileOutOfScreen(List<ActiveActorDestructible> projectiles, double screenWidth) {
        for (ActiveActorDestructible projectile : projectiles) {
            if (projectile.localToScene(projectile.getBoundsInLocal()).getMaxX() < 0 ||
                    projectile.localToScene(projectile.getBoundsInLocal()).getMinX() > screenWidth) {
                projectile.destroy();
            }
        }
    }

    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy, double screenWidth) {
        return Math.abs(enemy.getTranslateX()) - enemy.getBoundsInParent().getWidth() > screenWidth;
    }

    public void handleEnemyPenetration(ActiveActorDestructible userUnit, List<ActiveActorDestructible> enemyUnits, double screenWidth) {
        for (ActiveActorDestructible enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy, screenWidth)) {
                userUnit.takeDamage();
                enemy.destroy();
            }
        }
    }

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

    public void spawnProjectile(List<ActiveActorDestructible> fighterPlanes, List<ActiveActorDestructible> projectilesList, Group root) {
        for (ActiveActorDestructible fighterPlane : fighterPlanes) {
            ActiveActorDestructible projectile = ((FighterPlane) fighterPlane).fireProjectile();

            if (projectile != null) {
                root.getChildren().add(projectile);
                projectilesList.add(projectile);
            }
        }
    }

    public void updateCurrentNumberOfEnemies(LevelState levelState, List<ActiveActorDestructible> enemyUnits) {
        levelState.setCurrentNumberOfEnemies(enemyUnits.size());
    }

    public void updateKillCount(LevelState levelState, LevelView levelView, List<ActiveActorDestructible> enemyUnits, UserPlane userUnit, int userHealth) {
        int killEnemies = levelState.getCurrentNumberOfEnemies() - enemyUnits.size();
        for (int i = 0; i < killEnemies; i++) {
            userUnit.incrementKillCount();
            levelState.incrementNumberOfKills();
        }

        if (killEnemies > 0)
            updateLevelView(levelView, userHealth);
    }

	private void updateLevelView(LevelView levelView, int userHealth) {
		levelView.removeHearts(userHealth);
	}

	public void addEnemyUnit(ActiveActorDestructible enemy, List<ActiveActorDestructible> enemyUnits, Group root) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

    public void updateAllActors(List<List<ActiveActorDestructible>> actorsList) {
        for (List<ActiveActorDestructible> actors : actorsList){
            for (ActiveActorDestructible actor : actors)
                actor.updateActor();
        }
    }
}