# Sky Battle

---

## Overview
This is a simple JavaFX game created using Java and JavaFX. The game features basic gameplay mechanics and is designed to demonstrate the use of JavaFX for creating graphical applications.



## Git Commits 
The Git Commits in this Repo abides to the Atomic Commits style as best as possible for easier Commit tracing. Thus, a larger ammount of Commits are present and are expected due to the styling of Commits.



## Bug Fixes
- **Fixed InvocationTargetException Thrown Alert Box**: Exception thrown due to wrong naming and not using name constant declared | fixed by renaming and using constant

- **Added stopTimeline to Stop Game Logic**: Added and called stopTimeline method to stop timeline from running game logic endlessly, which checks and calls goToNextLevel() method, which endlesssly create new LevelTwo instances until garbage collection

- **Cropped Image to Fix HitBox Too Large**: Cropped image to reduce HitBox, recalculated and reassign image scalling

- **Modified EnemyPlane Despawn**: Modified EnemyPlane to only despawn if entire plane has left screen view instead of only the front of the EnemyPlane touching the edge of screen

- **Added Deletion of Projectiles**: Deletion of projectiles when projectiles have move out of the screen

- **Fixed ShieldImage Display**: Fixed BossPlane ShieldImage not displaying issue

- **Fixed EnemyPlane Penetration Bug**: Fixed EnemyPlane penetration triggering kill count +1 (Penetration of EnemyPlane was previously handled as an EnemyPlane kill)

- **Fixed EnemyPlane Collision Bug**: Fixed EnemyPlane collision with UserPlane triggering kill count +1 (Collision of UserPlane and EnemyPlane was previously handled as an EnemyPlane kill)



## New Java Class
- **LoggerUtil**: Add LoggerUtil class for static centralized logger for uniform debug and tracking log. Enables logging to both console and log file

- **GameLoop**: 

- **GameState**: 

- **GameSceneManager**: 

- **GameSceneFactory**: Factory class for creating different game scenes

- **LevelOne, LevelTwo**: Concrete implementations of `LevelScene` for the first and second levels

- **ActorManager, CollisionManager, EnemyManager, KillManager, ProjectileManager**: Services to manage actors, collisions, enemies, kills, and projectiles respectively

- **LevelState**: Manage Level State Component

- **LevelView**: Manage Level View Component

- **EnumUtil**: Contains enums for game states and scene types


- **GameState**: 
- **GameState**: 
- **GameState**: 
- **GameState**: 
- **GameState**: 
- **GameState**: 
- **GameState**: 



## Modified Java Class
- **Controller => Game**: Centralized controller for entire core of game 

- **LevelParent => GameScene + LevelScene**: Abstract class to be extended, managing game scenes and level scenes related resources, including background, root group, scene updates, enemy units, and projectiles


- **GameState**: 
- **GameState**: 
- **GameState**: 
- **GameState**: 
- **GameState**: 
- **GameState**: 
- **GameState**: 




## Additional Features

- **NA**: NA

## Prerequisites

- **Added .gitignore Item**: Added .gitignore to ignore log file 


- Java Development Kit (JDK) 8 or later
- JavaFX SDK (if not included with your JDK)
- Maven (optional, for building and managing dependencies)

add finalize() bllock to all for memory safe checking 
</br>

---

## Getting Started

### Running the Game

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/your-repo-name.git
   cd your-repo-name
   ```

2. **Build the Project using Maven**:
   ```bash
   mvn clean install
   ```

3. **Run the Game**:
   - If using an IDE (e.g., IntelliJ IDEA, Eclipse), open the project and run the `Main` class.
   - If using the command line, navigate to the project directory and run:
     ```bash
     java -cp target/your-game.jar com.example.demo.Main
     ```

### Controls

- **Arrow Keys**: Move the player character.
- **Spacebar**: Perform an action (e.g., jump, shoot).

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

## Dependencies

- JavaFX SDK
- (Any other dependencies you might have)

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue if you find any bugs or have suggestions for improvements.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Acknowledgments

- Thanks to the JavaFX community for providing excellent documentation and resources.
- (Any other acknowledgments you might have)