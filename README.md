# Sky Battle



## Background Music Toggle Feature
[Background Music Toggle Feature](#background-music-toggle-feature)
  <a id="invocationtargetexception-fix"></a>




## Overview
This is a simple JavaFX game created using Java and JavaFX. The game features basic gameplay mechanics and is designed to demonstrate the use of JavaFX for creating graphical applications.



### Controls

- **Arrow Keys**: Move the player character.
- **Spacebar**: Perform an action (e.g., jump, shoot).



## Git Commits 
The Git Commits in this Repo abides to the Atomic Commits style as best as possible for easier Commit tracing. Thus, a larger ammount of Commits are present and are expected due to the styling of Commits.




## GitHub Repository
[Link to GitHub Repository](https://github.com/yourusername/your-repo)




## Compilation Instructions
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/your-repo.git
   cd your-repo
   ```

2. **Install Dependencies:**
   - Ensure you have Java JDK 11 or later installed.
   - Install Maven if not already installed:
     ```bash
     sudo apt-get install maven
     ```

3. **Compile the Code:**
   ```bash
   mvn clean install
   ```

4. **Run the Application:**
   ```bash
   java -jar target/your-project.jar
   ```




## Bug Fixes
- **Fixed InvocationTargetException Thrown Alert Box**: Exception thrown due to wrong naming and not using name constant declared | fixed by renaming and using constant

- **Added stopTimeline to Stop Game Logic**: Added and called stopTimeline method to stop timeline from running game logic endlessly, which checks and calls goToNextLevel() method, which endlesssly create new LevelTwo instances until garbage collection

- **Cropped Image to Fix HitBox Too Large**: Cropped image to reduce HitBox, recalculated and reassign image scalling

- **Modified EnemyPlane Despawn**: Modified EnemyPlane to only despawn if entire plane has left screen view instead of only the front of the EnemyPlane touching the edge of screen

- **Added Deletion of Projectiles**: Deletion of projectiles when projectiles have move out of the screen

- **Fixed ShieldImage Display**: Fixed BossPlane ShieldImage not displaying issue

- **Fixed EnemyPlane Penetration Bug**: Fixed EnemyPlane penetration triggering kill count +1 (Penetration of EnemyPlane was previously handled as an EnemyPlane kill)

- **Fixed EnemyPlane Collision Bug**: Fixed EnemyPlane collision with UserPlane triggering kill count +1 (Collision of UserPlane and EnemyPlane was previously handled as an EnemyPlane kill)

## Implemented and Working Properly

- **Feature 1: User Authentication**
  - Description: Users can sign up and log in securely. Passwords are hashed and stored securely.
  - Location: `src/main/java/com/example/auth/UserAuth.java`


## Implemented but Not Working Properly

- **Feature 3: Email Notifications**
  - Description: Users should receive email notifications upon certain actions. Currently, the email sending functionality is not working due to issues with the SMTP server configuration.
  - Issues: SMTP server settings are not correctly configured, leading to failed email deliveries.
  - Steps Taken: Tried updating the SMTP server details in the configuration file (`src/main/resources/application.properties`), but the issue persists.

## Features Not Implemented

- **Feature 4: Social Media Integration**
  - Description: Integration with social media platforms for sharing content.
  - Reason: Due to time constraints and the complexity of integrating multiple APIs, this feature was not implemented.


## New Java Class

<br>
<a id="nclass-game"></a>

1. **Game.java**
  - **Location:** `src/main/java/com/example/demo/core/Game.java`
  - **Package:** `com.example.demo.core`
  - **Purpose:** The core game manager that handles the game's lifecycle, state management, and scene updates. It implements `PropertyChangeListener` to listen for state changes and react accordingly. This class serves as a central point for managing the game's overall behavior and transitions between scenes based on property change events.
  - **Methods:**
    - `getInstance(Stage stage)`: Returns the singleton instance of the Game class.
    - `init()`: Initializes and displays the game stage.
    - `update()`: Calls update on the game scene manager.
    - `setStateStartGame()`, `setStatePauseGame()`, `setStateResumeGame()`, `setStateEndGame()`: Methods to set the game state.
    - `getCurrentState()`: Retrieves the current state of the game.
    - `addGameStatePropChangeListener(PropertyChangeListener listener)`: Adds a property change listener to the game state.
    - `propertyChange(PropertyChangeEvent event)`: Handles property change events from the game state.
  - **Design Patterns:**
    - **Singleton Pattern:** The `Game` class is implemented as a singleton to ensure that there is only one instance of the game manager throughout the application. This ensures consistent state management and centralized control over the game's lifecycle.
    - **Observer Pattern:** The `Game` class as an `Observer` implements the `PropertyChangeListener` interface to observe and react to changes in the game state. This pattern decouples the game manager from the game state, allowing for flexible and dynamic state transitions.
    - **Mediator Pattern (Implicit):** The `Game` class acts as a mediator between different components of the game, such as the `GameState`, `GameLoop`, and `GameSceneManager`. It coordinates their interactions and ensures that they work together seamlessly.
    - **Extensibility:** The `Game` class is designed to be the central hub for managing the game's lifecycle and state transitions. This makes it easy to extend the game by adding new states or modifying existing ones without affecting other parts of the application.
    - **Extensibility:** The use of the `PropertyChangeListener` interface allows for easy integration of new event-driven behaviors, enhancing the game's flexibility and responsiveness.
    - **Separation of Concerns:** The `Game` class focuses on managing the game's overall behavior and state transitions. It delegates specific tasks, such as scene management and game loop updates, to other components (`GameSceneManager`, `GameLoop`), promoting a clear separation of concerns by centralizing state management and event handling in the `Game` class, other components can focus on their specific tasks without worrying about the overall game state.


<br>
<a id="nclass-gamestate"></a>

2. **GameState.java**
  - **Location:** `src/main/java/com/example/demo/core/GameState.java`
  - **Package:** `com.example.demo.core`
  - **Purpose:** Manages the state of the game and notifies listeners of state changes. It uses the `PropertyChangeSupport` mechanism to handle state change events.
  - **Methods:**
    - `getInstance()`: Returns the singleton instance of the GameState class.
    - `addPropChangeListener(PropertyChangeListener listener)`: Adds a property change listener to the game state.
    - `setPropChange(String propType, Object oldProp, Object newProp)`: Fires a property change event.
    - `getCurrentState()`: Retrieves the current state of the game.
    - `setCurrentState(State state)`: Sets the current state of the game and notifies listeners using `PropertyChangeSupport`.
    - `setStateStartGame()`, `setStatePauseGame()`, `setStateResumeGame()`, `setStateEndGame()`: Methods to set the game state.
  - **Design Patterns:**
    - **Singleton Pattern:** The `GameState` class is implemented as a singleton to ensure that there is only one instance of the game state throughout the application. This ensures consistent state management across different parts of the application.
    - **Observer Pattern:** The `GameState` class acts as an observable object `Observable`, using `PropertyChangeSupport` to notify registered listeners `Observers` of state changes. This pattern enhances the flexibility and responsiveness of the application by decoupling the state management from the components that depend on it.
    - **Extensibility:** The `GameState` class is designed to be reusable and extensible. New states can be added by extending the `State` enum, and the `setCurrentState()` method can be updated to handle additional state transitions.
    - **Separation of Concerns:** The `GameState` class focuses solely on managing the game's state and notifying listeners of state changes. It does not handle gameplay logic, scene updates, or other aspects of the game, promoting a clear separation of concerns by centralizing state management in the `GameState` class, other components can focus on their specific tasks without worrying about the overall game state.


<br>
<a id="nclass-gameloop"></a>

3. **GameLoop.java**
  - **Location:** `src/main/java/com/example/demo/core/GameLoop.java`
  - **Package:** `com.example.demo.core`
  - **Purpose:** Manages the game loop, ensuring the game updates at a consistent frame rate. It uses JavaFX's `Timeline` and `KeyFrame` to achieve the desired frame rate.
  - **Methods:**
    - `getInstance(Game game)`: Returns the singleton instance of the `GameLoop`.
    - `start()`: Starts the game loop.
    - `stop()`: Stops the game loop.
    - `get_TARGET_FPS()`: Retrieves the target frames per second (FPS) for the game loop.
  - **Design Patterns:**
    - **Singleton Pattern:** The `GameLoop` class is implemented as a singleton to ensure that there is only one instance of the game loop throughout the application. This ensures consistent frame rate management and centralized control over the game's rendering process.
    - **Command Pattern (Implicit):** The `KeyFrame` in the `GameLoop` acts as a command that triggers the `game.update()` method at each frame. This decouples the game loop from the game's update logic, allowing for flexible and modular game updates.
    - **Extensibility:** The `GameLoop` class is designed to be reusable and extensible. The target frame rate (`TARGET_FPS`) can be adjusted to suit different performance requirements, and the `KeyFrame` can be modified to include additional update logic if needed.
    - **Separation of Concerns:** The `GameLoop` class focuses solely on managing the game's frame rate and rendering process. It does not handle gameplay logic, state management, or other aspects of the game, promoting a clear separation of concerns by delegating the game update logic to the `Game` class (`game.update()`), the `GameLoop` avoids becoming tightly coupled to specific game implementations.


<br>
<a id="nclass-gamescene"></a>

4. **GameScene.java**
  - **Location:** `src/main/java/com/example/demo/scenes/GameScene.java`
  - **Package:** `com.example.demo.scenes`
  - **Purpose:** Represents an abstract base class for game scenes in the application. It provides common functionality for managing scene elements, handling property change events, and managing the scene's background and UI components.
  - **Methods:**
    - `update()`: Abstract method to update the state of the scene.
    - `addPropChangeListener(PropertyChangeListener listener)`: Adds a property change listener to the scene.
    - `removePropChangeListener(PropertyChangeListener listener)`: Removes a property change listener from the scene.
    - `setPropChange(String propType, Object oldProp, Object newProp)`: Fires a property change event.
    - `destroy()`: Destroys the scene by releasing resources.
    - `getScene()`: Gets the scene object.
    - `goToScene(Object newScene)`: Changes the current scene to a new scene.
    - `getRoot()`: Gets the root node of the scene.
    - `getBackground()`: Gets the background image of the scene.
    - `getScreenWidth()`: Gets the width of the screen.
    - `getScreenHeight()`: Gets the height of the screen.
    - `createButton(int buttonWidth, int buttonHeight, String imagePath)`: Creates a button with the specified width, height, and background image.
    - `initSoundButton()`: Initializes the sound button, which toggles the sound on and off.
  - **Design Patterns:**
    - **Template Method Pattern:** The `GameScene` class defines a template for creating game scenes, with the `update()` method acting as the template method. Subclasses must implement this method to define specific behavior for updating the scene.
    - **Observer Pattern:** The `PropertyChangeSupport` mechanism in `GameScene` allows it to act as an observable object, notifying registered listeners (observers) of property changes. This pattern is used to decouple the scene from its listeners, promoting loose coupling.
    - **Builder Pattern (Implicit):** The `createButton()` method uses a fluent interface to construct buttons with specific properties (width, height, and background image). This simplifies the creation of buttons and adheres to the Builder pattern.
    - **Extensibility:** The `GameScene` class is designed to be extended by subclasses, allowing new scene types to be added without modifying the existing code. This adheres to the Open/Closed Principle.
    - **Separation of Concerns:** The `GameScene` class focuses solely on managing scene-related functionality, such as UI components, background management, and property change events. It does not handle game logic or business rules, promoting a clear separation of concerns.


<br>
<a id="nclass-menuscene"></a>

5. **MenuScene.java**
  - **Location:** `src/main/java/com/example/demo/scenes/MenuScene.java`
  - **Package:** `com.example.demo.scenes`
  - **Purpose:** Represents the menu scene of the game. It extends `GameScene` and provides functionality for displaying the main menu, including buttons for starting the game and exiting.
  - **Methods:**
    - `update()`: Updates the state of the menu scene. This method is not needed for the menu scene.
  - **Design Patterns:**
    - **Template Method Pattern (Inherited):** The `MenuScene` class extends `GameScene`, which defines a template for managing scene elements. The `update()` method is inherited from `GameScene` but is not needed for the menu scene, demonstrating the flexibility of the template method pattern.
    - **Factory Pattern (Implicit):** The `MenuScene` is created by the `GameSceneFactory`, which encapsulates the creation logic of game scenes. This pattern allows for easy extension of the system by adding new scene types without modifying the existing code.
    - **Facade Pattern (Inherited):** The `MenuScene` class uses the `GameScene` facade to manage scene elements, such as buttons and images, without needing to handle low-level details. This simplifies the implementation of the menu scene.
    - **Extensibility:** The use of the `GameSceneFactory` ensures that adding new scene types (e.g., a "SettingsScene") involves only extending the `SceneType` enum and updating the factory, adhering to the Open/Closed Principle.
    - **Separation of Concerns:** The `MenuScene` class focuses solely on the visual and interactive elements of the menu scene. It does not handle gameplay logic, state management, or other aspects of the game, promoting a clear separation of concerns by delegating scene creation and management to the `GameSceneFactory` and `GameScene` classes, the `MenuScene` class avoids becoming tightly coupled to specific implementations.
     

<br>
<a id="nclass-winscene"></a>

6. **WinScene.java**
  - **Location:** `src/main/java/com/example/demo/scenes/WinScene.java`
  - **Package:** `com.example.demo.scenes`
  - **Purpose:** Represents the win scene when the player successfully completes the game. It extends `GameScene` to inherit scene element management functionality.
  - **Methods:**
    - `update()`: Updates the state of the scene. This method is not needed for the win scene.
  - **Design Patterns:**
    - **Template Method Pattern (Inherited):** The `WinScene` class extends `GameScene`, which defines a template for managing scene elements. The `update()` method is inherited from `GameScene` but is not needed for the win scene, demonstrating the flexibility of the template method pattern.
    - **Factory Pattern (Implicit):** The `WinScene` is created by the `GameSceneFactory`, which encapsulates the creation logic of game scenes. This pattern allows for easy extension of the system by adding new scene types without modifying the existing code.
    - **Facade Pattern (Inherited):** The `WinScene` class uses the `GameScene` facade to manage scene elements, such as buttons and images, without needing to handle low-level details. This simplifies the implementation of the win scene.
    - **Extensibility:** The use of the `GameSceneFactory` ensures that adding new scene types (e.g., a "WinScene" for a specific level) involves only extending the `SceneType` enum and updating the factory, adhering to the Open/Closed Principle.
    - **Separation of Concerns:** The `WinScene` class focuses solely on the visual and interactive elements of the win scene. It does not handle gameplay logic, state management, or other aspects of the game, promoting a clear separation of concerns by delegating scene creation and management to the `GameSceneFactory` and `GameScene` classes, the `WinScene` class avoids becoming tightly coupled to specific implementations.


<br>
<a id="nclass-losescene"></a>

7. **LoseScene.java**
  - **Location:** `src/main/java/com/example/demo/scenes/LoseScene.java`
  - **Package:** `com.example.demo.scenes`
  - **Purpose:** Represents the game over scene when the player loses. It extends `GameScene` to inherit scene element management functionality.
  - **Methods:**
    - `update()`: Updates the scene. This method is not needed for the lose scene.
  - **Design Patterns:**
    - **Template Method Pattern (Inherited):** The `LoseScene` class extends `GameScene`, which defines a template for managing scene elements. The `update()` method is inherited from `GameScene` but is not needed for the lose scene, demonstrating the flexibility of the template method pattern.
    - **Factory Pattern (Implicit):** The `LoseScene` is created by the `GameSceneFactory`, which encapsulates the creation logic of game scenes. This pattern allows for easy extension of the system by adding new scene types without modifying the existing code.
    - **Facade Pattern (Inherited):** The `LoseScene` class uses the `GameScene` facade to manage scene elements, such as buttons and images, without needing to handle low-level details. This simplifies the implementation of the lose scene.
    - **Extensibility:** The use of the `GameSceneFactory` ensures that adding new scene types (e.g., a "WinScene") involves only extending the `SceneType` enum and updating the factory, adhering to the Open/Closed Principle.
    - **Separation of Concerns:** The `LoseScene` class focuses solely on the visual and interactive elements of the game over scene. It does not handle gameplay logic, state management, or other aspects of the game, promoting a clear separation of concerns by delegating scene creation and management to the `GameSceneFactory` and `GameScene` classes, the `LoseScene` class avoids becoming tightly coupled to specific implementations.


<br>
<a id="nclass-gamescenemanager"></a>

8. **GameSceneManager.java**
  - **Location:** `src/main/java/com/example/demo/scenes/services/GameSceneManager.java`
  - **Package:** `com.example.demo.scenes.services`
  - **Purpose:** Manages the game scenes and transitions between them. It implements `PropertyChangeListener` to listen for scene change events.
  - **Methods:**
    - `getInstance(Game game, Stage stage)`: Returns the singleton instance of the GameSceneManager.
    - `update()`: Updates the current game scene.
    - `goToScene(SceneType sceneType)`: Transitions to the specified scene.
    - `propertyChange(PropertyChangeEvent event)`: Handles property change events thrown by game scenes. Used to trigger game scene changes such as transitioning from menu scene to level one scene, level one to level two and so on.
  - **Design Patterns:**
    - **Singleton Pattern:** The `GameSceneManager` class is implemented as a singleton to ensure that there is only one instance responsible for managing the game scenes and their transitions. This ensures consistent scene management across different parts of the application.
    - **Observer Pattern:** The `GameSceneManager` class acts as an `Observer` by implementing the `PropertyChangeListener` interface. It listens for property change events (e.g., scene changes) and reacts by transitioning to the new scene. This pattern enhances the flexibility and responsiveness of the application by decoupling scene management from the game logic.
    - **Extensibility:** The `GameSceneManager` class is designed to be reusable and extensible. New scenes can be added by extending the `SceneType` enum and updating the `GameSceneFactory` to handle the new scene type.
    - **Extensibility:** The use of the `PropertyChangeListener` interface allows for easy integration of new event-driven behaviors, making it simple to add new scene transitions or handle additional property changes.
    - **Separation of Concerns:** The `GameSceneManager` class focuses solely on managing the game scenes and their transitions. It does not handle gameplay logic, state management, or other aspects of the game, promoting a clear separation of concerns by delegating scene creation to the `GameSceneFactory`, the `GameSceneManager` avoids becoming tightly coupled to specific scene implementations.


<br>
<a id="nclass-gamescenefactory"></a>

9. **GameSceneFactory.java**
  - **Location:** `src/main/java/com/example/demo/scenes/services/GameSceneFactory.java`
  - **Package:** `com.example.demo.scenes.services`
  - **Purpose:** Acts as a factory for creating different types of game scenes based on the `SceneType`. It abstracts the instantiation logic of game scenes, making it easier to manage and extend the creation of scenes.
  - **Methods:**
    - `createScene(SceneType sceneType, double screenWidth, double screenHeight)`: Creates and returns a specific game scene based on the provided `SceneType`.
  - **Design Pattern**
    - **Factory Pattern:** The factory design pattern is used to encapsulate the creation logic of game scenes. This pattern allows for easy extension of the system by adding new scene types without modifying the existing code, adhering to the Open/Closed Principle.
    - **Extensibility:** Adding a new scene type involves only extending the `SceneType` enum and updating the `createScene` method, without affecting other parts of the application.
    - **Separation of Concerns:** The `GameSceneFactory` handles the instantiation of scenes only its own task, nothing else.


<br>
<a id="nclass-levelscene"></a>

10. **LevelScene.java**
  - **Location:** `src/main/java/com/example/demo/scenes/levels/LevelScene.java`
  - **Package:** `com.example.demo.scenes.levels`
  - **Purpose:** Represents an abstract base class for level scenes in the game. It extends `GameScene` and provides common functionality for managing level-specific game logic, including user input, actor management, collision detection, and game state updates.
  - **Methods:**
    - `update()`: Updates the state of the level scene.
    - `checkIfGameOver()`: Checks if the game is over based on the player's state and the level's logic.
    - `userKillTargetLogic()`: Abstract method to determine if the player has reached the kill target for the level.
    - `userKillTargetReachedAction()`: Abstract method to define the action to take when the player reaches the kill target.
    - `spawnEnemyUnits()`: Abstract method to spawn enemy units logic for the level.
    - `getLevelView()`: Gets the level view component.
    - `getLevelState()`: Gets the level state component.
    - `getUserUnit()`: Gets the user's plane.
    - `getEnemyUnits()`: Gets the list of enemy units in the level.
    - `initPauseMenu()`: Initializes the pause menu UI component.
    - `showPauseMenu()`: Shows the pause menu and pauses the game.
    - `resumeGame()`: Resumes the game by resuming the game state.
    - `initPauseButton()`: Initializes the pause button UI component.
  - **Design Patterns:**
    - **Template Method Pattern:** The `LevelScene` class defines a template for level-specific logic, with abstract methods like `userKillTargetLogic()`, `userKillTargetReachedAction()`, and `spawnEnemyUnits()` that must be implemented by subclasses. This ensures consistent behavior while allowing customization for each level.
    - **Observer Pattern:** The `PropertyChangeSupport` mechanism inherited from `GameScene` allows it to act as an observable object, notifying registered listeners (observers) of property changes. This pattern is used to decouple the scene from its listeners, promoting loose coupling.
    - **Command Pattern (Implicit):** The key event handlers (`setOnKeyPressed` and `setOnKeyReleased`) act as commands that trigger specific actions (e.g., moving the player, firing projectiles, or pausing the game). This pattern simplifies the handling of user input and ensures clear, reusable code.
    - **Extensibility:** The `LevelScene` class is designed to be extended by subclasses, allowing new levels to be added without modifying the existing code. This adheres to the Open/Closed Principle.
    - **Extensibility:** The use of abstract methods (`userKillTargetLogic()`, `userKillTargetReachedAction()`, `spawnEnemyUnits()`) ensures that subclasses can define their own logic while inheriting common functionality from the base class.
    - **Separation of Concerns:** The `LevelScene` class focuses on managing the level's core logic, such as game state checks. It delegates specific tasks (e.g., spawning enemies, handling projectiles) to specialized managers (`EnemyManager`, `ProjectileManager`, etc.), promoting a clear separation of concerns.


<br>
<a id="nclass-levelthree"></a>

11. **LevelThree.java**
  - **Location:** `src/main/java/com/example/demo/scenes/levels/LevelThree.java`
  - **Package:** `com.example.demo.scenes.levels`
  - **Purpose:** Represents the third level of the game. It extends `LevelScene` and provides specific functionality for Level Three, including the introduction of a boss enemy and logic for transitioning to the win scene.
  - **Methods:**
    - `userKillTargetLogic()`: Determines if the player has reached the kill target to win the game.
    - `userKillTargetReachedAction()`: Defines the action to take when the player defeats the boss.
    - `spawnEnemyUnits()`: Spawns the boss enemy and its shield if no enemies are currently present.
  - **Design Patterns:**
    - **Template Method Pattern (Inherited):** The `LevelThree` class extends `LevelScene`, which defines a template for level-specific logic. The `userKillTargetLogic()`, `userKillTargetReachedAction()`, and `spawnEnemyUnits()` methods are implemented to provide Level Three-specific behavior, adhering to the template method pattern.
    - **Factory Pattern (Implicit):** The `LevelThree` scene is created by the `GameSceneFactory`, which encapsulates the creation logic of game scenes. This pattern allows for easy extension of the system by adding new scene types without modifying the existing code.
    - **Extensibility:** The use of the `GameSceneFactory` ensures that adding new levels involves only extending the `SceneType` enum and updating the factory, adhering to the Open/Closed Principle.
    - **Separation of Concerns:** The `LevelThree` class focuses solely on the specific logic of Level Three, such as spawning the boss and handling the win condition. It does not handle general gameplay logic, state management, or other aspects of the game, promoting a clear separation of concerns by delegating enemy management to the `EnemyManager` class, the `LevelThree` class avoids becoming tightly coupled to specific enemy implementations.


<br>
<a id="nclass-levelstate"></a>

12. **LevelState.java**
  - **Location:** `src/main/java/com/example/demo/scenes/levels/services/LevelState.java`
  - **Package:** `com.example.demo.scenes.levels.services`
  - **Purpose:** Represents the state of a level in the game. It is a singleton and provides methods to manage and retrieve level-specific data, such as the number of kills, current number of enemies, and screen width.
  - **Methods:**
    - `getInstance(double screenWidth)`: Gets the singleton instance of the `LevelState`.
    - `getNumberOfKills()`: Gets the number of kills achieved in the level.
    - `setNumberOfKills(int numberOfKills)`: Sets the number of kills achieved in the level.
    - `incrementNumberOfKills()`: Increments the number of kills by one.
    - `getCurrentNumberOfEnemies()`: Gets the current number of enemies in the level.
    - `setCurrentNumberOfEnemies(int currentNumberOfEnemies)`: Sets the current number of enemies in the level.
    - `getScreenWidth()`: Gets the width of the screen.
  - **Design Patterns:**
    - **Singleton Pattern:** The `LevelState` class is implemented as a singleton to ensure that there is only one instance of the level state throughout the game. This ensures consistent state data across the game, which is crucial for game progression and level-specific events.
    - **Encapsulation:** The class encapsulates level-specific data (e.g., number of kills, current number of enemies, screen width) and provides controlled access to this data through getter and setter methods. This ensures that the data is managed in a consistent and predictable manner.
    - **Extensibility:** The `LevelState` class is designed to be reusable across different levels, as it does not contain level-specific logic. This makes it easy to extend the game by adding new levels without modifying the existing state management code.
    - **Separation of Concerns:** The `LevelState` class focuses solely on managing the state of the level, such as tracking kills and enemies. It does not handle gameplay logic or UI updates, promoting a clear separation of concerns by delegating state management to a dedicated class, other components (e.g., `LevelScene`, `KillManager`) can focus on their specific tasks without worrying about state synchronization.


<br>
<a id="nclass-levelview"></a>

13. **LevelView.java**
  - **Location:** `src/main/java/com/example/demo/scenes/levels/services/LevelView.java`
  - **Package:** `com.example.demo.scenes.levels.services`
  - **Purpose:** Manages the visual components of a level, including the heart display and health bar. It provides methods to show, update, and remove these visual elements.
  - **Methods:**
    - `showHeartDisplay()`: Adds the heart display to the root group.
    - `getHeartDisplay()`: Retrieves the heart display container.
    - `removeHearts(int heartsRemaining)`: Removes hearts from the display based on the remaining hearts.
    - `showHealthBarDisplay(int maxHealth)`: Adds the health bar display to the root group.
    - `updateHealthBarDisplay(int health)`: Updates the health bar display with the current health value.
  - **Design Patterns:**
    - **Composite Pattern (Implicit):** The `LevelView` class manages a collection of visual components (e.g., `HeartDisplay`, `HealthBarDisplay`) as part of a larger composite structure. Each component can be treated uniformly, allowing for easy addition, removal, and manipulation of visual elements.
    - **Facade Pattern (Implicit):** The `LevelView` class acts as a facade, providing a simplified interface for managing the visual components of a level. It hides the complexity of managing individual visual elements (e.g., heart display, health bar) and provides a unified API for interacting with them.
    - **Extensibility:** The `LevelView` class is designed to be reusable and extensible. New visual components (e.g., score display, power-up indicators) can be added by creating new classes and integrating them into the `LevelView` class.
    - **Extensibility:** The use of separate classes for visual components (e.g., `HeartDisplay`, `HealthBarDisplay`) allows for modular development and easy extension of the visual system.
    - **Separation of Concerns:** The `LevelView` class focuses solely on managing the visual components of a level. It does not handle gameplay logic, state management, or other aspects of the game, promoting a clear separation of concerns by delegating the implementation of visual components to separate classes (e.g., `HeartDisplay`, `HealthBarDisplay`), the `LevelView` class avoids becoming tightly coupled to specific implementations.


<br>
<a id="nclass-actormanager"></a>

14. **ActorManager.java**
  - **Location:** `src/main/java/com/example/demo/scenes/levels/services/managers/ActorManager.java`
  - **Package:** `com.example.demo.scenes.levels.services.managers`
  - **Purpose:** Manages the lifecycle and updates of actors in the game. It provides methods to remove destroyed actors, update all actors, and manage their lifecycle within the game scene.
  - **Methods:**
    - `getInstance()`: Returns the singleton instance of the `ActorManager`.
    - `removeDestroyedActors(List<ActiveActorDestructible> actors, Group root)`: Removes destroyed actors from the given list and the root group.
    - `updateAllActors(List<List<ActiveActorDestructible>> actorsList)`: Updates all actors in the given list of actor lists.
  - **Design Patterns:**
    - **Singleton Pattern:** The `ActorManager` class is implemented as a singleton to ensure that there is only one instance of the manager throughout the game. This ensures consistent management of actors across the game, avoiding conflicts or duplicate management.
    - **Command Pattern (Implicit):** The `updateAllActors()` method acts as a command that triggers the `updateActor()` method on each actor. This decouples the update logic from the `ActorManager`, allowing actors to define their own update behavior.
    - **Iterator Pattern (Implicit):** The `updateAllActors()` method uses iteration to traverse and update all actors in the provided list. This pattern simplifies the process of updating multiple actors without exposing the underlying data structure.
    - **Extensibility:** The `ActorManager` class is designed to be reusable across different levels and scenes, as it does not contain level-specific logic. This makes it easy to extend the game by adding new actors or levels without modifying the existing actor management code.
    - **Extensibility:** The use of generics in the `updateAllActors()` method allows it to handle lists of different actor types, providing flexibility for future extensions.
    - **Separation of Concerns:** The `ActorManager` class focuses solely on managing the lifecycle and updates of actors. It does not handle gameplay logic, collision detection, or UI updates, promoting a clear separation of concerns by delegating actor updates to the actors themselves (`actor.updateActor()`), the `ActorManager` avoids becoming tightly coupled to specific actor implementations.


<br>
<a id="nclass-collisionmanager"></a>

15. **CollisionManager.java**
  - **Location:** `src/main/java/com/example/demo/scenes/levels/services/managers/CollisionManager.java`
  - **Package:** `com.example.demo.scenes.levels.services.managers`
  - **Purpose:** Manages collision detection and handling between actors in the game. It provides methods to detect and handle collisions between different lists of actors, causing them to take damage upon collision.
  - **Methods:**
    - `getInstance()`: Returns the singleton instance of the `CollisionManager`.
    - `handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2, EffectAudioType effectAudioType)`: Handles collisions between two lists of actors.
  - **Design Patterns:**
    - **Singleton Pattern:** The `CollisionManager` class is implemented as a singleton to ensure that there is only one instance of the manager throughout the game. This ensures consistent collision detection and handling across the game, avoiding conflicts or duplicate collision checks.
    - **Mediator Pattern (Implicit):** The `CollisionManager` acts as a mediator between different groups of actors, detecting and handling collisions without the actors needing to know about each other. This centralizes collision logic and reduces coupling between actors.
    - **Command Pattern (Implicit):** The `handleCollisions()` method acts as a command that triggers the `takeDamage()` method on each actor involved in a collision. This decouples the collision handling logic from the actors, allowing them to define their own damage behavior.
    - **Extensibility:** The `CollisionManager` class is designed to be reusable across different levels and scenes, as it does not contain level-specific logic. This makes it easy to extend the game by adding new actors or levels without modifying the existing collision management code.
    - **Extensibility:** The `handleCollisions()` method can handle collisions between any two lists of actors, providing flexibility for future extensions and allowing for complex collision scenarios.
    - **Separation of Concerns:** The `CollisionManager` class focuses solely on managing collision detection and handling. It does not handle gameplay logic, actor updates, or UI updates, promoting a clear separation of concerns by delegating collision handling to the actors themselves (`actor.takeDamage()`), the `CollisionManager` avoids becoming tightly coupled to specific actor implementations.


<br>
<a id="nclass-enemymanager"></a>

16. **EnemyManager.java**
  - **Location:** `src/main/java/com/example/demo/scenes/levels/services/managers/EnemyManager.java`
  - **Package:** `com.example.demo.scenes.levels.services.managers`
  - **Purpose:** Manages enemy units in the game, including handling their penetration of the user's defenses and adding new enemy units to the game scene.
  - **Methods:**
    - `getInstance()`: Returns the singleton instance of the `EnemyManager`.
    - `handleEnemyPenetration(ActiveActorDestructible userUnit, List<ActiveActorDestructible> enemyUnits, double screenWidth)`: Handles enemy penetration by damaging the user unit and destroying the penetrating enemy.
    - `addEnemyUnit(ActiveActorDestructible enemy, List<ActiveActorDestructible> enemyUnits, Group root)`: Adds a new enemy unit to the list of enemy units and the root group.
    - `addEnemyProp(Node enemyProp, Group root)`: Adds an enemy prop to the root group.
  - **Design Patterns:**
    - **Singleton Pattern:** The `EnemyManager` class is implemented as a singleton to ensure that there is only one instance of the manager throughout the game. This ensures consistent management of enemy units across the game, avoiding conflicts or duplicate management.
    - **Command Pattern (Implicit):** The `handleEnemyPenetration()` method acts as a command that triggers the `takeDamage()` and `destroy()` methods on the user unit and enemy, respectively. This decouples the penetration handling logic from the actors, allowing them to define their own damage and destruction behavior.
    - **Facade Pattern (Implicit):** The `EnemyManager` provides a simplified interface for managing enemy units, hiding the complexity of handling enemy penetration and adding new enemies. This makes it easier for other parts of the game to interact with enemy-related functionality.
    - **Extensibility:** The `EnemyManager` class is designed to be reusable across different levels and scenes, as it does not contain level-specific logic. This makes it easy to extend the game by adding new enemy types or levels without modifying the existing enemy management code.
    - **Extensibility:** The `addEnemyUnit()` method provides a universal way to add any type of enemy to the game, ensuring flexibility for future extensions.
    - **Separation of Concerns:** The `EnemyManager` class focuses solely on managing enemy units, including handling their penetration and adding new enemies. It does not handle gameplay logic, actor updates, or UI updates, promoting a clear separation of concerns by delegating penetration handling to the actors themselves (`userUnit.takeDamage()` and `enemy.destroy()`), the `EnemyManager` avoids becoming tightly coupled to specific actor implementations.


<br>
<a id="nclass-killmanager"></a>

17. **KillManager.java**
  - **Location:** `src/main/java/com/example/demo/scenes/levels/services/managers/KillManager.java`
  - **Package:** `com.example.demo.scenes.levels.services.managers`
  - **Purpose:** Manages the kill count in the game. It provides methods to update the kill count based on the current state of the game.
  - **Methods:**
    - `getInstance()`: Returns the singleton instance of the `KillManager`.
    - `updateKillCount(LevelState levelState, LevelView levelView, List<ActiveActorDestructible> enemyUnits, UserPlane userUnit, int userHealth)`: Updates the kill count based on the current state of the game.
  - **Design Patterns:**
    - **Singleton Pattern:** The `KillManager` class is implemented as a singleton to ensure that there is only one instance of the manager throughout the game. This ensures consistent management of the kill count across the game, avoiding conflicts or duplicate management.
    - **Facade Pattern (Implicit):** The `KillManager` provides a simplified interface for managing the kill count, hiding the complexity of tracking and updating the kill count. This makes it easier for other parts of the game to interact with kill-related functionality.
    - **Extensibility:** The `KillManager` class is designed to be reusable across different levels and scenes, as it does not contain level-specific logic. This makes it easy to extend the game by adding new levels or mechanics without modifying the existing kill count management code.
    - **Separation of Concerns:** The `KillManager` class focuses solely on managing the kill count. It does not handle gameplay logic, actor updates, or UI updates, promoting a clear separation of concerns by delegating the kill count update to the `KillManager`, other components (e.g., `LevelScene`, `LevelState`) can focus on their specific tasks without worrying about kill count synchronization.


<br>
<a id="nclass-projectilemanager"></a>

18. **ProjectileManager.java**
  - **Location:** `src/main/java/com/example/demo/scenes/levels/services/managers/ProjectileManager.java`
  - **Package:** `com.example.demo.scenes.levels.services.managers`
  - **Purpose:** Manages projectiles in the game, including handling projectiles going out of the screen and spawning new projectiles from fighter planes.
  - **Methods:**
    - `getInstance()`: Returns the singleton instance of the `ProjectileManager`.
    - `handleProjectileOutOfScreen(List<ActiveActorDestructible> projectiles, double screenWidth)`: Handles projectiles that go out of the screen view by destroying them.
    - `spawnProjectile(List<ActiveActorDestructible> fighterPlanes, List<ActiveActorDestructible> projectilesList, Group root)`: Spawns projectiles from fighter planes and adds them to the list of projectiles and the root group.
  - **Design Patterns:**
    - **Singleton Pattern:** The `ProjectileManager` class is implemented as a singleton to ensure that there is only one instance of the manager throughout the game. This ensures consistent management of projectiles across the game, avoiding conflicts or duplicate management.
    - **Command Pattern (Implicit):** The `spawnProjectile()` method acts as a command that triggers the `fireProjectile()` method on each fighter plane. This decouples the projectile spawning logic from the fighter planes, allowing them to define their own projectile behavior.
    - **Facade Pattern (Implicit):** The `ProjectileManager` provides a simplified interface for managing projectiles, hiding the complexity of handling projectile out-of-screen logic and spawning new projectiles. This makes it easier for other parts of the game to interact with projectile-related functionality.
    - **Extensibility:** The `ProjectileManager` class is designed to be reusable across different levels and scenes, as it does not contain level-specific logic. This makes it easy to extend the game by adding new projectile types or levels without modifying the existing projectile management code.
    - **Extensibility:** The `spawnProjectile()` method is flexible and can handle different types of fighter planes by delegating the projectile spawning logic to the fighter planes themselves (`fighterPlane.fireProjectile()`).
    - **Separation of Concerns:** The `ProjectileManager` class focuses solely on managing projectiles, including handling their out-of-screen logic and spawning new projectiles. It does not handle gameplay logic, actor updates, or UI updates, promoting a clear separation of concerns by delegating projectile spawning to the fighter planes themselves (`fighterPlane.fireProjectile()`), the `ProjectileManager` avoids becoming tightly coupled to specific fighter plane implementations.


<br>
<a id="nclass-backgroundaudio"></a>

19. **BackgroundAudio.java**
  - **Location:** `src/main/java/com/example/demo/audio/background/BackgroundAudio.java`
  - **Package:** `com.example.demo.audio.background`
  - **Purpose:** Represents background audio in the game, extending the `Audio` class. This class provides functionality to play and pause background music, with looping and volume settings.
  - **Methods:**
    - `BackgroundAudio(String audioPath)`: Constructs a `BackgroundAudio` object with the specified audio file path. The audio is set to loop indefinitely and has a volume of 0.6.
    - `playAudio()`: Plays the background audio.
    - `pauseAudio()`: Pauses the background audio.
  - **Design Patterns:**
    - **Inheritance:** The `BackgroundAudio` class inherits functionality from the `Audio` class, allowing it to reuse common audio management logic while adding background-specific behavior (e.g., looping and volume settings).
    - **Separation of Concerns:** The `BackgroundAudio` class focuses solely on managing background audio, such as looping and volume settings. It does not handle gameplay logic, scene management, or other aspects of the game, promoting a clear separation of concerns by delegating common audio management tasks to the `Audio` class, the `BackgroundAudio` class avoids duplicating code and maintains a clean and focused implementation.


<br>
<a id="nclass-effectaudio"></a>

20. **EffectAudio.java**
  - **Location:** `src/main/java/com/example/demo/audio/effect/EffectAudio.java`
  - **Package:** `com.example.demo.audio.effect`
  - **Purpose:** Represents an audio effect in the game. This class extends `Audio` to inherit audio playback functionality. Audio effects are short sounds that play in response to specific events, such as firing or taking damage.
  - **Methods:**
    - `EffectAudio(String audioPath)`: Constructs an `EffectAudio` object with the specified audio file path. The audio effect is set to play once and has a volume of 0.7.
  - **Design Patterns:**
    - **Inheritance:** The `EffectAudio` class inherits functionality from the `Audio` class, allowing it to reuse common audio management logic while adding effect-specific behavior (e.g., single playback and volume settings).
    - **Separation of Concerns:** The `EffectAudio` class focuses solely on managing audio effects, such as single playback and volume settings. It does not handle gameplay logic, scene management, or other aspects of the game, promoting a clear separation of concerns by delegating common audio management tasks to the `Audio` class, the `EffectAudio` class avoids duplicating code and maintains a clean and focused implementation.


<br>
<a id="nclass-audiofactory"></a>

21. **AudioFactory.java**
  - **Location:** `src/main/java/com/example/demo/audio/services/AudioFactory.java`
  - **Package:** `com.example.demo.audio.services`
  - **Purpose:** A factory class responsible for creating different types of audio, including background audio and effect audio.
  - **Methods:**
    - `createBackgroundAudio(BackgroundAudioType backgroundAudioType)`: Creates a `BackgroundAudio` based on the specified `BackgroundAudioType`.
    - `fireEffectAudio(EffectAudioType effectAudioType)`: Plays an `EffectAudio` based on the specified `EffectAudioType`.
  - **Design Patterns:**
    - **Factory Pattern:** The `AudioFactory` class encapsulates the creation logic for different types of audio (background and effect). This pattern allows for easy extension of the system by adding new audio types without modifying the existing code, adhering to the Open/Closed Principle.
    - **Switch-Case Pattern (Implicit):** The `AudioFactory` uses switch-case statements to determine which type of audio to create or play, based on the provided enum values (`BackgroundAudioType` and `EffectAudioType`). This pattern ensures that the factory can handle multiple types of audio in a clean and organized manner.
    - **Extensibility:** Adding a new type of background or effect audio involves only extending the respective enums (`BackgroundAudioType` or `EffectAudioType`) and updating the switch-case logic in the `AudioFactory`. This makes it easy to extend the system without modifying the existing code.
    - **Extensibility:** The use of enums (`BackgroundAudioType` and `EffectAudioType`) ensures that the factory can handle a wide variety of audio types, making the system flexible and adaptable to future changes.
    - **Separation of Concerns:** The `AudioFactory` class focuses solely on the creation and playback of audio. It does not handle gameplay logic, scene management, or other aspects of the game, promoting a clear separation of concerns by centralizing the creation logic for audio in the `AudioFactory`, other components of the game can easily request and use audio without needing to know the details of how it is created or managed.


<br>
<a id="nclass-audiomanager"></a>

22. **AudioManager.java**
  - **Location:** `src/main/java/com/example/demo/audio/services/AudioManager.java`
  - **Package:** `com.example.demo.audio.services`
  - **Purpose:** Manages the audio playback in the game, including background music and sound effects. This class provides methods to control the playback of background audio and sound effects.
  - **Methods:**
    - `getInstance()`: Returns the singleton instance of the `AudioManager`.
    - `changeBackgroundAudio(BackgroundAudioType backgroundAudioType)`: Changes the background audio to the specified type.
    - `playBackgroundAudio()`: Plays the current background audio.
    - `pauseBackgroundAudio()`: Pauses the current background audio.
    - `fireEffectAudio(EffectAudioType effectAudioType)`: Plays a sound effect of the specified type.
    - `setHasSound(boolean value)`: Sets whether sound is enabled in the game.
    - `getHasSound()`: Checks and gets whether sound is enabled in the game.
  - **Design Patterns:**
    - **Singleton Pattern:** The `AudioManager` class is implemented as a singleton to ensure that there is only one instance of the audio manager throughout the application. This ensures consistent audio management across different parts of the game.
    - **Facade Pattern (Implicit):** The `AudioManager` acts as a facade, providing a simplified interface for managing audio playback. It hides the complexity of managing individual audio instances and provides a unified API for interacting with audio.
    - **Separation of Concerns:** The `AudioManager` class focuses solely on managing audio playback, including background music and sound effects. It does not handle gameplay logic, scene management, or other aspects of the game, promoting a clear separation of concerns by delegating audio creation to the `AudioFactory`, the `AudioManager` avoids becoming tightly coupled to specific audio implementations.


<br>
<a id="nclass-audio"></a>

23. **Audio.java**
  - **Location:** `src/main/java/com/example/demo/audio/Audio.java`
  - **Package:** `com.example.demo.audio`
  - **Purpose:** Represents an audio object in the game, providing functionality to manage audio playback. This class is the base class for all audio-related classes, such as background and effect audio.
  - **Methods:**
    - `Audio(String audioPath)`: Constructs an `Audio` object with the specified audio file path.
    - `destroy()`: Destroys the audio player by setting it to `null`.
  - **Design Patterns:**
    - **Template Method Pattern:** The `Audio` class serves as a base class for other audio-related classes (e.g., `BackgroundAudio`, `EffectAudio`). It defines a template for managing audio playback, with specific behaviors (e.g., looping, volume control) implemented in subclasses. This pattern ensures consistent audio management while allowing for customization in subclasses.
    - **Extensibility:** The `Audio` class is designed to be easily extensible. New types of audio (e.g., ambient sounds, voiceovers) can be added by creating additional subclasses of `Audio` and implementing the required behavior.
    - **Separation of Concerns:** The `Audio` class focuses solely on managing audio playback. It does not handle gameplay logic, scene management, or other aspects of the game, promoting a clear separation of concerns by centralizing common audio management tasks in the `Audio` class, other components of the game can easily interact with audio without needing to know the details of how it is managed.


<br>
<a id="nclass-enumutil"></a>

24. **EnumUtil.java**
  - **Location:** `src/main/java/com/example/demo/utils/EnumUtil.java`
  - **Package:** `com.example.demo.utils`
  - **Purpose:** A utility class containing enumerations for various game states, scene types, background audio types, and effect audio types. This class centralizes all enumerations used throughout the application, making it easier to manage and extend the game's states and audio types.
  - **Enumerations:**
    - `State`: Represents the possible states of the game (e.g., `RUNNING`, `PAUSED`, `STOP`).
    - `SceneType`: Represents the different types of scenes in the game (e.g., `MENU`, `LEVEL1`, `LOSESCENE`, `WINSCENE`).
    - `BackgroundAudioType`: Represents the different types of background audio in the game (e.g., `MENU`, `LEVEL`, `LOSESCENE`, `WINSCENE`).
    - `EffectAudioType`: Represents the different types of effect audio in the game (e.g., `USERFIRE`, `ENEMYFIRE`, `KILL`, `DAMAGE`, `CLICK`, `PAUSE`, `TRANSITION`, `GAME_OVER`).
  - **Design Patterns:**
    - **Singleton Pattern (Implicit):** While `EnumUtil` itself is not a singleton, the use of enums centralizes all game states, scene types, and audio types in a single class. This centralization ensures that all parts of the application reference the same set of enums, behaving similarly to a singleton in terms of consistency and global access.
    - **Factory Pattern (Implicit):** The enums in `EnumUtil` serve as a form of factory for defining and categorizing different types of game elements (e.g., scenes, audio). This pattern allows for easy extension of the system by adding new enum values without modifying the existing code, adhering to the Open/Closed Principle.
    - **Extensibility:** The `EnumUtil` class is designed to be easily extensible. New states, scene types, or audio types can be added by extending the respective enums. For example, adding a new `SceneType` (e.g., `LEVEL4`) involves only adding a new value to the `SceneType` enum, without affecting other parts of the application.
    - **Extensibility:** The use of enums ensures that the system is flexible and adaptable to future changes, making it easy to add new features or modify existing ones.


<br>
<a id="nclass-loggerutil"></a>

25. **LoggerUtil.java**
  - **Location:** `src/main/java/com/example/demo/utils/LoggerUtil.java`
  - **Package:** `com.example.demo.utils`
  - **Purpose:** A utility class for creating and configuring a logger for the application. This class provides a static logger instance that is configured to log messages to both the console and a file. The logger is pre-configured with handlers for console and file logging, and it captures messages at all logging levels.
  - **Static Field:**
    - `logger`: A static logger instance configured to log messages to both the console and a file (`SkyBattle.log`).
  - **Static Initializer:** Configures the logger to log messages to a file and sets the logging level to capture all messages.
  - **Design Patterns:**
    - **Singleton Pattern (Implicit):** The `LoggerUtil` class provides a static logger instance (`logger`), ensuring that there is only one logger instance throughout the application. This ensures consistent logging across different parts of the game.
    - **Facade Pattern (Implicit):** The `LoggerUtil` class acts as a facade, providing a simplified interface for logging messages. It hides the complexity of configuring and managing the logger, allowing other parts of the application to log messages without needing to know the details of how the logger is set up.


<br>
<a id="nclass-healthbardisplay"></a>

26. **HealthBarDisplay.java**
  - **Location:** `src/main/java/com/example/demo/actors/props/HealthBarDisplay.java`
  - **Package:** `com.example.demo.actors.props`
  - **Purpose:** Represents a health bar display for visualizing the health of an entity. This class uses a `ProgressBar` to display the health ratio dynamically.
  - **Methods:**
    - `HealthBarDisplay(int maxHealth)`: Constructor to initialize the health bar display.
    - `updateHealth(int currentHealth)`: Updates the health bar display based on the current health.
    - `getHealthBar()`: Retrieves the progress bar used to display the health.
  - **Design Patterns:**
    - **Composite Pattern (Implicit):** The `HealthBarDisplay` class uses a `ProgressBar` to represent the health bar. The `ProgressBar` is a composite UI component that combines multiple elements (e.g., background, fill, text) to create a single, cohesive visual element. This pattern allows for easy customization and dynamic updates of the health bar.
    - **Facade Pattern (Implicit):** The `HealthBarDisplay` class acts as a facade, providing a simplified interface for managing the health bar. It hides the complexity of updating the progress bar and dynamically changing its color, allowing other parts of the application to interact with the health bar without needing to know the details of how it is implemented.
    - **Extensibility:** The `HealthBarDisplay` class is designed to be easily extensible. Additional features (e.g., animations, additional visual indicators) can be added to the health bar by modifying the `updateHealth()` method or extending the `HealthBarDisplay` class.




## Modified Java Class

<font color="red"><b>Modified / Renamed Classes</b></font>  

<br>
<a id="mclass-controller"></a>

1. **LevelView.java** 
  - **Location (New):** `src/main/java/com/example/demo/scenes/levels/services/LevelView.java` 
  - **Location (Old):** `src/main/java/com/example/demo/LevelView.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.scenes.levels.services`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `LevelView` class was responsible for managing the visual components of a level, including the heart display, win image, and game over image.
  - **Modification:** The `LevelView` class was refactored to include additional functionality for managing the health bar display. The new design separates the visual components into distinct methods, making the code more modular and easier to maintain.
  - **Modified Methods:**
    - `showWinImage()`: This method was removed as the win image logic was not carried over to the new design.
      - **Old Code:**
        ```java
        public void showWinImage() {
            root.getChildren().add(winImage);
            winImage.showWinImage();
        }
        ```

      <br>

      - **New Code:**
        - The `showWinImage()` method was removed.

      <br>

      - **Reasoning for Modification:**
        - **Design Change:** The win image logic was not carried over to the new design. This decision was likely made to simplify the UI and focus on core gameplay elements rather than victory screens.
        - **Reusability:** By removing the win image logic, the `LevelView` class becomes more reusable and focused on managing health and other essential UI components.

    <br>
      
    - `showGameOverImage()`: This method was removed as the game over image logic was not carried over to the new design.
      - **Old Code:**
        ```java
        public void showGameOverImage() {
            root.getChildren().add(gameOverImage);
        }
        ```

      <br>

      - **New Code:**
        - The `showGameOverImage()` method was removed.

      <br>

      - **Reasoning for Modification:**
        - **Design Change:** Similar to the win image, the game over image logic was not carried over to the new design. This decision was likely made to streamline the UI and focus on core gameplay mechanics.
        - **Reusability:** Removing the game over image logic allows the `LevelView` class to be more focused on managing health and other essential UI components, making it more reusable across different levels.

    <br>

    - `showHealthBarDisplay(int maxHealth)`: This new method adds the health bar display to the root group.
      - **New Code:**
        ```java
        public void showHealthBarDisplay(int maxHealth) {
            healthBarDisplay = new HealthBarDisplay(maxHealth);
            root.getChildren().add(healthBarDisplay.getHealthBar());
        }
        ```

      <br>
      
      - **Reasoning for Modification:**
        - **New Feature:** The `showHealthBarDisplay()` method was introduced to add a health bar display to the root group. This new feature provides a more detailed representation of the player's health, which can be particularly useful in more complex levels.
        - **Enhanced UI:** By adding a health bar, the game's UI is enhanced, providing players with a clearer understanding of their health status.

    <br>

    - `updateHealthBarDisplay(int health)`: This new method updates the health bar display with the current health value.
      - **New Code:**
        ```java
        public void updateHealthBarDisplay(int health) {
            healthBarDisplay.updateHealth(health);
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Dynamic Updates:** The `updateHealthBarDisplay()` method was introduced to dynamically update the health bar display based on the player's current health. This ensures that the health bar accurately reflects the player's health in real-time.
        - **Enhanced UI:** By providing real-time updates, the health bar becomes a more effective tool for players to monitor their health during gameplay.


<br>
<a id="mclass-levelone"></a>

2. **LevelOne.java**
  - **Location (New):** `src/main/java/com/example/demo/scenes/levels/LevelOne.java` 
  - **Location (Old):** `src/main/java/com/example/demo/LevelOne.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.scenes.levels`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `LevelOne` class was responsible for managing the first level of the game, including enemy spawning, kill target conditions, and scene transitions.
  - **Modification:** The `LevelOne` class was refactored to extend `LevelScene` and provide specific functionality for Level One. The new design uses the `LevelScene` base class to handle common level-specific functionality, such as enemy spawning and kill target conditions.
  - **Modfied Methods:**
    - `userKillTargetLogic()`: Added new method. Determines if the player has reached the kill target to advance to the next level.
      - **New Code:**
        ```java
        @Override
        protected boolean userKillTargetLogic() {
            return getLevelState().getNumberOfKills() >= KILLS_TO_ADVANCE;
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Template Method Pattern:** The `userKillTargetLogic()` method is part of the template method pattern implemented in the `LevelScene` class. This method determines if the player has reached the kill target to advance to the next level.
        - **Custom Logic:** By implementing this method in `LevelOne`, the level can define its own specific kill target logic, making the code more flexible and reusable.

    <br>

    - `userKillTargetReachedAction()`: Added new method. Defines the action to take when the player reaches the kill target.
      - **New Code:**
        ```java
        @Override
        protected void userKillTargetReachedAction() {
            goToScene(NEXT_SCENE);
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Template Method Pattern:** The `userKillTargetReachedAction()` method is part of the template method pattern implemented in the `LevelScene` class. This method defines the action to take when the player reaches the kill target.
        - **Custom Logic:** By implementing this method in `LevelOne`, the level can define its own specific action to take when the kill target is reached, making the code more flexible and reusable.

    <br>

    - `planeClumping(double newEnemyInitialYPosition)`: Helper method to check if the new enemy's Y position is too close to the recently spawned enemies to avoid clumping.
      - **New Code:**
        ```java
        private boolean planeClumping(double newEnemyInitialYPosition) {
            for (int i = 0; i < 3; i++) {
                if (Math.abs(recentSpawnYCoord[i] - newEnemyInitialYPosition) < 50)
                    return true;
            }
            return false;
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Clumping Prevention:** The `planeClumping()` method is a helper method that checks if the new enemy's Y position is too close to the recently spawned enemies. This prevents enemy clumping, improving the gameplay experience.
        - **Custom Logic:** By implementing this method in `LevelOne`, the level can define its own specific logic for preventing enemy clumping, making the code more flexible and reusable.


    - `spawnEnemyUnits()`: This method was refactored to use the `EnemyManager` class for spawning enemies and to include logic to prevent enemy clumping.
      - **Old Code:**
        ```java
        @Override
        protected void spawnEnemyUnits() {
            int currentNumberOfEnemies = getCurrentNumberOfEnemies();
            for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
                if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                    double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                    ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
                    addEnemyUnit(newEnemy);
                }
            }
        }
        ```

      <br>

      - **New Code:**
        ```java
        @Override
        protected void spawnEnemyUnits() {
            int currentNumberOfEnemies = getLevelState().getCurrentNumberOfEnemies();
            for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
                if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                    double newEnemyInitialYPosition;
                    do {
                        newEnemyInitialYPosition = Math.random() * ENEMY_MAXIMUM_Y_POS;
                    } while (planeClumping(newEnemyInitialYPosition));
  
                    ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, "enemyplane1.png", .27);
                    enemyManager.addEnemyUnit(newEnemy, getEnemyUnits(), getRoot());
                    recentSpawnYCoord[0] = recentSpawnYCoord[1];
                    recentSpawnYCoord[1] = recentSpawnYCoord[2];
                    recentSpawnYCoord[2] = newEnemyInitialYPosition;
                }
            }
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Refactored Logic:** The `spawnEnemyUnits()` method was refactored to use the `EnemyManager` class for spawning enemies. This decision was made to centralize the enemy spawning logic in the `EnemyManager` class, making it easier to manage and extend.
        - **Clumping Prevention:** The new implementation includes logic to prevent enemy clumping by checking if the new enemy's Y position is too close to the recently spawned enemies. This improves the gameplay experience by ensuring that enemies are spread out.

    <br>

    - `checkIfGameOver()`: This method was removed as the game over logic was refactored into the `LevelScene` class's `checkIfGameOver()` method.
      - **Reasoning for Modification:**
        - **Refactored Logic:** The game over logic was refactored into the `LevelScene` class's `checkIfGameOver()` method. This decision was made to centralize the game over logic in the `LevelScene` class, making it easier to manage and extend.
        - **Template Method Pattern:** The `LevelScene` class uses the template method pattern to define the structure of the game over logic, allowing subclasses like `LevelOne` to provide specific implementations for kill target logic and actions.

    <br>

    - `initializeFriendlyUnits()`: This method was removed as the initialization of friendly units was refactored into the `LevelScene` class's constructor.
      - **Reasoning for Modification:**
        - **Refactored Logic:** The initialization of friendly units was refactored into the `LevelScene` class's constructor. This decision was made to centralize the initialization logic in the `LevelScene` class, making it easier to manage and extend.
        - **Reusability:** By moving the initialization logic to the `LevelScene` class, the code becomes more reusable across different levels, reducing code duplication.

    <br>

    - `instantiateLevelView()`: This method was removed as the level view initialization was refactored into the `LevelScene` class's constructor.
      - **Reasoning for Modification:**
        - **Refactored Logic:** The level view initialization was refactored into the `LevelScene` class's constructor. This decision was made to centralize the initialization logic in the `LevelScene` class, making it easier to manage and extend.
        - **Reusability:** By moving the initialization logic to the `LevelScene` class, the code becomes more reusable across different levels, reducing code duplication.

    <br>

    - `userHasReachedKillTarget()`: This method was removed as the kill target logic was refactored into the `LevelScene` class's `userKillTargetLogic()` method.
      - **Reasoning for Modification:**
        - **Refactored Logic:** The kill target logic was refactored into the `LevelScene` class's `userKillTargetLogic()` method. This decision was made to centralize the kill target logic in the `LevelScene` class, making it easier to manage and extend.
        - **Template Method Pattern:** The `LevelScene` class uses the template method pattern to define the structure of the kill target logic, allowing subclasses like `LevelOne` to provide specific implementations.


