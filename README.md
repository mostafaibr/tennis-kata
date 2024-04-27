# Tennis
## Description
This Kata goal is to implement a simple tennis score computer.

The scoring system consist in one game, divided by points :
Each player starts a game with 0 point.
- If the player wins the 1st ball, he will have 15 points. 2nd balls won : 30 points. 3rd ball won : 40points.
- If a player have 40 points and wins the ball, he wins the game, however there are special rules.
  - If both players have 40 points the players are “deuce”.
  - If the game is in deuce, the winner of the ball will have advantage
  - If the player with advantage wins the ball he wins the game
  - If the player without advantage wins the ball they are back at “deuce”.

You can found more details about the rules here : ( http://en.wikipedia.org/wiki/Tennis#Scoring )

Here we want you to develop a java method that will take a String as input containing the character ‘A’ or ‘B’. The character ‘A’ corresponding to “player A won the ball”, and ‘B’ corresponding to “player B won the ball”. The java method should print the score after each won ball (for example : “Player A : 15 / Player B : 30”) and print the winner of the game.

For example the following input “ABABAA” should print :

“Player A : 15 / Player B : 0”

“Player A : 15 / Player B : 15”

“Player A : 30 / Player B : 15”

“Player A : 30 / Player B : 30”

“Player A : 40 / Player B : 30”

“Player A wins the game

### Match/parties

In addition to a single game play, players A and B could play a match of several games with a limit of number of games already determined. 

**For example:** "Best of five" or "First to three wins": it means that the maximum number of match to be played is 5, if any player reaches 3 game wins, he wins the match.
Same for any other limit (Best of 3, Best of 7, etc.).

## Prerequisites
- Apache Maven
- Java 17

or 

- Docker

## Install
`mvn package`

## Run
### Java
`java -jar target/tennis.jar`

### Docker
If you want to run without need to install maven and Java 17, you could use docker.

When running inside a docker container, there's no interactive console to provide input. The inputs should be passed as environment variables.
- Build the docker image: `docker build -t tennis .`

- Run the container: 
  - In case of playing a single game, 
    - you should pass the `score` as an environment variable. 
    
    Example: `docker run -e score=AAAA tennis`
  - In case of playing a match of multi games, you should pass:
    - `limit`: the games limit to set, which is the max number of games to be played. Example: `3` 
    - `score`: the scores of the whole match split by a ',' for each game. Example: `AAAA,AAAA,AAAA,AAAAA,AAAA`
  
    Example: `docker run -e limit=3 -e score=AAAA,BBBB,AAAA tennis`

## Run tests
`mvn test`

## License
See the [LICENSE](/LICENSE) file.