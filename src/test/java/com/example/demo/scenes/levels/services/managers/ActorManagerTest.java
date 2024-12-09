package com.example.demo.scenes.levels.services.managers;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.user.UserPlane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Group;
import javafx.stage.Stage;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ActorManagerTest extends ApplicationTest {

    private ActorManager actorManager;
    private Stage stage;
    private Group mockRoot;
    private UserPlane mockActor1;
    private UserPlane mockActor2;
    private UserPlane mockActor3;

    // Set up the JavaFX stage with the specified title, dimensions, and properties
    @Override
    public void start(Stage stage) throws Exception {
        int SCREEN_WIDTH = 1366;
        int SCREEN_HEIGHT = 762;
        String TITLE = "Sky Battle";

        this.stage = stage;
        this.stage.setTitle(TITLE);
        this.stage.setResizable(false);
        this.stage.setHeight(SCREEN_HEIGHT);
        this.stage.setWidth(SCREEN_WIDTH);

        this.actorManager = ActorManager.getInstance();
        this.mockRoot = new Group();
        this.mockActor1 = mock(UserPlane.class, withSettings().useConstructor(5).defaultAnswer(CALLS_REAL_METHODS));
        this.mockActor2 = mock(UserPlane.class, withSettings().useConstructor(5).defaultAnswer(CALLS_REAL_METHODS));
        this.mockActor3 = mock(UserPlane.class, withSettings().useConstructor(5).defaultAnswer(CALLS_REAL_METHODS));
    }

    // Test logic: Ensure the ActorManager correctly removes destroyed actors.
    @Test
    public void testRemoveDestroyedActors() {
        List<ActiveActorDestructible> actors = new ArrayList<>(Arrays.asList(mockActor1, mockActor2, mockActor3));

        when(mockActor1.getIsDestroyed()).thenReturn(false);
        when(mockActor2.getIsDestroyed()).thenReturn(true);
        when(mockActor3.getIsDestroyed()).thenReturn(false);

        actorManager.removeDestroyedActors(actors, mockRoot);

        assertFalse(actors.contains(mockActor2));
    }

    // Test logic: Ensure the ActorManager correctly updates all actors.
    @Test
    public void testUpdateAllActors() {
        List<ActiveActorDestructible> actors1 = new ArrayList<>(Arrays.asList(mockActor1, mockActor2));
        List<ActiveActorDestructible> actors2 = new ArrayList<>(Arrays.asList(mockActor3));
        List<List<ActiveActorDestructible>> actorsList = new ArrayList<>(Arrays.asList(actors1, actors2));

        actorManager.updateAllActors(actorsList);

        verify(mockActor1).updateActor();
        verify(mockActor2).updateActor();
        verify(mockActor3).updateActor();
    }
}