<br>
<a id="mclass-leveltwo"></a>

3. **LevelTwo.java**
  - **Location (New):** `src/main/java/com/example/demo/scenes/levels/LevelTwo.java` 
  - **Location (Old):** `src/main/java/com/example/demo/LevelTwo.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.scenes.levels`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `LevelTwo` class was responsible for managing the second level of the game, including the boss enemy, kill target conditions, and scene transitions.
  - **Modification:** The `LevelTwo` class was refactored to extend `LevelScene` and provide specific functionality for Level Two. The new design uses the `LevelScene` base class to handle common level-specific functionality, such as enemy spawning and kill target conditions.
  - **Modfied Methods:**
    - `userKillTargetLogic()`: Added new method. Determines if the player has reached the kill target to advance to the next level.
      - **New Code:**
        ```java
        @Override
        protected boolean userKillTargetLogic() {
            return getLevelState().getNumberOfKills() >= KILLS_TO_ADVANCE;
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Template Method Pattern:** The `userKillTargetLogic()` method is part of the template method pattern implemented in the `LevelScene` class. This method determines if the player has reached the kill target to advance to the next level.
        - **Custom Logic:** By implementing this method in `LevelOne`, the level can define its own specific kill target logic, making the code more flexible and reusable.

    <br>

    - `userKillTargetReachedAction()`: Added new method. Defines the action to take when the player reaches the kill target.
      - **New Code:**
        ```java
        @Override
        protected void userKillTargetReachedAction() {
            goToScene(NEXT_SCENE);
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Template Method Pattern:** The `userKillTargetReachedAction()` method is part of the template method pattern implemented in the `LevelScene` class. This method defines the action to take when the player reaches the kill target.
        - **Custom Logic:** By implementing this method in `LevelOne`, the level can define its own specific action to take when the kill target is reached, making the code more flexible and reusable.

    <br>

    - `planeClumping(double newEnemyInitialYPosition)`: Helper method to check if the new enemy's Y position is too close to the recently spawned enemies to avoid clumping.
      - **New Code:**
        ```java
        private boolean planeClumping(double newEnemyInitialYPosition) {
            for (int i = 0; i < 3; i++) {
                if (Math.abs(recentSpawnYCoord[i] - newEnemyInitialYPosition) < 50)
                    return true;
            }
            return false;
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Clumping Prevention:** The `planeClumping()` method is a helper method that checks if the new enemy's Y position is too close to the recently spawned enemies. This prevents enemy clumping, improving the gameplay experience.
        - **Custom Logic:** By implementing this method in `LevelOne`, the level can define its own specific logic for preventing enemy clumping, making the code more flexible and reusable.


    - `spawnEnemyUnits()`: This method was refactored to use the `EnemyManager` class for spawning enemies and to include logic to prevent enemy clumping.
      - **Old Code:**
        ```java
        @Override
        protected void spawnEnemyUnits() {
            int currentNumberOfEnemies = getCurrentNumberOfEnemies();
            for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
                if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                    double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                    ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
                    addEnemyUnit(newEnemy);
                }
            }
        }
        ```

      <br>

      - **New Code:**
        ```java
        @Override
        protected void spawnEnemyUnits() {
            int currentNumberOfEnemies = getLevelState().getCurrentNumberOfEnemies();
            for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
                if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                    double newEnemyInitialYPosition;
                    do {
                        newEnemyInitialYPosition = Math.random() * ENEMY_MAXIMUM_Y_POS;
                    } while (planeClumping(newEnemyInitialYPosition));
  
                    ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, "enemyplane1.png", .27);
                    enemyManager.addEnemyUnit(newEnemy, getEnemyUnits(), getRoot());
                    recentSpawnYCoord[0] = recentSpawnYCoord[1];
                    recentSpawnYCoord[1] = recentSpawnYCoord[2];
                    recentSpawnYCoord[2] = newEnemyInitialYPosition;
                }
            }
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Refactored Logic:** The `spawnEnemyUnits()` method was refactored to use the `EnemyManager` class for spawning enemies. This decision was made to centralize the enemy spawning logic in the `EnemyManager` class, making it easier to manage and extend.
        - **Clumping Prevention:** The new implementation includes logic to prevent enemy clumping by checking if the new enemy's Y position is too close to the recently spawned enemies. This improves the gameplay experience by ensuring that enemies are spread out.

    <br>

    - `checkIfGameOver()`: This method was removed as the game over logic was refactored into the `LevelScene` class's `checkIfGameOver()` method.
      - **Reasoning for Modification:**
        - **Refactored Logic:** The game over logic was refactored into the `LevelScene` class's `checkIfGameOver()` method. This decision was made to centralize the game over logic in the `LevelScene` class, making it easier to manage and extend.
        - **Template Method Pattern:** The `LevelScene` class uses the template method pattern to define the structure of the game over logic, allowing subclasses like `LevelOne` to provide specific implementations for kill target logic and actions.

    <br>

    - `initializeFriendlyUnits()`: This method was removed as the initialization of friendly units was refactored into the `LevelScene` class's constructor.
      - **Reasoning for Modification:**
        - **Refactored Logic:** The initialization of friendly units was refactored into the `LevelScene` class's constructor. This decision was made to centralize the initialization logic in the `LevelScene` class, making it easier to manage and extend.
        - **Reusability:** By moving the initialization logic to the `LevelScene` class, the code becomes more reusable across different levels, reducing code duplication.

    <br>

    - `instantiateLevelView()`: This method was removed as the level view initialization was refactored into the `LevelScene` class's constructor.
      - **Reasoning for Modification:**
        - **Refactored Logic:** The level view initialization was refactored into the `LevelScene` class's constructor. This decision was made to centralize the initialization logic in the `LevelScene` class, making it easier to manage and extend.
        - **Reusability:** By moving the initialization logic to the `LevelScene` class, the code becomes more reusable across different levels, reducing code duplication.

    <br>

    - `userHasReachedKillTarget()`: This method was removed as the kill target logic was refactored into the `LevelScene` class's `userKillTargetLogic()` method.
      - **Reasoning for Modification:**
        - **Refactored Logic:** The kill target logic was refactored into the `LevelScene` class's `userKillTargetLogic()` method. This decision was made to centralize the kill target logic in the `LevelScene` class, making it easier to manage and extend.
        - **Template Method Pattern:** The `LevelScene` class uses the template method pattern to define the structure of the kill target logic, allowing subclasses like `LevelOne` to provide specific implementations.


