# Mancala Game API

## Project Overview
 - As part of my tech assignment, i've implemented the Mancala Game as backend REST API's to integrate with front-end to play the game with two players.

Architecture
--
### Gaming information
 - Players can initialize new board with a user defined no of stones or default stones (6) in each pit.
 - Each player has a pit house pool to keep their own pit stones.
 - Players will start the game by picking the pit stones from any one of their own pits and move towards the right/anti-clock wise and so on until one of the player wins or end of the game.

###Below Swagger Documentation can help understand about the Interface
 -http://localhost:8080/swagger-ui.html

###To access the application by using the below endpoints from either 'postman' or 'restclient'
 - http://localhost:8080/mancala/createGame (POST)
 - http://localhost:8080/mancala/createGame/{initPitStones} (POST)
 - http://localhost:8080/mancala/getById/{gameId} (GET)
 - http://localhost:8080/mancala/sow/{gameId}/pits/{pitIndex} (PUT)

Technical Info
--
Softwares:
 - Spring Boot 2.3.2 (JDK 8)
 - Maven 3.6.1
 - MongoDB 4.0.5
 - Swagger 2.9.2
 - Docker

Deployment
--
 - Clone the repository and checkout develop branch 
 - mvn clean install (enable -DskipTests if mongodb connection issues are encountered )

automated steps with Docker
--
This web application leverages the advantage of docker deployment.<br/>
 - Docker image can be built using 'docker build -t mancala-app .'<br/>
 - Run mongodb with 'docker run -d --rm --name mongodb -p27017:27017 mongo'<br/>
    (use 'docker stop <containerID>' to stop if mongodb is already running.)
 - To run mancala app itself 'docker run --rm --link mongodb:mongodb -p8080:8080 mancala-app' <br/>
 --- here --link mongodb:mongodb is used to link the mongodb host name dynamically.<br/>
 --- -p8080:8080 is used to expose the application port and map dynamically.
 
 manual steps to run the app locally
 --
 - Please ensure docker is installed locally
 - mongodb is installed locally and will be started as a background process
   using command 'docker-compose up -d'
 - Enable localhost as mongo host in application.yml (restart the app)
 - Run MancalaApplication.java to start the app locally
 - Execute MancalaE2ETest.java to see entire flow with a winner