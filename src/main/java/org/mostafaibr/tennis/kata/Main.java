package org.mostafaibr.tennis.kata;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    /**
     * It takes input from user when running the application
     * Play a game and keep track of the score
     * @param args
     */
    public static void main(String[] args) {
        String score = "";
        // The application is running with java command, where input could be asked to user in the console
        if(args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a sequence of characters representing players A/B scoring during the game (Example: AABAABA): ");
            score = scanner.nextLine();
            scanner.close();
        }
        // The application is running using docker without setting a value for the env variable `score` so the default defined in docker file is still here
        else if(args[0].equals("EMPTY")){
            System.out.println("You should pass 'score' to play a game. (use -e when running the docker image)");
        }
        // The application is running using docker with a given value of `score`
        else {
            score = args[0];
        }

        System.out.println(String.format("Playing a game with score: %s", score));
        TennisGame gameScan = new TennisGame();
        gameScan.play(score);
    }
}