<br>
<a id="mclass-activeactor"></a>

4. **ActiveActor.java**
  - **Location (New):** `src/main/java/com/example/demo/actors/ActiveActor.java`
  - **Location (Old):** `src/main/java/com/example/demo/ActiveActor.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.actors`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `ActiveActor` class was an abstract class representing an active actor in the game, such as planes or projectiles. It extended `ImageView` to display the actor's image and provided methods for moving the actor horizontally and vertically.
  - **Modification:** The `updatePosition()` method was renamed to `updateActor()` to better reflect its role in updating the actor's state. 
  - **Modification Done:**
    - **Method Renamed:** `updatePosition()` was renamed to `updateActor()` to better reflect the purpose of the method, which is to update the state of the actor.

    <br>

    - **Old Code:**
      ```java
      public abstract void updatePosition();
      ```

    <br>

    - **New Code:**
      ```java
      public abstract void updateActor();
      ```

    <br>

    - **Reasoning for Modification:**
      - **Clarity and Consistency:** The method `updatePosition()` was initially named to suggest that it only updates the position of the actor. However, the method's purpose is broader, as it updates the entire state of the actor, including its position, velocity, and other attributes. Renaming it to `updateActor()` clarifies that the method is responsible for updating the actor's overall state, not just its position.
      - **Consistency Across Classes:** By renaming the method to `updateActor()`, it aligns with the naming convention used in other classes, such as `FighterPlane` and `Projectile`, where the method is also named `updateActor()`. This consistency makes the codebase easier to understand and maintain.


