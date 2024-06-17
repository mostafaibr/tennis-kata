package org.mostafaibr.tennis.kata;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    /**
     * It takes input from user when running the application
     * Play a game/match and keep track of the score
     * @param args
     */
    public static void main(String[] args) {

        // The application is running with java command, where input could be asked to user in the console
        if(args.length == 0){
            Scanner scanner = new Scanner(System.in);

            String type;
            do{
                System.out.println("Do you want to play a single game or a match? (g/m): ");
                type = scanner.nextLine();
            } while(!type.equals("g") && !type.equals("m"));

            switch (type){
                // A single game with only on score as input
                case "g":
                    System.out.print("Enter a sequence of characters representing players A/B scoring during the game (Example: AABAABA): ");
                    String score = scanner.nextLine();
                    scanner.close();

                    System.out.printf("Playing a single game with score: %s%n", score);
                    TennisGame gameScan = new TennisGame();
                    gameScan.play(score);
                    break;
                // A match of a sequence of games
                case "m":
                    System.out.print("Enter the number of games to limit (Example: 3 for a best of three, 5 for a best of five, etc..): ");
                    int limit = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter the scoring of the whole match split by ',' (Example: AAAA,BAAAA,BABAAA,AAAA,AABAA,AAAA): ");
                    String[] scores = scanner.nextLine().split(",");
                    scanner.close();

                    System.out.printf("Playing a match of %d games-limited with scores: %s%n", limit, Arrays.toString(scores));
                    TennisMatch tennisMatch = new TennisMatch(limit);
                    tennisMatch.displayScore(scores);
                    break;
            }

        }
        // The application is running using docker without setting a value for the env variable `score` neither `limit` so the default values defined in docker file is still here
        else if(!isDefined(args[0]) && !isDefined(args[1])){
            System.out.println("You should pass either `score` for a single game, or `limit` and `score` for a game-limited match. (use -e when running the docker container)");
        }
        // The application is running using docker with a given value of both `limit` and `score` to play a match of limited games
        else if(isDefined(args[0]) && isDefined(args[1])){
            String[] scores = args[0].split(",");
            int limit = Integer.parseInt(args[1]);

            System.out.printf("Playing a match of %d games-limited with scores: %s%n", limit, Arrays.toString(scores));
            TennisMatch tennisMatch = new TennisMatch(limit);
            tennisMatch.displayScore(scores);
        }
        // The application is running using docker with a given value of only `score` to play a single game
        else {
            String score = args[0];

            System.out.printf("Playing one game with score: %s%n", score);
            TennisGame tennisGame = new TennisGame();
            tennisGame.play(score);
        }
    }

    /**
     *
     * Helper method to check if the default value of argument defined in the docker image is not changed
     *
     * @param arg the argument passed from docker image to the java application
     * @return true if the default value was changed
     */
    private static boolean isDefined(String arg){
        return !arg.equals("EMPTY");
    }
}
