# Sky Battle

---

## Overview

This is a simple JavaFX game created using Java and JavaFX. The game features basic gameplay mechanics and is designed to demonstrate the use of JavaFX for creating graphical applications.

## Git Commits 
The Git Commits in this Repo abides to the Atomic Commits style as best as possible for easier Commit tracing. Thus, a larger ammount of Commits are present and are expected due to the styling of Commits.

## Bug Fixes
- **Fixed InvocationTargetException Thrown Alert Box**: Exception thrown due to wrong naming and not using name constant declared | fixed by renaming and using constant
![Fixed InvocationTargetException Thrown Alert Box](./readme-images/bug-fixes-1.png)

## Additional Features

- **NA**: NA

## Prerequisites

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