<br>
<a id="mclass-activeactordestructible"></a>

5. **ActiveActorDestructible.java**
  - **Location (New):** `src/main/java/com/example/demo/actors/ActiveActorDestructible.java`
  - **Location (Old):** `src/main/java/com/example/demo/ActiveActorDestructible.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.actors`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `ActiveActorDestructible` class was an abstract class that extended `ActiveActor` and implemented the `Destructible` interface. It provided functionality for managing the destruction state of the actor.
  - **Modification:** The `updatePosition()` method was renamed to `updateActor()`, and the `isDestroyed()` method was renamed to `getIsDestroyed()` for clarity. Additionally, a `finalize()` method was added to log when the object is garbage collected.
  - **Modification Done:**
    - **Method Renamed:** `updatePosition()` was renamed to `updateActor()` to better reflect the purpose of the method, which is to update the state of the actor.
    - **Garbage Collection Logging:** Added a `finalize()` method to log when the object is garbage collected, which can help with debugging and memory management.

    <br>

    - **Old Code:**
      ```java
      public abstract void updatePosition();
      ```

    <br>

    - **New Code:**
      ```java
      public abstract void updateActor();

      @Override
      protected void finalize() throws Throwable {
          LoggerUtil.logger.info(getClass().getName() + " class is garbage collected");
      }
      ```

    <br>

    - **Reasoning for Modification:**
      - **Clarity and Consistency:** Similar to the `ActiveActor` class, the method `updatePosition()` was renamed to `updateActor()` to reflect its broader purpose of updating the actor's state.
      - **Debugging and Memory Management:** The `finalize()` method was added to log when the object is garbage collected. This can be useful for debugging memory leaks or understanding the lifecycle of objects in the game. By logging the garbage collection, developers can ensure that objects are being properly cleaned up and that memory is being managed efficiently.


