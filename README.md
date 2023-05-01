# Boids the Game README
![image](https://user-images.githubusercontent.com/89105607/235391409-f2abe552-3d9e-42b0-bde5-02385bc96a67.png)

## Table of Contents
- [Boids the Game README](#boids-the-game-readme)
  - [Table of Contents](#table-of-contents)
  - [Description of Boids the Game](#description-of-boids-the-game)
  - [Description of the Boids Algorithm](#description-of-the-boids-algorithm)
  - [Getting Started](#getting-started)
  - [Gameplay](#gameplay)
    - [Poid:](#poid)
    - [Game Settings:](#game-settings)
    - [Game Screens:](#game-screens)
  - [Testing](#testing)
  - [Acknowledgements](#acknowledgements)
  - [License](#license)

## Description of Boids the Game
Boids the Game is a simulation-based game that is inspired by Craig Reynolds' Boids algorithm, which models the flocking behavior of birds or fish. In this game, players can choose to control a boid known as a Hoid (herd-like boid) and survive as long as possible, or a Poid (predator-like boid) and hunt down as many Hoids as possible. The game is built using Java, JavaFX, and Maven, with a total of four screens: Main Menu, Settings, Scoreboard, and Game Screen.

## Description of the Boids Algorithm
Boids the Game is based on the Boids algorithm, which was developed by Craig Reynolds in 1986 as a model for simulating flocking behavior of birds or fish. The algorithm is simple, yet effective in creating realistic swarm-like movement patterns.

The Boids algorithm uses three basic rules to govern the behavior of each individual boid:

1. **Separation**: Boids try to maintain a certain distance from other nearby boids to avoid collisions.
2. **Alignment**: Boids attempt to align their direction and speed with the average direction and speed of nearby boids.
3. **Cohesion**: Boids move towards the average position of nearby boids, effectively staying together as a group.

## Getting Started
To get started with Boids the Game, follow these steps:

1. Ensure you have Java and JavaFX installed on your system. If not, download and install them from the official websites.
2. Clone or download the repository to your local machine.
3. Navigate to the project's root folder and run `mvn clean install` to build the project using Maven.
4. Once the build is successful, navigate to the `target` folder and run the game using `java -jar BoidsTheGame-<version>.jar`.

## Gameplay
In Boids the Game, you can play as a Hoid or a Poid (predator boid). 

The Boids algorithm simulates flocking behavior by trying to achieve local flock center, aligning velocity with neighboring boids, and maintaining a minimum distance from other boids. Hoids follow the Boids algorithm, while Poids target the nearest Hoid or PlayerBoid when in Hoid game mode. The PlayerBoid is the character that the player controls, and it follows the cursor in the game.


### Poid:
As a Poid, your goal is to hunt down and capture as many Boids as possible. The player-controlled Poid will also follow the cursor's last known position.

### Game Settings:
You can customize your gameplay experience through the settings screen. Here, you can adjust the number of Boids, the ratio of Hoids vs Poids, and enable or disable the map's wraparound feature.

### Game Screens:
The game features four main screens:
1. Main Menu: Start a new game or access the settings and scoreboard.
![image](https://user-images.githubusercontent.com/89105607/235390449-6b0e963d-d793-49e2-907b-90822e660e47.png)

2. Settings: Customize the gameplay experience.
![image](https://user-images.githubusercontent.com/89105607/235390259-558817ad-ad04-4d06-bd00-758039453efc.png)

3. Scoreboard: View the highest scores achieved.
![image](https://user-images.githubusercontent.com/89105607/235390531-93a6e6a9-b040-41f5-9519-ada6c42e6b04.png)

4. Game Screen: Play the game.
![image](https://user-images.githubusercontent.com/89105607/235391383-25de986f-146f-473e-9f04-1c3098971fd4.png)


## Testing
To run the test suite for Boids the Game, follow these steps:

1. Navigate to the project's root folder.
2. Run `mvn test` to execute the test suite.

## Acknowledgements
We would like to thank Craig Reynolds for creating the Boids algorithm, which inspired the development of this game. Additionally, we appreciate the support of the Java, JavaFX, and Maven communities for their respective technologies.

## License
Boids the Game is released under the [MIT License](https://opensource.org/licenses/MIT). Please refer to the `LICENSE` file in the project's root folder for the full text.
