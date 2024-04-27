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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sequence of characters representing players A/B scoring during the game (Example: AABAABA): ");
        String score = scanner.nextLine();
        scanner.close();

        System.out.println(String.format("Playing a game with score: %s", score));
        TennisGame gameScan = new TennisGame();
        gameScan.play(score);
    }
}