<br>
<a id="mclass-destructible"></a>

6. **Destructible.java**
  - **Location (New):** `src/main/java/com/example/demo/actors/Destructible.java`
  - **Location (Old):** `src/main/java/com/example/demo/Destructible.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.actors`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `Destructible` interface defined methods for taking damage and being destroyed.
  - **Modification:**  No changes were made to the method signatures. Only reorganization of class package structure has been done. 
  - **Reasoning for Modification:**
    - **Package Structure:** The `Destructible` interface was moved to a more appropriate package (`com.example.demo.actors`) to better organize the codebase. This change improves the overall structure and makes it easier to locate and manage related classes.


<br>
<a id="mclass-fighterplane"></a>

7. **FighterPlane.java**
  - **Location (New):** `src/main/java/com/example/demo/actors/FighterPlane.java`
  - **Location (Old):** `src/main/java/com/example/demo/FighterPlane.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.actors`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `FighterPlane` class was an abstract class representing a fighter plane in the game. It extended `ActiveActorDestructible` and provided functionality for managing the plane's health, taking damage, and firing projectiles.
  - **Modification:** The `updatePosition()` method was renamed to `updateActor()`, and the `healthAtZero()` method was made private for better encapsulation.
  - **Modification Done:**
    - **Method Renamed:** `updatePosition()` was renamed to `updateActor()` to better reflect the purpose of the method, which is to update the state of the fighter plane.

    <br>

    - **Old Code:**
      ```java
      public abstract void updatePosition();
      ```

    <br>

    - **New Code:**
      ```java
      public abstract void updateActor();
      ```

    <br>

    - **Reasoning for Modification:**
      - **Clarity and Consistency:** The method `updatePosition()` was renamed to `updateActor()` to reflect its broader purpose of updating the fighter plane's state, including its position, health, and other attributes.
      - **Consistency Across Classes:** Renaming the method to `updateActor()` aligns with the naming convention used in other classes, such as `ActiveActor` and `Projectile`, where the method is also named `updateActor()`. This consistency makes the codebase easier to understand and maintain.


<br>
<a id="mclass-projectile"></a>

8. **Projectile.java**
  - **Location (New):** `src/main/java/com/example/demo/actors/Projectile.java`
  - **Location (Old):** `src/main/java/com/example/demo/Projectile.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.actors`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `Projectile` class was an abstract class representing a projectile in the game. It extended `ActiveActorDestructible` and provided functionality for moving the projectile horizontally and handling damage.
  - **Modification:** The `updatePosition()` method was renamed to `updateActor()`, and the constructor was modified to accept a `horizontalVelocity` parameter, which was previously hardcoded in subclasses.
  - **Modification Done:**
    - **Method Renamed:** `updatePosition()` was renamed to `updateActor()` to better reflect the purpose of the method, which is to update the state of the projectile.
    - **Horizontal Velocity:** Added a `horizontalVelocity` field to the constructor to allow for more flexible projectile movement.

    <br>

    - **Old Code:**
      ```java
      public abstract void updatePosition();
      ```

    <br>

    - **New Code:**
      ```java
      public abstract void updateActor();

      public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos, double horizontalVelocity) {
          super(imageName, imageHeight, initialXPos, initialYPos);
          this.horizontalVelocity = horizontalVelocity;
      }
      ```

    <br>

    - **Reasoning for Modification:**
      - **Clarity and Consistency:** The method `updatePosition()` was renamed to `updateActor()` to reflect its broader purpose of updating the projectile's state, including its position and velocity.
      - **Flexibility in Movement:** By adding the `horizontalVelocity` field to the constructor, projectiles can now have different horizontal velocities, making the movement logic more flexible and adaptable to different types of projectiles.


<br>
<a id="mclass-userplane"></a>

9. **UserPlane.java**
  - **Location (New):** `src/main/java/com/example/demo/actors/user/UserPlane.java`
  - **Location (Old):** `src/main/java/com/example/demo/UserPlane.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.actors.user`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `UserPlane` class represented the user's fighter plane in the game. It extended `FighterPlane` and provided functionality for moving the plane and firing projectiles.
  - **Modification:** The `updatePosition()` method was renamed to `updateActor()`, and the movement logic was enhanced to allow for smoother movement and boundary checking. Additionally, the `isFiring` flag was added to control projectile firing.
  - **Modification Done:**
    - **Method Renamed:** `updatePosition()` was renamed to `updateActor()` to better reflect the purpose of the method, which is to update the state of the user plane.
    - **Movement Logic:** Added more detailed movement logic, including handling multiple key presses simultaneously and ensuring the plane stays within bounds.
    - **Firing Logic:** Added a `setIsFiring(boolean isFiring)` method to control when the user plane is firing projectiles.

    <br>

    - **Old Code:**
      ```java
      public void updatePosition() {
          if (isMoving()) {
              double initialTranslateY = getTranslateY();
              this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
              double newPosition = getLayoutY() + getTranslateY();
              if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
                  this.setTranslateY(initialTranslateY);
              }
          }
      }
      ```

    <br>

    - **New Code:**
      ```java
      public void updateActor() {
          updatePosition();
      }

      private void updatePosition() {
          if (isMovingY()) {
              double initialTranslateY = getTranslateY();
              this.moveVertically(VERTICAL_VELOCITY * yVelocityMultiplier);
              double newPosition = getLayoutY() + getTranslateY();
              if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
                  this.setTranslateY(initialTranslateY);
              }
          }

          if (isMovingX()){
              double initialTranslateX = getTranslateX();
              this.moveHorizontally(VERTICAL_VELOCITY * xVelocityMultiplier);
              double newPosition = getLayoutX() + getTranslateX();
              if (newPosition < X_LEFT_BOUND || newPosition > X_RIGHT_BOUND) {
                  this.setTranslateX(initialTranslateX);
              }
          }
      }

      public void setIsFiring(boolean isFiring) { 
          this.isFiring = isFiring;
      }
      ```

    <br>

    - **Reasoning for Modification:**
      - **Clarity and Consistency:** The method `updatePosition()` was renamed to `updateActor()` to reflect its broader purpose of updating the user plane's state, including its position, velocity, and other attributes.
      - **Enhanced Movement Logic:** The new movement logic allows the user plane to handle multiple key presses simultaneously, such as moving diagonally when both the left and up keys are pressed. This improves the responsiveness and accuracy of the user's controls.
      - **Firing Control:** The `setIsFiring(boolean isFiring)` method allows for more precise control over when the user plane fires projectiles. This can be useful for implementing firing cooldowns or other firing logic.


