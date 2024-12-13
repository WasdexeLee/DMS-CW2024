# Sky Battle


## Student
- **Full Name (as per NRIC):** Lee Jia Zhe
- **Student ID:** 20516230


<br>


## Controls
- **Arrow Keys / WASD Keys**: Move the player character Left, Right, Up, Down.
- **Spacebar Tap**: Shoot Projectile.
- **Spacebar Hold**: Burt Fire Projectile.


<br>


## Git Commits 
The Git Commits in this Repo abides to the Atomic Commits style as best as possible for easier Commit tracing. Thus, a larger ammount of Commits are present and are expected due to the styling of Commits.


<br>


## GitHub Repository
[Link to GitHub Repository](https://github.com/WasdexeLee/DMS-CW2024)
[https://github.com/WasdexeLee/DMS-CW2024](https://github.com/WasdexeLee/DMS-CW2024)


<br>


## Compilation Instructions
1. **Clone the Repository:**
  ```bash
  git clone https://github.com/WasdexeLee/DMS-CW2024
  cd DMS-CW2024
  ```

<br>

2. **Install Dependencies:**
  - Ensure you have Java JDK 21 (21.0.5) installed. (Amazon Corretto 21.0.5 for IntelliJ)
    - **Java JDK 21 Download Link (from Oracle):** https://www.oracle.com/my/java/technologies/downloads/#jdk21-windows
    - **Java JDK Setup Tutorial:** https://docs.oracle.com/en/java/javase/21/install/overview-jdk-installation.html

  <br>

  - Ensure you have Apache Maven (3.9.8) installed.
    - **Apache Maven 3.9.8 Download Link (from Apache Maven Project):** https://dlcdn.apache.org/maven/maven-3/3.9.8/binaries/apache-maven-3.9.8-bin.zip
    - **Apache Maven 3.9.8 Installation Tutorial:** https://maven.apache.org/install.html

  <br>
  
  - The game has been developed and tested to be fully functioning in <b>Java JDK 21 (21.0.5) | Apache Maven (3.9.8) | Windows 11 Home 23H2</b>. Other versions of Java JDK, Apache Maven and Windows are not officially supported and may result in unwanted behaviour. 
  - Other dependencies such as JavaFX, JUnit, Surefire etc. have been configured in the `pom.xml`

<br>

3. **Run the Game:**
  - **Run in Command Line**
    - Ensure that you are in the directory of `DMS-CW2024\`

    ```bash
    mvn clean javafx:run
    ```
    
  - **Run in IntelliJ**
    - Open clone repo as project in IntelliJ
    - Go to Maven Sidepanel
    - Under Plugins, JavaFX, click javafx:run


  - The game should now be running.
  
<br>

4. **Run JUnit Tests:**
  - Ensure that you are in the directory of `DMS-CW2024\`
  - Ensure that no other JVM instances or Java apps are running (including other coursework games) as JavaFX might give certain errors and result in Build Failure
  - In Co
  
  ```bash
  mvn clean test
  ```

  - The process make take a bit of time, and might ramp up the CPU usage to high or even 100% usage. 
  - Such situations are totally normal as a new JVM instance is used for every JUnit Test Class.
  - New JVM instance is use as integration testing of `JavaFX` components require `TestFX` which has been included in the `pom.xml`.
  - The use of `TestFX` and `ApplicationTest` is a bit delicate and requires a new JVM instance to ensure no carry over setup from other Test Classes.
  - There also may be `Build Errors`. This is infrequent but can happen. However, when checking the test cases, they should be fine and are all passed.
  - In the case that there are `Errors` and not `Failures`, re-run the test again as it maybe due to another JVM instance causing errors.

<br>

5. **Viewing JavaDoc:**
  - Navigate to the Javadoc folder included in the `COMP2042LeeJiaZhe.zip`
  - Ensure that you are in the directory of `Javadoc\`
  - Click / run `index.html` to view the Javadoc


<br><br><br>


## Bug Fixes
- **Fixed InvocationTargetException Thrown Alert Box:**
  - **Issue:** 
    -An `InvocationTargetException` was thrown due to incorrect naming and the failure to use a predefined constant for the image path, causing an alert box to appear unexpectedly.

  - **Resolution:**
    - Corrected the image path by using the predefined constant (`IMAGE_NAME`) instead of a hardcoded path.
    - Updated the constant to reflect the correct resource location (`/com/example/demo/images/shield.png`).

<br>

- **Reduced Hitbox by Cropping Images and Recalculating Heights:**
  - **Issue:**
    - The hitboxes of game objects were too large, leading to unintended collisions and gameplay inconsistencies.

  - **Resolution:**
    - Cropped images for various game objects (e.g., `Boss`, `EnemyPlane`, `UserPlane`, `EnemyProjectile`, `UserProjectile`) to reduce their visual size.
    - Recalculated the `IMAGE_HEIGHT` for each object to match the new dimensions, ensuring accurate hitbox adjustments.
    - Updated the corresponding image files in the resources to reflect the cropped versions.

<br>

- **Modified EnemyPlane Despawning After Entire Plane Exists Screen View:**
  - **Issue:**
    - Enemy planes were despawning only when their center point exited the screen, which could lead to them still being partially visible and causing unintended gameplay behavior.

  - **Resolution:**
    - Adjusted the condition for despawning enemy planes to ensure they are removed only after their entire plane (including its bounds) has exited the screen.
    - Updated the `enemyHasPenetratedDefenses` method to account for the full width of the enemy plane when checking if it has left the screen.

<br>

- **Added Deletion of Projectiles Moving Out of Screen:**
  - **Issue:**
    - Projectiles (both enemy and user) were not being removed when they moved out of the screen, leading to unnecessary memory usage and potential performance issues.

  - **Resolution:**
    - Implemented a `handleProjectileOutOfScreen` method in `LevelParent` to detect and destroy projectiles that have exited the screen.
    - Called this method for both `enemyProjectiles` and `userProjectiles` during the `updateScene` process to ensure timely removal of out-of-bounds projectiles.
    - Added a `finalize` method to `EnemyProjectile` for memory validation during garbage collection.

<br>

- **Fixed BossPlane ShieldImage Not Displaying Issue:**
  - **Issue:**
    - The `ShieldImage` for the `BossPlane` was not displaying correctly, preventing the shield from being visually represented during gameplay.

  - **Resolution:**
    - Updated the `Boss` class to include methods for tracking the shield's state and movement.
    - Modified the `LevelViewLevelTwo` class to properly show and hide the `ShieldImage` based on the `Boss` shield state.
    - Ensured the `ShieldImage` moves in sync with the `BossPlane` by updating its position during each frame.
    - Adjusted the `ShieldImage` positioning and scaling to match the `BossPlane` correctly.

<br>

- **Fixed EnemyPlane Penetration Handled as EnemyPlane Kill:**
  - **Issue:**
    - When an `EnemyPlane` penetrated the player's defenses, it was incorrectly being counted as a kill, leading to inaccurate kill counts and gameplay inconsistencies.

  - **Resolution:**
    - Adjusted the game logic to ensure that `EnemyPlane` penetration does not trigger a kill count.
    - Moved the `removeAllDestroyedActors()` method call before `updateNumberOfEnemies()` to ensure that destroyed actors are properly removed before updating the enemy count.

<br>

- **Fixed Shadow of Button 'Start Game' and 'Exit' Being Clickable:**
  - **Issue:**
    - The shadow effect applied to the "Start Game" and "Exit" buttons was clickable, allowing users to interact with it and trigger unintended actions.

  - **Resolution:**
    - Wrapped the buttons in a `StackPane` to ensure that only the button area itself is clickable, preventing interaction with the shadow.
    - Adjusted the shadow properties to improve visual appearance without affecting functionality.
    - Updated the button styling to ensure proper background image rendering and alignment.

<br>

- **Fixed Bug of Previous Action (Left, Right, Fire) of UserPlane Continues After Pause:**
  - **Issue:**
    - When the game was paused, the `UserPlane` continued to perform its previous actions (e.g., moving left, right, or firing) after resuming, leading to unintended behavior.

  - **Resolution:**
    - Added calls to stop all user actions (`stopLeft()`, `stopRight()`, `stopUp()`, `stopDown()`, and `setIsFiring(false)`) when the game is paused.
    - This ensures that the `UserPlane` remains idle during the pause and does not continue its previous actions after resuming.


<br><br><br>


## Implemented and Working Properly

1. **Added Main Menu Scene**
  - **Why is this feature needed?**
    - A main menu is essential for any game as it provides a user-friendly interface for players to start the game, exit, or access other options (in future implementations). It enhances the user experience by allowing players to navigate the game easily and provides a clear starting point.

  <br>

  - **How is it implemented?**
    - A new `MenuScene` class is created under `src/main/java/com/example/demo/scenes/MenuScene.java`. This class extends the abstract `GameScene` class and implements the main menu UI.

  <br>

  - **Implementation Steps:**
    1. **Class Definition and Constructor:**
      - The `MenuScene` class is defined with a constructor that takes the screen width and height as parameters.
      - The constructor initializes the background image and sets up the UI components.

      ```java
      public class MenuScene extends GameScene {
          private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/menu_background.jpg";
          private static final SceneType NEXT_SCENE = SceneType.LEVEL1;

          public MenuScene(double screenWidth, double screenHeight) {
              super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight);
              // UI setup code here
          }
      }
      ```

    <br>

    2. **UI Components:**
      - A `Text` object is created to display the game title.
      - Two `Button` objects are created for "Start Game" and "Exit".

      ```java
      Text title = new Text("Sky Battle");
      title.setFill(Color.WHITE);
      title.setFont(Font.font("Monospaced", FontWeight.BOLD, 120));
      title.setFill(Color.web("#D3D3D3"));
      title.setStroke(Color.BLACK);
      title.setStrokeWidth(4);

      Button startButton = createButton("Start Game", 240);
      Button exitButton = createButton("Exit", 170);
      ```

    <br>
    
    3. **Button Actions:**
      - The `startButton` triggers the transition to the next scene (`LEVEL1`).
      - The `exitButton` exits the application.

      ```java
      startButton.setOnAction(e -> {
          System.out.println("Start Game selected");
          goToScene(NEXT_SCENE);
      });

      exitButton.setOnAction(e -> System.exit(0));
      ```

    <br>

    4. **Layout:**
      - The buttons and title are organized in a `VBox` layout for proper alignment.

      ```java
      VBox buttonBox = new VBox(40, startButton, exitButton);
      buttonBox.setAlignment(Pos.CENTER);

      VBox mainLayout = new VBox(120, title, buttonBox);
      mainLayout.setAlignment(Pos.TOP_CENTER);
      mainLayout.setPadding(new Insets(100));
      mainLayout.setPrefSize(screenWidth, screenHeight);

      getRoot().getChildren().add(mainLayout);
      ```

    <br>

    5. **Integration with GameSceneManager and GameSceneFactory:**
      - The `MenuScene` is integrated into the game by adding it to the `GameSceneFactory` and initializing it in `GameSceneManager`.

      ```java
      public class GameSceneFactory {
          public GameScene createScene(SceneType sceneType, double screenWidth, double screenHeight) {
              switch (sceneType) {
                  case MENU:
                      return new MenuScene(screenWidth, screenHeight);
                  // Other cases
              }
          }
      }

      public class GameSceneManager {
          public GameSceneManager(Stage stage) {
              this.currentGameScene = this.gameSceneFactory.createScene(SceneType.MENU, this.screenWidth, this.screenHeight);
          }
      }
      ```

  <br>

  - **Future Improvements:**
    - Add more menu options (e.g., settings, credits, high scores).
    - Implement animations or transitions when switching between scenes.


<br>


2. **Updated Game Initialization to Start from Main Menu**
  - **Why is this feature needed?**
    - Starting the game directly from the main menu ensures a consistent and user-friendly flow. It prevents the game from launching into a level without player interaction and aligns with standard game design practices.

  <br>

  - **How is it implemented?**
    - The `GameSceneManager` is updated to initialize the game with the `MenuScene` instead of directly loading the first level (`LEVEL1`).

  <br>

  - **Implementation Steps:**
    1. **Modify GameSceneManager:**
      - The `GameSceneManager` constructor is updated to create the `MenuScene` as the initial scene.
      ```java
      public class GameSceneManager {
          public GameSceneManager(Stage stage) {
              this.currentGameScene = this.gameSceneFactory.createScene(SceneType.MENU, this.screenWidth, this.screenHeight);
              this.stage.setScene(this.currentGameScene.getScene());
          }
      }
      ```

    <br>

    2. **Ensure MenuScene is Created:**
      - The `GameSceneFactory` is updated to handle the `MENU` scene type.
      ```java
      public class GameSceneFactory {
          public GameScene createScene(SceneType sceneType, double screenWidth, double screenHeight) {
              switch (sceneType) {
                  case MENU:
                      return new MenuScene(screenWidth, screenHeight);
                  // Other cases
              }
          }
      }
      ```

  <br>

  - **Future Improvements:**
    - Add a splash screen before the main menu to enhance the game's presentation.
    - Allow players to select different game modes (e.g., easy, medium, hard) from the menu.


<br>


3. **Left and Right Movement of UserPlane**
  - **Why is this feature needed?**
    - The `UserPlane` class now supports left and right movement in addition to the existing up and down movement. This allows the player to navigate the plane horizontally within the game screen. Horizontal movement is essential for a 2D shooter game to provide more dynamic gameplay. It allows the player to dodge enemy attacks more effectively and position the plane for better firing angles.

  <br>

  - **Implementation Steps:**
    1. **Addition of Horizontal Bounds:**
      - The `X_LEFT_BOUND` and `X_RIGHT_BOUND` constants were added to define the horizontal boundaries of the game screen.
      ```java
      private static final double X_LEFT_BOUND = 0;
      private static final double X_RIGHT_BOUND = 400;
      ```

    <br>

    2. **Horizontal Movement Logic:**
      - The `moveLeft()` and `moveRight()` methods were added to handle the horizontal movement.
      ```java
      public void moveLeft() {
          leftPressed = true;
          xVelocityMultiplier = -1;
      }

      public void moveRight() {
          rightPressed = true;
          xVelocityMultiplier = 1;
      }
      ```

    <br>

    3. **Stopping Horizontal Movement:**
      - The `stopLeft()` and `stopRight()` methods were added to stop the horizontal movement when the corresponding keys are released.
      ```java
      public void stopLeft() {
          leftPressed = false;
          if (rightPressed)
              xVelocityMultiplier = 1;
          else
              xVelocityMultiplier = 0;
      }

      public void stopRight() {
          rightPressed = false;
          if (leftPressed)
              xVelocityMultiplier = -1;
          else
              xVelocityMultiplier = 0;
      }
      ```

    <br>

    4. **Updating Position:**
      - The `updatePosition()` method was modified to handle horizontal movement.
      ```java
      private void updatePosition() {
          if (isMovingX()){
              double initialTranslateX = getTranslateX();
              this.moveHorizontally(VERTICAL_VELOCITY * xVelocityMultiplier);
              double newPosition = getLayoutX() + getTranslateX();

              if (newPosition < X_LEFT_BOUND || newPosition > X_RIGHT_BOUND) {
                  this.setTranslateX(initialTranslateX);
              }
          }
      }
      ```

    <br>

    5. **Checking Horizontal Movement:**
      - The `isMovingX()` method was added to check if the plane is moving horizontally.
      ```java
      private boolean isMovingX() {
          return xVelocityMultiplier != 0;
      }
      ```

    <br>

  - **Future Improvements:**
    - **Variable Speed:** Implementing variable speed for horizontal movement based on the duration of key press.
    - **Smooth Movement:** Adding acceleration and deceleration to make the movement feel more natural.


<br>


4. **Continuous Firing for UserPlane Despite Moving**
  - **Why is this feature needed?**
    - The `UserPlane` can now continuously fire projectiles while moving, allowing for more continuous and dynamic gameplay. Continuous firing is a common feature in shooter games, allowing the player to maintain a steady stream of projectiles without needing to repeatedly press the fire button.

  <br>

  - **Implementation Steps:**
    1. **Firing State Management:**
      - The `isFiring` boolean variable was added to track whether the player is currently firing.
      ```java
      private boolean isFiring;

      public void setIsFiring(boolean isFiring) { 
          this.isFiring = isFiring;
      }

      public boolean getIsFiring() { 
          return isFiring;
      }
      ```

    <br>

    2. **Key Event Handling:**
      - The `setOnKeyPressed` and `setOnKeyReleased` methods were modified to set the `isFiring` state when the space bar is pressed or released.
      ```java
      getBackground().setOnKeyPressed(new EventHandler<KeyEvent>() {
          public void handle(KeyEvent e) {
              KeyCode kc = e.getCode();
              if (kc == KeyCode.SPACE)
                  userUnit.setIsFiring(true);
          }
      });

      getBackground().setOnKeyReleased(new EventHandler<KeyEvent>() {
          public void handle(KeyEvent e) {
              KeyCode kc = e.getCode();
              if (kc == KeyCode.SPACE)
                  userUnit.setIsFiring(false);
          }
      });
      ```

    <br>

    3. **Projectile Spawning:**
      - The `frameCounter` was added to control the rate of projectile spawning. The `projectileManager.spawnProjectile` method is called every 1/10th of a second if the player is firing.
      ```java
      frameCounter++;
      if (frameCounter == (int) GameLoop.getInstance(null).get_TARGET_FPS() / 10) {
          if (userUnit.getIsFiring() && frameCounter == (int) GameLoop.getInstance(null).get_TARGET_FPS() / 10) {
              projectileManager.spawnProjectile(Arrays.asList(userUnit), userProjectiles, root);
          }
          frameCounter = 0;
      }
      ```

  <br>

  - **Future Improvements:**
    - **Fire Rate Control:** Implementing a fire rate control system to allow players to adjust the rate of fire.
    - **Weapon Upgrades:** Adding weapon upgrades that can increase the fire rate or change the type of projectiles fired.

<br>

5. **Multi-Key Press Control and Release**
  - **Why is this feature needed?**
    - The `UserPlane` can now handle multiple key presses simultaneously, allowing for more complex control schemes. Multi-key press control is essential for allowing players to perform complex maneuvers, such as moving diagonally or firing while moving.

  <br>

  - **Implementation Steps:**
    1. **Key State Tracking:**
      - The `leftPressed`, `rightPressed`, `upPressed`, and `downPressed` boolean variables were added to track the state of each key.
      ```java
      private boolean leftPressed;
      private boolean rightPressed;
      private boolean upPressed;
      private boolean downPressed;
      ```

    <br>

    2. **Key Event Handling:**
      - The `setOnKeyPressed` and `setOnKeyReleased` methods were modified to update the state of each key when pressed or released.
      ```java
      getBackground().setOnKeyPressed(new EventHandler<KeyEvent>() {
          public void handle(KeyEvent e) {
              KeyCode kc = e.getCode();
              if (kc == KeyCode.LEFT || kc == KeyCode.A)
                  userUnit.moveLeft();
              if (kc == KeyCode.RIGHT || kc == KeyCode.D)
                  userUnit.moveRight();
              if (kc == KeyCode.UP || kc == KeyCode.W)
                  userUnit.moveUp();
              if (kc == KeyCode.DOWN || kc == KeyCode.S)
                  userUnit.moveDown();
          }
      });

      getBackground().setOnKeyReleased(new EventHandler<KeyEvent>() {
          public void handle(KeyEvent e) {
              KeyCode kc = e.getCode();
              if (kc == KeyCode.LEFT || kc == KeyCode.A)
                  userUnit.stopLeft();
              if (kc == KeyCode.RIGHT || kc == KeyCode.D)
                  userUnit.stopRight();
              if (kc == KeyCode.UP || kc == KeyCode.W)
                  userUnit.stopUp();
              if (kc == KeyCode.DOWN || kc == KeyCode.S)
                  userUnit.stopDown();
          }
      });
      ```

    <br>

    3. **Velocity Multiplier Adjustment:**
      - The `xVelocityMultiplier` and `yVelocityMultiplier` variables were adjusted based on the state of the keys.
      ```java
      public void stopLeft() {
          leftPressed = false;
          if (rightPressed)
              xVelocityMultiplier = 1;
          else
              xVelocityMultiplier = 0;
      }

      public void stopRight() {
          rightPressed = false;
          if (leftPressed)
              xVelocityMultiplier = -1;
          else
              xVelocityMultiplier = 0;
      }
      ```

  <br>

  - **Future Improvements:**
    - **Diagonal Movement:** Implementing diagonal movement by combining horizontal and vertical velocity multipliers.
    - **Custom Key Bindings:** Allowing players to customize key bindings for movement and firing.

<br>

6. **Added Win and Lose Scenes**
  - **Why is this feature needed?**
    - Win and Lose scenes are essential for providing feedback to the player about the outcome of the game. They enhance the user experience by clearly indicating whether the player has won or lost, and they provide options to replay the game or return to the main menu.

  <br>

  - **How is it implemented?**
    - Two new classes, `WinScene` and `LoseScene`, are created under `src/main/java/com/example/demo/scenes/`. These classes extend the abstract `GameScene` class and implement the UI for the "You Win" and "Game Over" screens, respectively.

  <br>

  - **Implementation Steps:**
    1. **Class Definitions and Constructors:**
      - Both `WinScene` and `LoseScene` classes are defined with constructors that take the screen width and height as parameters.
      - The constructors initialize the background image and set up the UI components.

      ```java
      public class WinScene extends GameScene {
          private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/end/end_background.jpeg";
          private static final SceneType YES_SCENE = SceneType.LEVEL1;
          private static final SceneType NO_SCENE = SceneType.MENU;

          public WinScene(double screenWidth, double screenHeight) {
              super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight);
              // UI setup code here
          }
      }
      ```

    <br>
  
    2. **UI Components:**
      - An `ImageView` is used to display the "You Win" or "Game Over" title.
      - Another `ImageView` is used to display the "Play Again" text.
      - Two `Button` objects are created for "Yes" (to replay the game) and "No" (to return to the main menu).

      ```java
      ImageView title = new ImageView(new Image(getClass().getResource("/com/example/demo/images/end/you_win.png").toExternalForm()));
      title.setFitWidth(750); 
      title.setPreserveRatio(true); 

      ImageView playAgain = new ImageView(new Image(getClass().getResource("/com/example/demo/images/end/play_again.png").toExternalForm()));
      playAgain.setFitWidth(450); 
      playAgain.setPreserveRatio(true); 

      Button yesButton = createButton(getClass().getResource("/com/example/demo/images/end/yes.png").toExternalForm());
      Button noButton = createButton(getClass().getResource("/com/example/demo/images/end/no.png").toExternalForm());
      ```

    <br>

    3. **Button Actions:**
      - The `yesButton` triggers a scene transition to `LEVEL1`.
      - The `noButton` triggers a scene transition to `MENU`.

      ```java
      yesButton.setOnAction(e -> goToScene(YES_SCENE));
      noButton.setOnAction(e -> goToScene(NO_SCENE));
      ```

    <br>

    4. **Layout:**
      - The buttons and images are organized in a `VBox` layout for proper alignment.

      ```java
      VBox mainLayout = new VBox(40, title, spacer, playAgain, buttonBox);
      mainLayout.setAlignment(Pos.TOP_CENTER);
      mainLayout.setPadding(new Insets(100));
      mainLayout.setPrefSize(screenWidth, screenHeight);

      getRoot().getChildren().add(mainLayout);
      ```

    <br>

    5. **Integration with GameSceneManager and GameSceneFactory:**
      - The `WinScene` and `LoseScene` are integrated into the game by adding them to the `GameSceneFactory` and initializing them in `GameSceneManager`.

      ```java
      public class GameSceneFactory {
          public GameScene createScene(SceneType sceneType, double screenWidth, double screenHeight) {
              switch (sceneType) {
                  case LOSESCENE:
                      return new LoseScene(screenWidth, screenHeight);
                  case WINSCENE:
                      return new WinScene(screenWidth, screenHeight);
                  // Other cases
              }
          }
      }
      ```

  <br>

  - **Future Improvements:**
    - Add animations or transitions when switching to the Win or Lose scenes.
    - Include additional options, such as viewing high scores or sharing the result on social media.


<br>
  

7. **Added New Playable Level**
  - **Why is this feature needed?**
    - Adding new levels to the game increases its replayability and complexity. It allows players to experience new challenges and keeps the gameplay engaging. By introducing a new `LevelTwo` and moving the previous `LevelTwo` to `LevelThree`, the game now has a structured progression system.

  <br>

  - **How is it implemented?**
    - The previous `LevelTwo` class is renamed to `LevelThree`, and a new `LevelTwo` class is created to introduce a new level with different enemy types and gameplay mechanics. 

  <br>

  - **Implementation Steps:**
    1. **Create New LevelTwo:**
      - A new `LevelTwo` class is created with updated gameplay mechanics, including a highernumber of enemies and a different spawn probability.
      ```java
      public class LevelTwo extends LevelScene {
          private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/imagesbackground2.jpg";
          private static final SceneType NEXT_SCENE = SceneType.LEVEL3;
          private static final int TOTAL_ENEMIES = 8;
          private static final int KILLS_TO_ADVANCE = 10;
          private static final double ENEMY_SPAWN_PROBABILITY = 7.0 / (GameLoop.getInstanc(null).get_TARGET_FPS());

          public LevelTwo(double screenWidth, double screenHeight) {
              super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight, PLAYER_INITIAL_HEALTH);
              // New level setup
          }

          @Override
          protected void userKillTargetReachedAction() {
              goToScene(NEXT_SCENE);
          }
      }
      ```

  <br>

  - **Future Improvements:**
  - Add more levels with increasing difficulty.
  - Introduce unique mechanics or objectives for each level (e.g. time limits).
  - Include level-specific rewards or achievements.


<br>


8. **Updated Creation of Enemy Planes**
  - **Why is this feature needed?**
    - Varied enemy types make the game more challenging and interesting. By allowing different image names and fire rates for enemy planes, the game can introduce more diverse enemies, enhancing the gameplay experience.

  <br>

  - **How is it implemented?**
    - The `EnemyPlane` class is updated to accept different image names and fire rates, enabling the creation of varied enemy types.

  <br>
  
  - **Implementation Steps:**
    1. **Modify EnemyPlane Constructor:**
      - The `EnemyPlane` constructor is updated to accept an image name and fire rate as parameters.

      ```java
      public EnemyPlane(double initialXPos, double initialYPos, String imageName, double fireRate) {
          super(imageName, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
          this.FIRE_RATE = fireRate / GameLoop.getInstance(null).get_TARGET_FPS();
      }
      ```

    <br>

    2. **Update LevelOne and LevelTwo to Use Varied Enemies:**
      - In `LevelOne`, the `EnemyPlane` is created with a specific image (`enemyplane1.png`) and fire rate.

      ```java
      ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, "enemyplane1.png", .2);
      ```

      - In `LevelTwo`, multiple enemy types are created using different images (`enemyplane2.png` and `enemyplane3.png`) and fire rates.

      ```java
      int imageIndex = (int) Math.round(Math.random());
      ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, ENEMY_PLANE_IMAGE_NAME[imageIndex], .42);
      ```

  <br>

  - **Future Improvements:**
    - Add more enemy types with unique behaviors (e.g., faster movement, stronger projectiles).
    - Introduce enemy variations that require different strategies to defeat.
    - Include enemy animations or special effects.

<br>

9. **Added Pause Button**
  - **Why is this feature needed?**
    - A pause button provides an alternative way for players to access the pause menu, making the game more user-friendly. It allows players to pause the game without relying solely on the ESC key, which may not be intuitive for all users.

  <br>

  - **How is it implemented?**
    - A pause button is added to the `LevelScene` class, which triggers the pause menu when clicked.

  <br>

  - **Implementation Steps:**
    1. **Add Pause Button to LevelScene:**
      - A `Button` object is created and added to the `LevelScene` layout.

      ```java
      private final Button pauseButton;

      public LevelScene(String backgroundImageName, double screenWidth, double screenHeight, int playerInitialHealth) {
          this.pauseButton = initPauseButton();
          // Other initialization code
      }
      ```

    <br>

    2. **Initialize Pause Button:**
      - The `initPauseButton` method creates the pause button, sets its position, and defines its action (showing the pause menu).

      ```java
      private Button initPauseButton() {
          Button pauseButton = createButton(60, 56, getClass().getResource("/com/example/demo/images/pause/pause_button.png").toExternalForm());

          pauseButton.setLayoutX((getScreenWidth() - 60) / 2);
          pauseButton.setLayoutY(25);
          pauseButton.setOnAction(e -> {
              if (Game.getInstance(null).getCurrentState() == State.RUNNING) {
                  showPauseMenu();
              }
          });

          return pauseButton;
      }
      ```

    <br>

    3. **Add Pause Button to Root Layout:**
      - The pause button is added to the root layout of the scene.

      ```java
      this.root.getChildren().addAll(userUnit, pauseButton);
      ```

  - **Future Improvements:**
    - Add hover effects or animations to the pause button.
    - Include a "Resume" button in the pause menu for better accessibility.
    - Allow players to customize the pause button's position or appearance.


<br>


10. **Comprehensive Audio System with Background Music and Sound Effects**
  - **Why is this feature needed?**
    - A comprehensive audio system has been implemented, including background music for different scenes (Menu, Level, Lose Scene, Win Scene) and sound effects for various actions (user fire, enemy fire, kill, damage, click, pause, transition, game over). Audio enhances the overall gaming experience by providing immersive background music and responsive sound effects for actions. It helps to create a more engaging and dynamic environment, making the game feel more alive and interactive.
    
  <br>

  - **Implementation Steps:**
    1. **Dependencies:**
     - The `pom.xml` file was updated to include the `javafx-media` dependency, which provides support for audio playback in JavaFX applications.
     ```xml
     <dependency>
         <groupId>org.openjfx</groupId>
         <artifactId>javafx-media</artifactId>
         <version>19.0.2</version>
     </dependency>
     ```

    <br>

    2. **Audio Classes:**
      - **`Audio` Class:**
        - A base class for all audio types, providing a `MediaPlayer` for playing audio files.
        ```java
        public class Audio {
            protected MediaPlayer audioPlayer;

            public Audio(String audioPath) {
                this.audioPlayer = new MediaPlayer(new Media(getClass().getResource(audioPath).toExternalForm()));
            }

            public void destroy() {
                audioPlayer = null;
            }
        }
        ```

      - **`BackgroundAudio` Class:**
        - Extends `Audio` to handle background music, which loops indefinitely and has a volume setting.
        ```java
        public class BackgroundAudio extends Audio {
            public BackgroundAudio(String audioPath) {
                super(audioPath);
                audioPlayer.setVolume(0.6); 
                audioPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
                playAudio();
            }

            public void playAudio() {
                audioPlayer.play();
            }

            public void pauseAudio() {
                audioPlayer.pause();
            }
        }
        ```

      - **`EffectAudio` Class:**
        - Extends `Audio` to handle sound effects, which play once and then dispose of the `MediaPlayer`.
        ```java
        public class EffectAudio extends Audio {
            public EffectAudio(String audioPath) {
                super(audioPath);
                audioPlayer.setVolume(0.7);
                audioPlayer.setOnEndOfMedia(audioPlayer::dispose);
                audioPlayer.play();
            }
        }
         ```

    <br>

    3. **Audio Factory:**
      - The `AudioFactory` class is responsible for creating different types of audio (background and effect) based on the provided type.
      ```java
      public class AudioFactory {
          public BackgroundAudio createBackgroundAudio(BackgroundAudioType backgroundAudioType) {
              switch (backgroundAudioType) {
                  case MENU:
                      return new BackgroundAudio("/com/example/demo/audio/background/menu.mp3");
                  default:
                      throw new IllegalArgumentException("Unknown audio type: " + backgroundAudioType);
              }
          }

          public void fireEffectAudio(EffectAudioType effectAudioType) {
              switch (effectAudioType) {
                  case USERFIRE:
                      new EffectAudio("/com/example/demo/audio/effect/user_fire.mp3");
                      break;
                  default:
                      throw new IllegalArgumentException("Unknown audio type: " + effectAudioType);
              }
          }
      }
      ```

    <br>

    4. **Audio Manager:**
      - The `AudioManager` class manages the background music and sound effects, providing methods to change the background music, play/pause it, and fire sound effects.
      ```java
      public class AudioManager {
          private static AudioManager instance;
          private final AudioFactory audioFactory;
          private BackgroundAudio currentBackgroundAudio;

          private AudioManager() {
              this.audioFactory = new AudioFactory();   
              currentBackgroundAudio = audioFactory.createBackgroundAudio(BackgroundAudioType.MENU);
          }

          public static AudioManager getInstance() {
              if (instance == null) {
                  instance = new AudioManager();
              }
              return instance;
          }
      }
      ```

    5. **Integration with Scenes:**
      - Each scene (Menu, Level, Lose Scene, Win Scene) initializes its background music when it is loaded.
      ```java
      public class MenuScene extends GameScene {
          // ...
          AudioManager.getInstance().changeBackgroundAudio(BackgroundAudioType.MENU);
      }

      public class LevelOne extends LevelScene {
          // ...
          AudioManager.getInstance().changeBackgroundAudio(BackgroundAudioType.LEVEL);
      }
      ```
      
    <br>

    6. **Sound Effects Integration:**
      - Sound effects are triggered for various actions, such as firing projectiles, collisions, and game events.
      ```java
      public class UserPlane extends FighterPlane {
          @Override
          public ActiveActorDestructible fireProjectile() {
              AudioManager.getInstance().fireEffectAudio(EffectAudioType.USERFIRE);
              return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
          }
      }

      public class CollisionManager {
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
      }
      ```

  <br>

  - **Future Improvements:**
    - **Volume Control:** Implement a volume control system to allow players to adjust the volume of background music and sound effects.
    - **Dynamic Audio Switching:** Add logic to dynamically switch background music based on game events (e.g., increasing intensity during boss fights).
    - **Audio Caching:** Cache frequently used sound effects to reduce loading times and improve performance.


<br>


11. **Implemented Sound Toggle Button**
  - **Why is this feature needed?**
		- A sound toggle button allows players to enable or disable sound effects and background music during gameplay. This feature is essential for players who prefer to play without sound or who may need to mute the game in certain environments.

  <br>

	- **How is it implemented?**
		- A sound toggle button is added to the game interface, allowing players to switch between sound on and sound off states. The button changes its appearance based on the current sound state and updates the `AudioManager` accordingly.

  <br>

	- **Implementation Steps:**
		1. **Add Sound Toggle Button to GameScene:**
			- The `GameScene` class is updated to include a sound toggle button. This button is initialized in the constructor and added to the root layout.

			```java
			protected ArrayList<Node> topNode;

			public GameScene(String backgroundImageName, double screenWidth, double screenHeight) {
					// Existing code
					Button tempButton = initSoundButton();
					this.root.getChildren().add(tempButton);
					this.topNode.add(tempButton);
			}
			```

  	<br>

		2. **Initialize Sound Toggle Button:**
			- The `initSoundButton` method creates the sound toggle button, sets its position, and defines its action (toggling sound on/off).

			```java
			protected Button initSoundButton() {
					Button soundButton;
					if (AudioManager.getInstance().getHasSound())
							soundButton = createButton(58, 57, getClass().getResource("/com/example/demo/images/sound_yes.png").toExternalForm());
					else
							soundButton = createButton(58, 57, getClass().getResource("/com/example/demo/images/sound_no.png").toExternalForm());

					// ......

					return soundButton;
			}
			```

  	<br>

		3. **Update AudioManager to Handle Sound State:**
			- The `AudioManager` class is updated to include a `hasSound` boolean flag, which determines whether sound effects and background music should be played.

			```java
			public class AudioManager {
					private boolean hasSound;

					// existing code	
			}
			```

  <br>

	- **Future Improvements:**
		- Add a visual indicator (e.g., tooltip) to explain the button's function when hovered over.
		- Allow players to customize the sound volume separately for background music and sound effects.
		- Save the sound state in a configuration file so it persists across game sessions.


<br>


12. **Top Node Management for UI Elements**
	- **Why is this feature needed?**
		- Ensuring that UI elements like the pause button, heart display, and sound toggle button remain on top of other game elements (e.g., enemies, projectiles) is crucial for a smooth user experience. This prevents these elements from being obscured by game objects.
		
	<br>

	- **How is it implemented?**
		- A `topNode` list is introduced in the `GameScene` class to manage UI elements that should always remain on top. A `ListChangeListener` is used to ensure that these elements are repositioned to the top of the scene's node hierarchy whenever new elements are added.

	<br>

	- **Implementation Steps:**
		1. **Add Top Node List to GameScene:**
			- The `topNode` list is initialized in the `GameScene` constructor and populated with UI elements.

			```java
			protected ArrayList<Node> topNode;

			public GameScene(String backgroundImageName, double screenWidth, double screenHeight) {
					// Existing code
					this.topNode = new ArrayList<Node>();

					Button tempButton = initSoundButton();
					this.root.getChildren().add(tempButton);
					this.topNode.add(tempButton);
			}
			```

		<br>

		2. **Add ListChangeListener to Manage Top Nodes:**
			- A `ListChangeListener` is added to the root's children to ensure that top nodes are always repositioned to the top of the hierarchy.

			```java
			this.root.getChildren().addListener((ListChangeListener<Node>) change -> {
					while (change.next()) {
							ObservableList<Node> tempChildren = this.root.getChildren();
							if (change.wasAdded()) {
									if (!(change.getAddedSubList().stream().anyMatch(topNode::contains))) {
											for (Node node : topNode) {
													if (tempChildren.contains(node)) {
															Platform.runLater(() -> {
																	this.root.getChildren().remove(node);
																	this.root.getChildren().add(node);
															});
													}
											}
									}
							}
					}
			});
			```

		<br>

		3. **Add UI Elements to Top Node List:**
			- UI elements like the pause button, heart display, and pause menu are added to the `topNode` list in the `LevelScene` class.

			```java
			this.root.getChildren().addAll(userUnit, pauseButton);
			topNode.add(pauseButton);
			topNode.add(levelView.getHeartDisplay());
			topNode.add(pauseMenu);
			```

	<br>

	- **Future Improvements:**
		- Extend the `topNode` management to include other UI elements (e.g., score display, boss health bar).
		- Allow developers to dynamically add or remove top nodes without modifying the core logic.
		- Add unit tests to ensure that top nodes are always positioned correctly.


<br>


13. **Added Boss Health Bar**
  - **Why is this feature needed?**
    - A health bar has been introduced for the boss in `LevelThree`, providing players with real-time feedback on the boss's remaining health. A health bar for the boss is essential for gameplay clarity and player engagement. It allows players to gauge the boss's remaining health, making the battle more strategic and engaging.

  <br>

  - **Implementation Steps:**
    1. **HealthBarDisplay Class:**
      - A new class `HealthBarDisplay` was created to manage the health bar's appearance and functionality.
      ```java
      public class HealthBarDisplay {
          private static final double BAR_WIDTH = 500;
          private static final double BAR_HEIGHT = 25;
          private final ProgressBar healthBar;
          private int maxHealth;

          public HealthBarDisplay(int maxHealth) {
              this.maxHealth = maxHealth;
              healthBar = new ProgressBar(1.0);
              healthBar.setPrefWidth(BAR_WIDTH);
              healthBar.setPrefHeight(BAR_HEIGHT);
              healthBar.setLayoutX((1366 - 500) / 2);
              healthBar.setLayoutY(25);
              healthBar.setStyle("-fx-accent: red;"); 
          }

          public void updateHealth(int currentHealth) {
              double healthRatio = (double) currentHealth / maxHealth;
              healthBar.setProgress(healthRatio);
              if (healthRatio > 0.3) {
                  healthBar.setStyle("-fx-accent: red;");
              } else {
                  healthBar.setStyle("-fx-accent: yellow;");
              } 
          }

          public ProgressBar getHealthBar() {
              return healthBar;
          }
      }
      ```

    <br>

    2. **Integration with Boss Class:**
     - The `Boss` class was modified to include a reference to `LevelView` and to update the health bar whenever the boss's health changes.
     ```java
     public class Boss extends FighterPlane {
         private final LevelView levelView;

         public Boss(LevelView levelView) {
             super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
             this.levelView = levelView;
             levelView.showHealthBarDisplay(HEALTH);
         }

         @Override
         public void update() {
             updatePosition();
             updateShield();
             updateShieldView();
             levelView.updateHealthBarDisplay(getHealth());
         }
     }
     ```

    <br>

    3. **LevelView Class:**
      - The `LevelView` class was updated to manage the health bar display.
      ```java
      public class LevelView {
          private HealthBarDisplay healthBarDisplay;

          public void showHealthBarDisplay(int maxHealth) {
              healthBarDisplay = new HealthBarDisplay(maxHealth);
              root.getChildren().add(healthBarDisplay.getHealthBar());
          }

          public void updateHealthBarDisplay(int health) {
              healthBarDisplay.updateHealth(health);
          }
      }
      ```

  <br>

  - **Future Improvements:**
    - **Health Bar Animation:** Add animations to the health bar to make it more visually appealing when health changes.
    - **Health Bar Positioning:** Allow customization of the health bar's position and size based on the game's layout.


<br><br><br>


## Implemented but Not Working Properly

1. **Pause Menu Functionality**
  - **Why is this feature needed?**
  - The game now includes a fully functional pause menu that allows the player to pause the game, resume it, or exit to the main menu. A pause menu is a standard feature in most games, providing players with the ability to take a break, review the game state, or exit to the main menu without losing progress. It enhances the user experience by offering flexibility and control.

  <br>

  - **Implementation Steps:**
    1. **Game State Management:**
      - The `GameState` class was updated to manage the game's state, including the addition of a `PAUSED` state.
    
    <br>

    2. **Pause Menu Initialization:**
      - The `LevelScene` class was updated to include a `VBox` for the pause menu, which is initialized in the `initPauseMenu()` method.
    
    <br>

    3. **Showing and Hiding the Pause Menu:**
      - The `showPauseMenu()` method is called when the `ESCAPE` key is pressed, setting the game state to `PAUSED` and adding the pause menu to the root group.
      ```java
      private void showPauseMenu() {
          Game.getInstance(null).setStatePauseGame();
          root.getChildren().add(pauseMenu);
      }
      ```

      - The `resumeGame()` method is called when the "Continue" button is pressed, removing the pause menu from the root group and setting the game state to `RUNNING`.
      ```java
      private void resumeGame() {
          Platform.runLater(() -> {
              root.getChildren().removeLast();
              root.requestLayout();
          });
          Game.getInstance(null).setStateResumeGame();
      }
      ```

    <br>
    
    4. **Key Event Handling:**
      - The `setOnKeyPressed` method in `LevelScene` was updated to handle the `ESCAPE`key, toggling the pause menu.

    <br>

    5. **Button Creation:**
      - The `createButton()` method was added to create buttons with custom styles andactions.
    
  <br>

  - **Issues Encountered**
    - Despite the implementation, the pause menu occasionally remains on the screen after resuming the game. This issue arises sporadically and is not consistently reproducible.

  <br>

  - **Steps Taken to Address the Issue**
    1. **Initial Debugging**:
      - The first step was to ensure that the `resumeGame()` method was correctly removing the pause menu from the scene. This was done by adding debug logs and verifying that the `root.getChildren().removeLast()` method was being called.

    2. **Thread Safety**:
      - Since JavaFX UI updates must be performed on the JavaFX Application Thread, `Platform.runLater()` was used in the `resumeGame()` method to ensure that the pause menu removal happens on the correct thread.

    3. **State Consistency**:
      - The game state was checked to ensure that it transitions correctly from `PAUSED` to `RUNNING`. However, the issue persisted, indicating that the problem might not be related to state management.

    4. **UI Update Timing**:
      - It was suspected that the UI update might be happening too quickly or too slowly, causing the pause menu to remain visible. However, adjusting the timing did not resolve the issue.

    5. **Scene Graph Inspection**:
      - The scene graph was inspected to ensure that the pause menu was being added and removed correctly. This involved logging the contents of `root.getChildren()` before and after the pause menu was removed. However, it has shown that the `pauseMenu` node has been removed but is still displayed on the screen.

  <br>

  - **Potential Causes of the Issue**
    1. **Race Conditions**:
      - There might be a race condition where the pause menu is not removed quickly enough, or the game state transitions too slowly, causing the pause menu to remain visible.

    2. **UI Update Lag**:
      - The JavaFX UI might be experiencing lag or delays in updating the scene graph, causing the pause menu to remain visible even after the `resumeGame()` method is called.

  <br>

  - **Conclusion**
    - Unfortunately, despite using all methods, it is still an issue and cannot be solved with no knowed resolving method. However, the good news is that this issue only happens when the user spam pauses the game, in other words, if the user uses pause menu infrequently, this issue will normally not show up.


<br>


2. **Exit Button Functionality**
  - **Why is this feature needed?**
  - The exit button was implemented in the `LevelScene` class to allow users to exit the game gracefully. The goal was to play a sound effect (`EffectAudioType.CLICK`) before closing the application.

  <br>

  - **Exit Button Action**:
    - The `setOnAction` method is used to define the behavior of the exit button. When the button is clicked, the following actions are performed:
    - The `AudioManager.getInstance().fireEffectAudio(EffectAudioType.CLICK)` method is called to play the sound effect.
    - The `System.exit(0)` method is called to terminate the application.

  <br>

  - **Issues Encountered**
    - The primary issue is that the sound effect (`EffectAudioType.CLICK`) does not play before the application closes. Despite trying various approaches, such as asynchronous execution and delays, the sound effect is not consistently heard.

  <br>

  - **Steps Taken to Address the Issue**
    1. **Initial Debugging**:
      - The first step was to verify that the `fireEffectAudio()` method is being called when the exit button is clicked. This was done by adding debug logs and confirming that the method is invoked.

    2. **Asynchronous Execution**:
      - To ensure that the sound effect plays before the application closes, the exit action was wrapped in an asynchronous task using `Platform.runLater()` or `CompletableFuture`. However, this did not resolve the issue, as the application still closed before the sound effect could play.

    3. **Delayed Execution**:
      - A delay was introduced using `Thread.sleep()` or `Task.delay()` to give the sound effect time to play before the application closed. While this sometimes worked, it was not a reliable solution and introduced unwanted delays.

    4. **AudioManager Review**:
      - The `AudioManager` class was reviewed to ensure that the `fireEffectAudio()` method correctly plays the sound effect. No issues were found with the audio playback logic.

    5. **System.exit() Behavior**:
      - The behavior of `System.exit(0)` was investigated to understand why it terminates the application so quickly that the sound effect does not have time to play. It was determined that `System.exit(0)` forcefully terminates the JVM, which may interrupt any ongoing processes, including audio playback.

  <br>

  - **Potential Causes of the Issue**
    1. **Immediate Application Termination**:
      - The `System.exit(0)` method terminates the JVM immediately, without waiting for any ongoing processes (such as audio playback) to complete. This causes the sound effect to be cut off before it can finish playing.

    2. **Thread Interruption**:
      - The asynchronous execution of the exit action may be interrupted by the JVM shutdown, preventing the sound effect from playing.

    3. **JavaFX Audio Playback Timing**:
      - JavaFX's audio playback system may not be able to handle the rapid shutdown of the application, causing the sound effect to be skipped.

  <br>

  - **Conclusion**
    - Unfortunately due to the unwanted behaviours of delays, the issue cannot be fixed.

<br><br><br>


## Features Not Implemented

1. **Kill Count Indicator**  
  - **Description:** The kill count indicator was intended to provide real-time feedback on the player's performance by displaying the number of enemies defeated. 
  - **Reason:** Implementing this feature required a robust system for tracking and updating the kill count dynamically during gameplay. Given the complexity of needing to refactor certain classes like LevelState and Kill manager in order to synchronize this data with the game's event-driven architecture and ensuring smooth performance, it was not feasible to include this feature within the project timeline.

<br>

2. **Difficulty Selector**  
  - **Description:** A difficulty selector would have allowed players to customize their experience by adjusting the game's challenge level. 
  - **Reason:** While the logic for varying difficulty levels (e.g., enemy speed, player health, or resource availability) was conceptually straightforward, integrating this feature required significant changes to the game's core mechanics and balancing. Due to the time-intensive nature of testing and refining these adjustments across different difficulty settings, this feature was deferred. However, in place of it, we do have multiple levels in the game which could also act as a difference in difficulty of gameplay.

<br>

3. **Separate Control for Muting Background Music and Effect Audio**  
  - **Description:** Providing players with the ability to independently mute background music and sound effects was a planned enhancement for personalization.  
  - **Reason:** The additional button mechanics needed to implement the selection would require a rehaul of the game scenes and introduced additional complexity and potential performance overhead. As a result, this feature was not included in the final version.

<br>

4. **In-Game Volume Control**  
  - **Description:** An in-game volume control was intended to allow players to adjust the game's audio settings dynamically during gameplay. 
  - **Reason:** While this feature is relatively simple in concept, integrating it required modifying the game's UI framework to accommodate a volume slider and ensuring that changes to audio levels were applied seamlessly across all audio streams. Given the time constraints and the need to prioritize core gameplay mechanics, this feature was not implemented.

<br>

5. **Leaderboard with Persistence Storage**  
  - **Description:** A leaderboard system with persistent storage was planned to allow players to track their high scores and compare their performance with others. 
  - **Reason:** However, implementing this feature required integrating a robust data storage solution, such as a database or file-based persistence, and ensuring secure and efficient data handling. Given the complexity of setting up and managing persistent storage within the JavaFX environment, this feature was not included in the final version.


<br><br><br>


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


<br><br><br>


## Unexpected Problems

1. **JavaFX Version Compatibility Issue**
  - **Problem:** Initially, the game was unable to run due to compatibility issues with the JavaFX version specified in the Maven `pom.xml`. The default version caused runtime errors, preventing the application from launching.

  - **Solution:** After diagnosing the issue, I identified that the JavaFX version needed to be updated to ensure compatibility with the JDK and other dependencies. I upgraded the JavaFX version to `19.0.2` in the Maven configuration. This involved explicitly specifying the correct version in the `pom.xml` and ensuring that the required JavaFX modules were included. The updated configuration resolved the runtime errors, allowing the game to run smoothly.

  - **Technical Details:**
    - Updated the JavaFX version in the `pom.xml` to `19.0.2`.
    - Ensured that the correct JavaFX modules (`javafx.controls`, `javafx.fxml`, etc.) were included as dependencies.
    - Verified compatibility with the JDK version being used.

<br>

2. **JUnit Testing JavaFX Core Functionality**
  - **Problem:** Standard JUnit tests were unable to effectively test JavaFX-specific functionality, such as UI components and event handling, due to the limitations of JUnit in interacting with the JavaFX Application Thread.

  - **Solution:** To address this, I integrated **TestFX**, a specialized testing framework designed for JavaFX applications. TestFX provides the `ApplicationTest` class, which allows for robust unit testing of JavaFX components by running tests on the JavaFX Application Thread. This enabled me to write comprehensive tests for UI elements, event handlers, and other core JavaFX functionality.

  - **Technical Details:**
    - Added the TestFX dependency to the `pom.xml`.
    - Extended the `ApplicationTest` class in my test cases to ensure tests ran on the JavaFX Application Thread.
    - Implemented test scenarios for UI components, event handling, and other JavaFX-specific features.

<br>

3. **Carryover Configuration in JUnit Tests**
  - **Problem:** Running multiple JUnit test classes on a single JVM instance caused carryover configurations, particularly with TestFX, leading to inconsistent test results and potential conflicts between tests.

  - **Solution:** To mitigate this issue, I disabled the reuse of JVM instances for JUnit tests. By configuring the `maven-surefire-plugin` to fork multiple JVM instances, each test class ran in isolation, eliminating the risk of carryover configurations. Additionally, I increased the number of forked JVM instances to `20` to parallelize the testing process and improve overall build speed.

  - **Technical Details:**
    - Configured the `maven-surefire-plugin` in the `pom.xml` to disable JVM reuse (`reuseForks=false`).
    - Set the `forkCount` to `20` to parallelize test execution and speed up the testing process.
    - Ensured that each test class ran in a separate JVM instance, preventing configuration conflicts.

<br>

4. **Maven Javadoc Plugin Not Generating Documentation**
  - **Problem:** The Maven Javadoc plugin failed to generate Javadoc documentation as expected, resulting in incomplete or missing documentation for the project.

  - **Solution:** After investigating the issue, I identified that the Javadoc plugin required proper configuration to function correctly. I added the `maven-javadoc-plugin` to the `pom.xml` and configured it with the necessary settings, such as specifying the output directory and enabling additional Javadoc options. This ensured that the Javadoc documentation was generated accurately and consistently.

  - **Technical Details:**
    - Added the `maven-javadoc-plugin` to the `pom.xml`.
    - Configured the plugin with appropriate settings, including the output directory and additional Javadoc options.
    - Verified that the Javadoc documentation was generated correctly by running `mvn javadoc:javadoc`.

<br>

5. **Development Environment Setup with Visual Studio Code**
  - **Problem:** While Visual Studio Code (VS Code) is a powerful editor, its setup for JavaFX development was not as straightforward as IntelliJ IDEA. The lack of built-in Maven integration and JavaFX support required additional configuration to achieve a comparable quality of life.

  - **Solution:** To overcome this, I carefully configured the `pom.xml` to ensure proper Maven integration and JavaFX support in VS Code. I also installed the necessary VS Code extensions, such as the **Java Extension Pack** and **Maven for Java**, to enhance development efficiency. While the setup required more manual effort compared to IntelliJ, it provided a functional and productive development environment.

  - **Technical Details:**
    - Configured the `pom.xml` to include all necessary dependencies and plugins for JavaFX and Maven.
    - Installed and configured VS Code extensions for Java and Maven development.
    - Ensured that the project structure and configurations were compatible with VS Code.


<br><br><br>


## Project Structure

```
CW2024/
├── .idea/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │    ├─── com/
│   │   │    │   └── example/
│   │   │    │       └── demo/
│   │   │    │           ├── actors/
│   │   │    │           │   ├── enemy/
│   │   │    │           │   │   ├── Boss.java
│   │   │    │           │   │   └── EnemyPlane.java
│   │   │    │           │   ├── projectile/
│   │   │    │           │   │   ├── BossProjectile.java
│   │   │    │           │   │   ├── EnemyProjectile.java
│   │   │    │           │   │   └── UserProjectile.java
│   │   │    │           │   ├── props/
│   │   │    │           │   │   ├── HealthBarDisplay.java
│   │   │    │           │   │   ├── HeartDisplay.java
│   │   │    │           │   │   └── ShieldImage.java
│   │   │    │           │   ├── user/
│   │   │    │           │   │   └── UserPlane.java
│   │   │    │           │   ├── ActiveActor.java
│   │   │    │           │   ├── ActiveActorDestructible.java
│   │   │    │           │   ├── Destructible.java
│   │   │    │           │   ├── FighterPlane.java
│   │   │    │           │   └── Projectile.java
│   │   │    │           |
│   │   │    │           ├── audio/
│   │   │    │           │   ├── background/
│   │   │    │           │   │   └── BackgroundAudio.java
│   │   │    │           │   ├── effect/
│   │   │    │           │   │   └── EffectAudio.java
│   │   │    │           │   ├── services/
│   │   │    │           │   │   ├── AudioFactory.java
│   │   │    │           │   │   └── AudioManager.java
│   │   │    │           │   └── Audio.java
│   │   │    │           |
│   │   │    │           ├── core/
│   │   │    │           │   ├── Game.java
│   │   │    │           │   ├── GameLoop.java
│   │   │    │           │   └── GameState.java
│   │   │    │           |
│   │   │    │           ├── scenes/
│   │   │    │           │   ├── levels/
│   │   │    │           │   │   ├── services/
│   │   │    │           │   │   │   ├── managers/
│   │   │    │           │   │   │   │   ├── ActorManager.java
│   │   │    │           │   │   │   │   ├── CollisionManager.java
│   │   │    │           │   │   │   │   ├── EnemyManager.java
│   │   │    │           │   │   │   │   ├── KillManager.java
│   │   │    │           │   │   |   |   └── ProjectileManger.java
│   │   │    │           │   │   │   ├── LevelState.java
│   │   │    │           │   │   |   └── LevelView.java
│   │   │    │           │   │   ├── LevelOne.java
│   │   │    │           │   │   ├── LevelScene.java
│   │   │    │           │   │   ├── LevelThree.java
│   │   │    │           │   │   └── LevelTwo.java
│   │   │    │           │   │   
│   │   │    │           │   ├── services/
│   │   │    │           │   │   ├── GameSceneFactory.java
│   │   │    │           │   │   └── GameSceneManager.java
│   │   │    │           │   ├── GameScene.java
│   │   │    │           │   ├── LoseScene.java
│   │   │    │           │   ├── MenuScene.java
│   │   │    │           │   └── WinScene.java
│   │   │    │           │   
│   │   │    │           ├── utils/
│   │   │    │           |   ├── EnumUtil.java
│   │   │    │           |   └── LoggerUtil.java
│   │   │    │           |   
│   │   │    │           └── Main.java
│   │   │    └── module-info.java
│   │   └── resources/
├── target/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml 
└── README.md
```