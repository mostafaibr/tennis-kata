package org.mostafaibr.tennis.kata;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public class TennisGame {
    private final Player playerA;
    private final Player playerB;

    static final char NO_WINNER = ' ';
    static final char PLAYER_A = 'A';
    static final char PLAYER_B = 'B';

    /**
     * A constructor that initialize the tennis game with two players
     */
    public TennisGame() {
        playerA = new Player();
        playerB = new Player();
    }

    /**
     *
     * A function to update score by recording points and return winner when the game ends
     *
     * @param player the player that scores a point
     * @return winner of the game (empty character when game still in progress)
     * @throws IllegalArgumentException when an illegal player scores a point
     *
     * @see https://en.wikipedia.org/wiki/Tennis#Scoring
     */
    char recordPoint(char player) {
        Player scoringPlayer = player == PLAYER_A ? playerA : playerB;
        Player otherPlayer = player == PLAYER_A ? playerB : playerA;

        if(scoringPlayer.hasWinningScore() && otherPlayer.hasWinningScore()){
            if(scoringPlayer.hasAdvantage()){
                return player;
            } else if(otherPlayer.hasAdvantage()) {
                otherPlayer.setAdvantage(false);
            } else {
                scoringPlayer.setAdvantage(true);
            }
        } else if (scoringPlayer.hasWinningScore()) {
            return player;
        } else {
            scoringPlayer.incrementScore();
        }
        return NO_WINNER;
    }


    /**
     *
     * A function to translate the scores to display
     *
     * @param winner the winner of the game when game ends, empty character means game is still in progress
     * @return a string to be displayed on the scoring board
     */
    String translateScore(char winner) {
        if (winner == NO_WINNER) {
            return "Player A: " + (playerA.hasAdvantage() ? "adv" : playerA.getScore()) + " / Player B: " + (playerB.hasAdvantage() ? "adv" : playerB.getScore());
        }

        return "Player " + winner + " wins the game";

    }

    /**
     *
     * A function to play and keep track of the score during a tennis game by printing it.
     * It exists either when the game ends, or when the players stop the game and no more score points
     * Any score point after the end of the game is ignored and considered as training (not taken into consideration on the scoring board)
     *
     * @param input the whole game scoring points, each character represents the player which scores successively
     */
    public void play(String input){
        Optional<Character> illegalPlayer = input.chars()
                .mapToObj(player -> (char) player)
                .filter(player -> player != PLAYER_A && player != PLAYER_B)
                .findFirst();
        if(illegalPlayer.isPresent()){
            throw new IllegalArgumentException("Illegal player: " + illegalPlayer.get());
        }
        char winner;
        for (char player : input.toCharArray()) {
            winner = recordPoint(player);
            System.out.println(translateScore(winner));

            // if a player win, the game ends even if more score points received
            if(winner != NO_WINNER){
                break;
            }
        }
    }
}