<br>
<a id="mclass-boss"></a>

10. **Boss.java**
  - **Location (New):** `src/main/java/com/example/demo/actors/enemy/Boss.java`
  - **Location (Old):** `src/main/java/com/example/demo/Boss.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.actors.enemy`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `Boss` class represented the boss enemy in the game. It extended `FighterPlane` and provided specific behaviors such as moving in a pattern, firing projectiles, and having a shield.
  - **Modification:** The `updatePosition()` method was renamed to `updateActor()`, and the shield logic was enhanced to include a `ShieldImage` class for visual representation. The boss's fire rate and shield activation probability were adjusted based on the game's target FPS.
  - **Modification Done:**
    - **Method Renamed:** `updatePosition()` was renamed to `updateActor()` to better reflect the purpose of the method, which is to update the state of the boss.
    - **Shield Logic:** Added more detailed shield logic, including the ability to activate and deactivate the shield based on a probability and duration.
    - **Health Bar Integration:** Integrated the boss's health bar display with the `LevelView` class to dynamically update the health bar.

    <br>

    - **Old Code:**
      ```java
      public void updatePosition() {
          double initialTranslateY = getTranslateY();
          moveVertically(getNextMove());
          double currentPosition = getLayoutY() + getTranslateY();
          if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
              setTranslateY(initialTranslateY);
          }
      }
      ```

    <br>

    - **New Code:**
      ```java
      public void updateActor() {
          updatePosition();
          updateShield();
          updateShieldView();
          levelView.updateHealthBarDisplay(getHealth());
      }

      private void updatePosition() {
          double initialTranslateY = getTranslateY();
          double nextMove = getNextMove();
          shieldNextMove = nextMove;

          moveVertically(nextMove);
          double currentPosition = getLayoutY() + getTranslateY();

          if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
              setTranslateY(initialTranslateY);
              shieldNextMove = 0;
          }
      }

      private void updateShield() {
          if (isShielded) {
              framesWithShieldActivated++;
          } else if (shieldShouldBeActivated()) {
              activateShield();
          }

          if (shieldExhausted()) {
              deactivateShield();
          }
      }

      private void updateShieldView() {
          shieldImage.moveVertically(shieldNextMove);
      }
      ```

    <br>

    - **Reasoning for Modification:**
      - **Clarity and Consistency:** The method `updatePosition()` was renamed to `updateActor()` to reflect its broader purpose of updating the boss's state, including its position, health, and shield status.
      - **Enhanced Shield Logic:** The new shield logic allows the boss to activate and deactivate its shield based on a probability and duration. This adds a layer of complexity and unpredictability to the boss's behavior, making the game more challenging and engaging.
      - **Health Bar Integration:** By integrating the boss's health bar with the `LevelView` class, the health bar can be dynamically updated as the boss takes damage. This provides the player with real-time feedback on the boss's health, enhancing the gameplay experience.


<br>
<a id="mclass-enemyplane"></a>

11. **EnemyPlane.java**
  - **Location (New):** `src/main/java/com/example/demo/actors/enemy/EnemyPlane.java`
  - **Location (Old):** `src/main/java/com/example/demo/EnemyPlane.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.actors.enemy`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `EnemyPlane` class represented an enemy plane in the game. It extended `FighterPlane` and provided functionality for moving the plane and firing projectiles.
  - **Modification:** The `updatePosition()` method was renamed to `updateActor()`, and the fire rate was adjusted based on the game's target FPS.
  - **Modification Done:**
    - **Method Renamed:** `updatePosition()` was renamed to `updateActor()` to better reflect the purpose of the method, which is to update the state of the enemy plane.
    - **Fire Rate:** Added a `fireRate` parameter to the constructor to allow for more flexible fire rates for different enemy planes.

    <br>

    - **Old Code:**
      ```java
      public void updatePosition() {
          moveHorizontally(HORIZONTAL_VELOCITY);
      }
      ```

    <br>

    - **New Code:**
      ```java
      public void updateActor() {
          moveHorizontally(HORIZONTAL_VELOCITY);
      }

      public EnemyPlane(double initialXPos, double initialYPos, String imageName, double fireRate) {
          super(imageName, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
          this.FIRE_RATE = fireRate / GameLoop.getInstance(null).get_TARGET_FPS();
      }
      ```

    <br>

    - **Reasoning for Modification:**
      - **Clarity and Consistency:** The method `updatePosition()` was renamed to `updateActor()` to reflect its broader purpose of updating the enemy plane's state, including its position and firing behavior.
      - **Flexibility in Fire Rate:** By adding the `fireRate` parameter to the constructor, different enemy planes can have different fire rates. This allows for more varied and challenging enemy behavior, making the game more dynamic and engaging.


<br>
<a id="mclass-bossprojectile"></a>

12. **BossProjectile.java**
  - **Location (New):** `src/main/java/com/example/demo/actors/projectile/BossProjectile.java`
  - **Location (Old):** `src/main/java/com/example/demo/BossProjectile.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.actors.projectile`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `BossProjectile` class represented a projectile fired by the boss. It extended `Projectile` and provided specific behavior for the boss's projectiles.
  - **Modification:** The horizontal velocity was adjusted based on the game's target FPS.
  - **Modification Done:**
    - **Horizontal Velocity:** Added a `horizontalVelocity` field to the constructor to allow for more flexible projectile movement.

    <br>

    - **New Code:**
      ```java
      public BossProjectile(double initialYPos) {
          super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos, HORIZONTAL_VELOCITY);
      }
      ```

    <br>

    - **Reasoning for Modification:**
      - **Flexibility in Movement:** By adding the `horizontalVelocity` field to the constructor, boss projectiles can have different horizontal velocities, making the movement logic more flexible and adaptable to different types of projectiles.


<br>
<a id="mclass-enemyprojectile"></a>

13. **EnemyProjectile.java**
  - **Location (New):** `src/main/java/com/example/demo/actors/projectile/EnemyProjectile.java`
  - **Location (Old):** `src/main/java/com/example/demo/EnemyProjectile.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.actors.projectile`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `EnemyProjectile` class represented a projectile fired by an enemy plane. It extended `Projectile` and provided specific behavior for enemy projectiles.
  - **Modification:** The horizontal velocity was adjusted based on the game's target FPS.
  - **Modification Done:**
    - **Horizontal Velocity:** Added a `horizontalVelocity` field to the constructor to allow for more flexible projectile movement.

    <br>

    - **New Code:**
      ```java
      public EnemyProjectile(double initialXPos, double initialYPos) {
          super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
      }
      ```

    <br>

    - **Reasoning for Modification:**
      - **Flexibility in Movement:** By adding the `horizontalVelocity` field to the constructor, enemy projectiles can have different horizontal velocities, making the movement logic more flexible and adaptable to different types of projectiles.


<br>
<a id="mclass-userprojectile"></a>

14. **UserProjectile.java**
  - **Location (New):** `src/main/java/com/example/demo/actors/projectile/UserProjectile.java`
  - **Location (Old):** `src/main/java/com/example/demo/UserProjectile.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.actors.projectile`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `UserProjectile` class represented a projectile fired by the user's plane. It extended `Projectile` and provided specific behavior for user projectiles.
  - **Modification:** The horizontal velocity was adjusted based on the game's target FPS.
  - **Modification Done:**
    - **Horizontal Velocity:** Added a `horizontalVelocity` field to the constructor to allow for more flexible projectile movement.

    <br>

    - **New Code:**
      ```java
      public UserProjectile(double initialXPos, double initialYPos) {
          super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
      }
      ```

    <br>

    - **Reasoning for Modification:**
      - **Flexibility in Movement:** By adding the `horizontalVelocity` field to the constructor, user projectiles can have different horizontal velocities, making the movement logic more flexible and adaptable to different types of projectiles.
   
   
<br>
<a id="mclass-heartdisplay"></a>
    
15. **HeartDisplay.java**
  - **Location (New):** `src/main/java/com/example/demo/actors/props/HeartDisplay.java`
  - **Location (Old):** `src/main/java/com/example/demo/HeartDisplay.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.actors.props`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `HeartDisplay` class represented a display of hearts to indicate health or lives in the game. It managed the creation, display, and removal of heart icons.
  - **Modification:**  No changes were made to the method signatures. Only reorganization of class package structure has been done. 
  - **Reasoning for Modification:**
    - **Package Structure:** The `HeartDisplay` class was moved to a more appropriate package (`com.example.demo.actors.props`) to better organize the codebase. This change improves the overall structure and makes it easier to locate and manage related classes.
   
   
<br>
<a id="mclass-shieldimage"></a>
  
16. **ShieldImage.java**
  - **Location (New):** `src/main/java/com/example/demo/actors/props/ShieldImage.java`
  - **Location (Old):** `src/main/java/com/example/demo/ShieldImage.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package (New):** `com.example.demo.actors.props`
  - **Package (Old):** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `ShieldImage` class represented a shield image that could be displayed or hidden. It extended `ImageView` to display the shield image.
  - **Modification:** The `moveVertically()` method was added to allow the shield image to move with the boss. 
  - **Modification Done:**
    - **Movement Logic:** Added a `moveVertically(double verticalMove)` method to allow the shield image to move with the boss.

    <br>

    - **New Code:**
      ```java
      public void moveVertically(double verticalMove) {
          this.setTranslateY(getTranslateY() + verticalMove);
      }
      ```

    <br>

    - **Reasoning for Modification:**
      - **Synchronized Movement:** The `moveVertically(double verticalMove)` method allows the shield image to move in sync with the boss. This ensures that the shield remains visually attached to the boss, providing a more cohesive and realistic visual experience.




<br><br><br>

<font color="red"><b>Deleted / Broken Apart (Refactored) Classes</b></font>  

<br>
<a id="mclass-controller"></a>

1. **Controller.java** <font color="red"><b>(Deleted)</b></font>  
  - **Location:** `src/main/java/com/example/demo/controller/Controller.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package:** `com.example.demo.controller` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `Controller` class was responsible for managing the game's lifecycle, scene transitions, and handling level changes. It used the `Observable` and `Observer` pattern to manage state changes.
  - **Reason:** To improve modularity and separation of concerns, the original `Controller.java` was split into multiple classes.
  - **Modification:** The `Controller` class was refactored and split into multiple new classes ([<b><u>`Game`</b></u>](#nclass-game), [<b><u>`GameState`</b></u>](#nclass-gamestate), [<b><u>`GameSceneManager`</b></u>](#nclass-gamescenemanager)) to improve modularity and maintainability. The new design uses the `PropertyChangeSupport` mechanism for state management and scene transitions, which is more flexible and easier to extend.
  - **Modified Methods:**
    - `launchGame()`: This method was responsible for initializing the game and transitioning to the first level. It was refactored into the [<b><u>`Game`</b></u>](#nclass-game) class's `init()` method and the [<b><u>`GameSceneManager`</b></u>](#nclass-gamescenemanager)'s `goToScene()` method.
      - **Old Code:**
        - The `launchGame()` method in the `Controller` class was responsible for showing the stage and transitioning to the first level. This method was tightly coupled with the `goToLevel()` method, which handled the creation and transition to a new level.

        ```java
        public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
                InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
            stage.show();
            goToLevel(LEVEL_ONE_CLASS_NAME);
        }
        ```

      <br>
    
      - **New Code:**
        - The functionality of `launchGame()` was split into two parts:
          1. **Stage Initialization:** The `stage.show()` method was moved to the [<b><u>`Game`</b></u>](#nclass-game) class's `init()` method.
          2. **Scene Transition:** The `goToLevel()` method was refactored into the [<b><u>`GameSceneManager`</b></u>](#nclass-gamescenemanager))'s `goToScene()` method.

        ```java
        public void init() {
            stage.show();
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Separation of Concerns:** By moving the stage initialization to the [<b><u>`Game`</b></u>](#nclass-game) class and the scene transition to the [<b><u>`GameSceneManager`</b></u>](#nclass-gamescenemanager)), the responsibilities are clearly separated. The [<b><u>`Game`</b></u>](#nclass-game) class manages the game lifecycle and state, while the [<b><u>`GameSceneManager`</b></u>](#nclass-gamescenemanager)) handles scene transitions.
        - **Singleton Pattern:** The [<b><u>`Game`</b></u>](#nclass-game) class is designed as a singleton, ensuring that there is only one instance of the game manager. This makes it easier to manage the game's state and lifecycle from a central location.
        - **Reusability:** The [<b><u>`GameSceneManager`</b></u>](#nclass-gamescenemanager)) can now handle any scene transition, not just level transitions, making the code more flexible and reusable.

    <br>

    - `goToLevel(String className)`: This method was responsible for creating and transitioning to a new level. It was refactored into the [<b><u>`GameSceneManager`</b></u>](#nclass-gamescenemanager)'s `goToScene()` method.
      - **Old Code:**
        - The `goToLevel()` method in the `Controller` class was responsible for dynamically creating a new level based on the class name, adding the controller as an observer, initializing the scene, and setting it to the stage.

        ```java
        private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
                InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            Class<?> myClass = Class.forName(className);
            Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
            LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
            myLevel.addObserver(this);
            Scene scene = myLevel.initializeScene();
            stage.setScene(scene);
            myLevel.startGame();
        }
        ```

      <br>

      - **New Code:**
        - The functionality of `goToLevel()` was refactored into the [<b><u>`GameSceneManager`</b></u>](#nclass-gamescenemanager))'s `goToScene()` method.

        ```java
        private void goToScene(SceneType sceneType) {
            currentGameScene.removePropChangeListener(this);
            currentGameScene.destroy();

            currentGameScene = gameSceneFactory.createScene(sceneType, screenWidth, screenHeight);
            currentGameScene.addPropChangeListener(this);

            stage.setScene(currentGameScene.getScene());
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Centralized Scene Management:** By moving the scene transition logic to the [<b><u>`GameSceneManager`</b></u>](#nclass-gamescenemanager)), all scene-related operations are centralized in one place. This makes it easier to manage and extend the scene transitions.
        - **Factory Pattern:** The  [<b><u>`GameSceneFactory`</b></u>](#nclass-gamescenefactory) is used to create scenes, which abstracts the creation logic and makes it easier to add new scenes in the future.
        - **Observer Pattern Replacement:** The old code used the `Observer` pattern to handle level transitions. The new code replaces this with the `PropertyChangeListener` pattern, which is more flexible and integrates better with the JavaBeans framework.

    <br>

    - `update(Observable arg0, Object arg1)`: This method was responsible for handling level transitions based on state changes. It was refactored into the [<b><u>`GameSceneManager`</b></u>](#nclass-gamescenemanager)'s `propertyChange()` method.
      - **Old Code:**
        - The `update()` method in the `Controller` class was responsible for handling level transitions based on state changes. It used the `Observable` pattern to listen for updates and transition to a new level.

        ```java
        @Override
        public void update(Observable arg0, Object arg1) {
            try {
                goToLevel((String) arg1);
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                    | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText(e.getClass().toString());
                alert.show();
            }
        }
        ```

      <br>

      - **New Code:**
        - The functionality of `update()` was refactored into the [<b><u>`GameSceneManager`</b></u>](#nclass-gamescenemanager))'s `propertyChange()` method.

        ```java
        @Override
        public void propertyChange(PropertyChangeEvent event) {
            switch (event.getPropertyName()) {
                case "sceneChange":
                    goToScene((SceneType) event.getNewValue());
                    break;
                case "stateChange":
                    break;  
                default:
                    throw new IllegalArgumentException("Unknown Property Change Name: " + event);
            }
        }
        ```

        <br>

        - **Reasoning for Modification:**
          - **Event-Driven Architecture (Observer Pattern):** The new code uses the `PropertyChangeListener` pattern, which is more suitable for event-driven architectures. This pattern allows for more flexible and decoupled event handling.
          - **Centralized Event Handling:** By moving the event handling logic to the [<b><u>`GameSceneManager`</b></u>](#nclass-gamescenemanager)), all scene-related events are handled in one place. This makes it easier to manage and extend the event handling logic.
          - **Error Handling:** The new code includes more robust error handling, as it can handle different types of property change events (e.g., `sceneChange`, `stateChange`) and throw specific exceptions for unknown events.


<br>
<a id="mclass-levelparent"></a>

2. **LevelParent.java** <font color="red"><b>(Deleted)</b></font>  
  - **Location:** `src/main/java/com/example/demo/LevelParent.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package:** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `LevelParent` class was responsible for managing the game's lifecycle, scene transitions, and handling level changes. It used the `Observable` pattern to manage state changes.
  - **Modification:** The `LevelParent` class was refactored and split into multiple new classes (`GameScene`, `LevelScene`, `LevelState`, `ActorManager`, `CollisionManager`, `EnemyManager`, `KillManager`, `ProjectileManager`) to improve modularity and maintainability. The new design uses the `PropertyChangeSupport` mechanism for state management and scene transitions, which is more flexible and easier to extend.
  - **Modified Methods:**
      <br>
    <br>

    - `initializeScene()`: This method was responsible for initializing the scene and adding the background. It was refactored into the `GameScene` class's constructor and `LevelScene` class's constructor.
      - **Old Code:**
        - The `initializeScene()` method in `LevelParent` was responsible for setting up the scene, including initializing the background, friendly units, and displaying the heart UI.
      
      <br>
    
      - **New Code:**
        - The functionality of `initializeScene()` was split into the `GameScene` and `LevelScene` constructors. The background initialization and UI setup were moved to the `GameScene` constructor, while level-specific initialization was handled in the `LevelScene` constructor.
    
      <br>
        
      - **Reasoning for Modification:**
        - **Separation of Concerns:** By moving scene initialization to the `GameScene` and `LevelScene` constructors, the responsibility for scene setup is clearly separated. The `GameScene` class handles generic scene initialization, while `LevelScene` handles level-specific setup.
        - **Reusability:** The `GameScene` class can now be reused across different scenes, not just levels, making the code more modular and flexible.
    
    <br>

    - `startGame()`: This method was responsible for starting the game loop. It was refactored into the `GameLoop` class's `start()` method.
      - **Old Code:**
        - The `startGame()` method in `LevelParent` was responsible for starting the game loop by playing the `Timeline`.
        
        ```java
        public void startGame() {
            background.requestFocus();
            timeline.play();
        }
        ```

      <br>
    
      - **New Code:**
        - The functionality of `startGame()` was moved to the `GameLoop` class's `start()` method.
    
        ```java
        public void start() {
            timeline.play();
        }
        ```

      <br>
    
      - **Reasoning for Modification:**
        - **Centralized Game Loop Management:** By moving the game loop control to the `GameLoop` class, the game loop is managed in a centralized location. This makes it easier to control the game loop from a single point, improving maintainability.
        - **Singleton Pattern:** The `GameLoop` class is implemented as a singleton, ensuring that there is only one game loop instance, which is crucial for maintaining a consistent frame rate across the game.

    <br>

    - `goToNextLevel(String levelName)`: This method was responsible for transitioning to the next level. It was refactored into the `GameScene` class's `goToScene(Object newScene)` method.
      - **Old Code:**
        - The `goToNextLevel()` method in `LevelParent` was responsible for notifying observers to transition to the next level.

        ```java
        public void goToNextLevel(String levelName) {
            setChanged();
            notifyObservers(levelName);
        }
        ```
        
      <br>

      - **New Code:**
        - The functionality of `goToNextLevel()` was refactored into the `GameScene` class's `goToScene(Object newScene)` method.

        ```java
        protected void goToScene(Object newScene) {
            setPropChange("sceneChange", null, newScene);
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Event-Driven Architecture (Observer Pattern):** The new implementation uses the `PropertyChangeSupport` mechanism to notify listeners of scene changes. This is more flexible and integrates better with the JavaBeans framework.
        - **Centralized Scene Management:** By moving scene transition logic to the `GameScene` class, all scene-related operations are centralized in one place, making it easier to manage and extend the scene transitions.

    <br>

    - `updateScene()`: This method was responsible for updating the scene, handling collisions, and managing actors. It was refactored into the `LevelScene` class's `update()` method and the various manager classes (`ActorManager`, `CollisionManager`, `EnemyManager`, `KillManager`, `ProjectileManager`).
      - **Old Code:**
        - The `updateScene()` method in `LevelParent` was responsible for updating the scene, handling collisions, managing actors, and checking game-over conditions.

        ```java
        private void updateScene() {
            spawnEnemyUnits();
            updateActors();
            generateEnemyFire();
            updateNumberOfEnemies();
            handleEnemyPenetration();
            handleUserProjectileCollisions();
            handleEnemyProjectileCollisions();
            handlePlaneCollisions();
            removeAllDestroyedActors();
            updateKillCount();
            updateLevelView();
            checkIfGameOver();
        }
        ```

      <br>

      - **New Code:**
        - The functionality of `updateScene()` was refactored into the `LevelScene` class's `update()` method and the various manager classes (`ActorManager`, `CollisionManager`, `EnemyManager`, `KillManager`, `ProjectileManager`).

        ```java
        @Override
        public void update() {
            // Update kill count
            levelState.setCurrentNumberOfEnemies(enemyUnits.size());
            collisionManager.handleCollisions(userProjectiles, enemyUnits, EffectAudioType.KILL); // UserProjectile collide with EnemyUnits
            actorManager.removeDestroyedActors(enemyUnits, root);
            killManager.updateKillCount(levelState, levelView, enemyUnits, userUnit, userUnit.getHealth());

            // Handle actor collision
            collisionManager.handleCollisions(enemyProjectiles, Arrays.asList(userUnit), EffectAudioType.DAMAGE); // EnemyProjectile collide with UserUnit
            collisionManager.handleCollisions(Arrays.asList(userUnit), enemyUnits, EffectAudioType.DAMAGE); // UserUnit collide with EnemyUnits

            // Handle for EnemyPlane and Projectile out of screen
            enemyManager.handleEnemyPenetration(userUnit, enemyUnits, levelState.getScreenWidth());
            projectileManager.handleProjectileOutOfScreen(userProjectiles, levelState.getScreenWidth());
            projectileManager.handleProjectileOutOfScreen(enemyProjectiles, levelState.getScreenWidth());

            // Call removal of all destroyed actors on screen
            actorManager.removeDestroyedActors(enemyUnits, root);
            actorManager.removeDestroyedActors(userProjectiles, root);
            actorManager.removeDestroyedActors(enemyProjectiles, root);

            // Then finally spawn in enemy and projectiles
            spawnEnemyUnits();
            projectileManager.spawnProjectile(enemyUnits, enemyProjectiles, root);
            frameCounter++;
            if (frameCounter == (int) GameLoop.getInstance(null).get_TARGET_FPS() / 10) {
                if (userUnit.getIsFiring() && frameCounter == (int) GameLoop.getInstance(null).get_TARGET_FPS() / 10) {
                    projectileManager.spawnProjectile(Arrays.asList(userUnit), userProjectiles, root);
                }
                frameCounter = 0;
            }

            // Update LevelView heart display
            levelView.removeHearts(userUnit.getHealth());

            // Update all actors
            actorManager.updateAllActors(Arrays.asList(Arrays.asList(userUnit), enemyUnits, userProjectiles, enemyProjectiles));

            // Check for game end
            checkIfGameOver();
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Manager Classes:** By introducing manager classes (`ActorManager`, `CollisionManager`, `EnemyManager`, `KillManager`, `ProjectileManager`), the responsibilities for updating actors, handling collisions, managing enemies, and updating the kill count are clearly separated. This improves code readability and maintainability.
        - **Reusability:** The manager classes can be reused across different levels, reducing code duplication and making the codebase more modular.

    <br>

    - `initializeTimeline()`: This method was responsible for initializing the game loop. It was refactored into the `GameLoop` class's constructor.
      - **Old Code:**
        - The `initializeTimeline()` method in `LevelParent` was responsible for setting up the game loop using a `Timeline`.

        ```java
        private void initializeTimeline() {
            timeline.setCycleCount(Timeline.INDEFINITE);
            KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
            timeline.getKeyFrames().add(gameLoop);
        }
        ```

      <br>

      - **New Code:**
        - The functionality of `initializeTimeline()` was moved to the `GameLoop` class's constructor.

        ```java
        private GameLoop(Game game) {
            this.game = game;
            this.keyframe = new KeyFrame(FRAME_DURATION, e -> this.game.update());
            this.timeline = new Timeline(this.keyframe);
            this.timeline.setCycleCount(Timeline.INDEFINITE);
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Centralized Game Loop Management:** By moving the game loop initialization to the `GameLoop` class, the game loop is managed in a centralized location. This makes it easier to control the game loop from a single point, improving maintainability.
        - **Singleton Pattern:** The `GameLoop` class is implemented as a singleton, ensuring that there is only one game loop instance, and there would only be 1 Timeline instance, which is crucial for maintaining a consistent frame rate across the game.

    <br>

    - `initializeBackground()`: This method was responsible for initializing the background and handling user input. It was refactored into the `GameScene` class's constructor and `LevelScene` class's constructor.
      - **Old Code:**
        - The `initializeBackground()` method in `LevelParent` was responsible for setting up the background and handling user input.

        ```java
        private void initializeBackground() {
            background.setFocusTraversable(true);
            background.setFitHeight(screenHeight);
            background.setFitWidth(screenWidth);

            background.setOnKeyPressed(new EventHandler<KeyEvent>() {
                public void handle(KeyEvent e) {
                    KeyCode kc = e.getCode();
                    if (kc == KeyCode.UP) user.moveUp();
                    if (kc == KeyCode.DOWN) user.moveDown();
                    if (kc == KeyCode.SPACE) fireProjectile();
                }
            });

            background.setOnKeyReleased(new EventHandler<KeyEvent>() {
                public void handle(KeyEvent e) {
                    KeyCode kc = e.getCode();
                    if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
                }
            });

            root.getChildren().add(background);
        }
        ```

      <br>

      - **New Code:**
        - The functionality of `initializeBackground()` was moved to the `GameScene` and `LevelScene` constructors.

        ```java
        public GameScene(String backgroundImageName, double screenWidth, double screenHeight) {
            this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
            this.background.setFocusTraversable(true);
            this.background.setFitWidth(this.screenWidth);
            this.background.setFitHeight(screenHeight);
            this.root.getChildren().add(this.background);
            this.background.requestFocus();
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Separation of Concerns:** By moving background initialization to the `GameScene` and `LevelScene` constructors, the responsibility for background setup is clearly separated. The `GameScene` class handles generic background initialization, while `LevelScene` handles level-specific input handling.
        - **Reusability:** The `GameScene` class can now be reused across different scenes as the logic for initializing background is no longer tied down to a particular level, making the code more modular and flexible.

    <br>

    - `fireProjectile()`: This method was responsible for firing projectiles. It was refactored into the `ProjectileManager` class's `spawnProjectile()` method.
      - **Old Code:**
        - The `fireProjectile()` method in `LevelParent` was responsible for firing projectiles from the user's plane.
    
        ```java
        private void fireProjectile() {
            ActiveActorDestructible projectile = user.fireProjectile();
            root.getChildren().add(projectile);
            userProjectiles.add(projectile);
        }
        ```
    
      <br>

      - **New Code:**
        - The functionality of `fireProjectile()` was refactored into the `ProjectileManager` class's `spawnProjectile()` method.
    
        ```java
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
        ```
    
      <br>

      - **Reasoning for Modification:**
        - **Centralized Projectile Management:** By moving projectile firing logic to the `ProjectileManager` class, all projectile-related operations are centralized in one place. This makes it easier to manage and extend projectile behavior.
        - **Reusability:** The `spawnProjectile()` method of the `ProjectileManager` class can be reused across different levels, also handling for projectile firing of all types of fighter plane, reducing code duplication and making the codebase more modular.

    <br>

    - `generateEnemyFire()`: This method was responsible for generating enemy fire. It was refactored into the `ProjectileManager` class's `spawnProjectile()` method.
      - **Old Code:**
        - The `generateEnemyFire()` method in `LevelParent` was responsible for generating enemy fire by spawning projectiles from enemy units.

        ```java
        private void generateEnemyFire() {
            enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
        }
        ```

      <br>

      - **New Code:**
        - The functionality of `generateEnemyFire()` was refactored into the `ProjectileManager` class's `spawnProjectile()` method.
        - It has been combined with the refactored method above, `fireProjectile()` into a single method in `ProjectileManager` as `spawnProjectile()`.

        ```java
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
        ```

      <br>

      - **Reasoning for Modification:**
        - **Centralized Projectile Management:** By moving enemy fire generation to the `ProjectileManager` class, all projectile-related operations are centralized in one place. This makes it easier to manage and extend projectile behavior.
        - **Reusability:** The `spawnProjectile()` method of the `ProjectileManager` class can be reused across different levels and different fighter planes, reducing code duplication and making the codebase more modular.

    <br>


    - `spawnEnemyProjectile(ActiveActorDestructible projectile)`: This method was responsible for spawning enemy projectiles. It was refactored into the `ProjectileManager` class's `spawnProjectile()` method.
      - **Old Code:**
        - The `spawnEnemyProjectile()` method in `LevelParent` was responsible for spawning enemy projectiles.

        ```java
        private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
            if (projectile != null) {
                root.getChildren().add(projectile);
                enemyProjectiles.add(projectile);
            }
        }
        ```

      <br>

      - **New Code:**
        - The functionality of `spawnEnemyProjectile()` was refactored into the `ProjectileManager` class's `spawnProjectile()` method.
        - As mentioned above, `spawnProjectile()` method is refactored in the hopes of abiding by the reusability law, hence allowing the method `spawnProjectil()` to handle firing projectile for both enemy and user forces.

        ```java
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
        ```

      <br>

      - **Reasoning for Modification:**
        - **Centralized Projectile Management:** By moving enemy projectile spawning to the `ProjectileManager` class, all projectile-related operations are centralized in one place. This makes it easier to manage and extend projectile behavior.
        - **Reusability:** The method `spawnProjectile()` of the `ProjectileManager` class can be reused across different levels, reducing code duplication and making the codebase more modular.

    <br>

    - `updateActors()`: This method was responsible for updating actors. It was refactored into the `ActorManager` class's `updateAllActors()` method.
      - **Old Code:**
        - The `updateActors()` method in `LevelParent` was responsible for updating all actors in the scene.

        ```java
        private void updateActors() {
            friendlyUnits.forEach(plane -> plane.updateActor());
            enemyUnits.forEach(enemy -> enemy.updateActor());
            userProjectiles.forEach(projectile -> projectile.updateActor());
            enemyProjectiles.forEach(projectile -> projectile.updateActor());
        }
        ```

      <br>

      - **New Code:**
        - The functionality of `updateActors()` was refactored into the `ActorManager` class's `updateAllActors()` method.
        - With the same philosophy, the method `updateAllActors()` has been build for the ground up to handle all types of actors, and as many actors as the developer requires, hence creating a one stop solution for updating all actors the developers need.

        ```java
        public void updateAllActors(List<List<ActiveActorDestructible>> actorsList) {
            for (List<ActiveActorDestructible> actors : actorsList) {
                for (ActiveActorDestructible actor : actors)
                    actor.updateActor();
            }
        }
        ```

      <br>

      - **Reasoning for Modification:**
        - **Centralized Actor Management:** By moving actor update logic to the `ActorManager` class, all actor-related operations are centralized in one place. This makes it easier to manage and extend actor behavior.
        - **Reusability:** The `ActorManager` class can be reused across different levels, reducing code duplication and making the codebase more modular.
        - **Ease of Updating Actors:** Only one call for this method is necessary to update all available and existing actors in the game, making it more efficient compared to seperately and repetitively calling multiple update methods.

    <br>

    - `removeAllDestroyedActors()`: This method was responsible for removing destroyed actors. It was refactored into the `ActorManager` class's `removeDestroyedActors()` method.
      - **Old Code:**
        - The `removeAllDestroyedActors()` method in `LevelParent` was responsible for removing all destroyed actors from the scene.
    
        ```java
        private void removeAllDestroyedActors() {
            removeDestroyedActors(friendlyUnits);
            removeDestroyedActors(enemyUnits);
            removeDestroyedActors(userProjectiles);
            removeDestroyedActors(enemyProjectiles);
        }
        ```

      <br>
    
      - **New Code:**
        - The functionality of `removeAllDestroyedActors()` was refactored into the `ActorManager` class's `removeDestroyedActors()` method.
    
        ```java
        public void removeDestroyedActors(List<ActiveActorDestructible> actors, Group root) {
            List<ActiveActorDestructible> destroyedActors = actors.stream()
                    .filter(actor -> actor.getIsDestroyed())
                    .collect(Collectors.toList());
            root.getChildren().removeAll(destroyedActors);
            actors.removeAll(destroyedActors);
        }
        ```
    
      <br>

      - **Reasoning for Modification:**
        - **Centralized Actor Management:** By moving actor removal logic to the `ActorManager` class, all actor-related operations are centralized in one place. This makes it easier to manage and extend actor behavior.
        - **Reusability:** The `ActorManager` class can be reused across different levels, reducing code duplication and making the codebase more modular.
        - **Ease of Use:** `removeAllDestroyedActors()` only needs 1 call to remove all different types of destroyed actors regardless of type. The List of actors can also increase by just passing them to the method, with no code changes necessary.

    <br>

    - `handlePlaneCollisions()`, `handleUserProjectileCollisions()`, `handleEnemyProjectileCollisions()`: These methods were responsible for handling collisions. They were refactored into the `CollisionManager` class's `handleCollisions()` method.
      - **Old Code:**
        - These methods in `LevelParent` were responsible for handling collisions between different groups of actors.
    
        ```java
        private void handlePlaneCollisions() {
            handleCollisions(friendlyUnits, enemyUnits);
        }
    
        private void handleUserProjectileCollisions() {
            handleCollisions(userProjectiles, enemyUnits);
        }
    
        private void handleEnemyProjectileCollisions() {
            handleCollisions(enemyProjectiles, friendlyUnits);
        }
        ```
    
      <br>

      - **New Code:**
        - The functionality of these methods was refactored into the `CollisionManager` class's `handleCollisions()` method.
    
        ```java
        public void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2, EffectAudioType effectAudioType) {
            for (ActiveActorDestructible act1 : actors1) {
                for (ActiveActorDestructible act2 : actors2) {
                    if (act1.getBoundsInParent().intersects(act2.getBoundsInParent())) {
                        act1.takeDamage();
                        act2.takeDamage();
                        if (effectAudioType != null)
                            AudioManager.getInstance().fireEffectAudio(effectAudioType);
                    }
                }
            }
        }
        ```
    
      <br>

      - **Reasoning for Modification:**
        - **Centralized Collision Management:** By moving collision handling logic to the `CollisionManager` class, all collision-related operations are centralized in one place. This makes it easier to manage and extend collision behavior.
        - **Reusability:** The `handleCollisions()` method can be reused across different levels and different actor types, reducing code duplication and making the codebase more modular.

    <br>

    - `handleEnemyPenetration()`: This method was responsible for handling enemy penetration. It was refactored into the `EnemyManager` class's `handleEnemyPenetration()` method.
      - **Old Code:**
        - The `handleEnemyPenetration()` method in `LevelParent` was responsible for handling enemy units that penetrated the user's defenses.

      <br>

      - **New Code:**
        - The functionality of `handleEnemyPenetration()` was refactored into the `EnemyManager` class's `handleEnemyPenetration()` method.

      <br>
      
      - **Reasoning for Modification:**
        - **Centralized Enemy Management:** By moving enemy penetration handling to the `EnemyManager` class, all enemy-related operations are centralized in one place. This makes it easier to manage and extend enemy behavior.
        - **Reusability:** The `EnemyManager` class can be reused across different levels, reducing code duplication and making the codebase more modular.

    <br>

    - `updateLevelView()`: This method was responsible for updating the level view. It was refactored into the `LevelScene` class's `update()` method.
      - **Old Code:**
        - The `updateLevelView()` method in `LevelParent` was responsible for updating the level view, specifically the heart display.

      <br>

      - **New Code:**
        - The functionality of `updateLevelView()` was refactored into the `LevelScene` class's `update()` method.

      <br>

      - **Reasoning for Modification:**
        - **Centralized View Management:** By moving view update logic to the `LevelScene` class, all view-related operations are centralized in one place. This makes it easier to manage and extend view behavior.
        - **Reusability:** The `LevelScene` class can be reused across different levels, reducing code duplication and making the codebase more modular.

    <br>

    - `updateKillCount()`: This method was responsible for updating the kill count. It was refactored into the `KillManager` class's `updateKillCount()` method.
      - **Old Code:**
        - The `updateKillCount()` method in `LevelParent` was responsible for updating the kill count based on the number of destroyed enemies.

      <br>

      - **New Code:**
        - The functionality of `updateKillCount()` was refactored into the `KillManager` class's `updateKillCount()` method.

      <br>

      - **Reasoning for Modification:**
        - **Centralized Kill Management:** By moving kill count update logic to the `KillManager` class, all kill-related operations are centralized in one place. This makes it easier to manage and extend kill count behavior.
        - **Reusability:** The `KillManager` class can be reused across different levels, reducing code duplication and making the codebase more modular.

    <br>

    - `winGame()`, `loseGame()`: These methods were responsible for handling game over conditions. They were refactored into the `LevelScene` class's `checkIfGameOver()` method.
      - **Old Code:**
        - The `winGame()` and `loseGame()` methods in `LevelParent` were responsible for handling game over conditions.
    
        ```java
        protected void winGame() {
            timeline.stop();
            levelView.showWinImage();
        }
    
        protected void loseGame() {
            timeline.stop();
            levelView.showGameOverImage();
        }
        ```
    
      <br>

      - **New Code:**
        - The functionality of `winGame()` and `loseGame()` was refactored into the `LevelScene` class's `checkIfGameOver()` method.
    
        ```java
        protected void checkIfGameOver() {
            if (userUnit.getIsDestroyed()) {
                Game.getInstance(null).setStateEndGame();
                AudioManager.getInstance().fireEffectAudio(EffectAudioType.GAME_OVER);
                goToScene(LOSE_SCENE);
    
            } else if (userKillTargetLogic()) {
                AudioManager.getInstance().fireEffectAudio(EffectAudioType.TRANSITION);
                userKillTargetReachedAction();
            }
        }
        ```
    
      <br>

      - **Reasoning for Modification:**
        - **Centralized Game Over Management:** By moving game over handling to the `LevelScene` class, all game over-related operations are centralized in one place. This makes it easier to manage and extend game over behavior.
        - **Reusability:** The `LevelScene` class can be reused across different levels, reducing code duplication and making the codebase more modular.

<br>
<a id="mclass-levelviewleveltwo"></a>

3. **LevelViewLevelTwo.java** <font color="red"><b>(Deleted)</b></font>  
  - **Location:** `src/main/java/com/example/demo/LevelViewLevelTwo.java` <font color="red"><b>(Deprecated)</b></font>
  - **Package:** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>
  - **Usage:** The original `LevelViewLevelTwo` class extended `LevelView` and added specific functionality for managing the shield image in level two.
  - **Modification:** The `LevelViewLevelTwo` class was removed as the shield image logic was moved to the `Boss` class in the new design. The new design uses the `Boss` class to manage the shield image, making the code more modular and easier to maintain.
  - **Modified Methods:**
    - `addImagesToRoot()`: This method was removed as the shield image logic was moved to the `Boss` class.
      - **Old Code:**
        ```java
        private void addImagesToRoot() {
            root.getChildren().addAll(shieldImage);
        }
        ```

      <br>

      - **New Code:**
        - The `addImagesToRoot()` method was removed.

      <br>

      - **Reasoning for Modification:**
        - **Logic Transfer:** The shield image logic was moved to the `Boss` class, where it is more appropriately managed. This decision was made to centralize the management of the boss's shield, making the code more modular and easier to maintain.
        - **Reusability:** By moving the shield image logic to the `Boss` class, the `LevelViewLevelTwo` class becomes more focused on level-specific UI components, enhancing its reusability across different levels.

    <br>

    - `showShield()`: This method was removed as the shield image logic was moved to the `Boss` class.
      - **Old Code:**
        ```java
        public void showShield() {
            shieldImage.showShield();
        }
        ```

      <br>

      - **New Code:**
        - The `showShield()` method was removed.

      <br>

      - **Reasoning for Modification:**
        - **Logic Transfer:** The shield image logic was moved to the `Boss` class, where it is more appropriately managed. This decision was made to centralize the management of the boss's shield, making the code more modular and easier to maintain.
        - **Reusability:** By moving the shield image logic to the `Boss` class, the `LevelViewLevelTwo` class becomes more focused on level-specific UI components, enhancing its reusability across different levels.

    <br>

    - `hideShield()`: This method was removed as the shield image logic was moved to the `Boss` class.
      - **Old Code:**
        ```java
        public void hideShield() {
            shieldImage.hideShield();
        }
        ```

      <br>

      - **New Code:**
        - The `hideShield()` method was removed.

      <br>

      - **Reasoning for Modification:**
        - **Logic Transfer:** The shield image logic was moved to the `Boss` class, where it is more appropriately managed. This decision was made to centralize the management of the boss's shield, making the code more modular and easier to maintain.
        - **Reusability:** By moving the shield image logic to the `Boss` class, the `LevelViewLevelTwo` class becomes more focused on level-specific UI components, enhancing its reusability across different levels.








<br>
<a id="mclass-winimage"></a>

4. **WinImage.java** <font color="red"><b>(Deleted)</b></font>  
  - **Location:** `src/main/java/com/example/demo/WinImage.java` <font color="red"><   (Deprecated)</b></font> 
  - **Package:** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>  
  - **Usage:** The original `WinImage` class provided functionality for displaying a   "YouWin" image when the player wins the game.
  - **Reason:** The class was removed because the functionality was integrated into    the`WinScene` class, which provides a more comprehensive and modular approach to    handlingthe win scene.
  - **Modification:**
    - **Old Code:**
      ```java
      public WinScene(double screenWidth, double screenHeight) {
          super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight);

          // Create the title image
          ImageView title = new ImageView(new Image(getClass().getResource("/com/example/demo/images/end/you_win.png").toExternalForm()));
          title.setFitWidth(750); 
          title.setPreserveRatio(true); 

          // Add title to the scene
          getRoot().getChildren().add(title);
      }
      ```
      
    <br>

    - **Reasoning for Modification:** The `WinScene` class now handles the display of  the"You Win" image, making the code more modular and easier to maintain.  


<br>
<a id="mclass-gameoverimage"></a>

5. **GameOverImage.java** <font color="red"><b>(Deleted)</b></font>  
  - **Location:** `src/main/java/com/example/demo/GameOverImage.java` <font color="red"><b>(Deprecated)</b></font>   
  - **Package:** `com.example.demo` <font color="red"><b>(Deprecated)</b></font>  
  - **Usage:** The original `GameOverImage` class provided functionality for displaying a  "Game Over" image when the player loses the game.
  - **Reason:** The class was removed because the functionality was integrated into the  `LoseScene` class, which provides a more comprehensive and modular approach to handling  the lose scene.
  - **Modification:**
    ```java
    public LoseScene(double screenWidth, double screenHeight) {
        super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight);

        // Create the title image
        ImageView title = new ImageView(new Image(getClass().getResource("/com/example  demo/images/end/game_over.png").toExternalForm()));
        title.setFitWidth(750); 
        title.setPreserveRatio(true); 

        // Add title to the scene
        getRoot().getChildren().add(title);
    }
    ```

    <br>

    - **Reasoning for Modification:** The `LoseScene` class now handles the display of the "Game Over"  image, making the code more modular and easier to maintain.


<br><br><br><br><br>


## Unexpected Problems

- **Problem 1: Dependency Conflicts**
  - Description: During the Maven build, there were conflicts between different versions of the same dependency.
  - Resolution: Resolved by explicitly specifying the compatible versions in the `pom.xml` file.


## Project Structure

```
your-repo-name/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   └── example/
│   │   │   │       └── demo/
│   │   │   │           ├── Main.java
│   │   │   │           ├── Player.java
│   │   │   │           ├── GameController.java
│   │   │   │           └── ...
│   │   └── resources/
│   │       ├── images/
│   │       │   └── background1.jpg
│   │       └── ...
├── target/
│   └── your-game.jar
├── pom.xml (if using Maven)
└── README.md
```
