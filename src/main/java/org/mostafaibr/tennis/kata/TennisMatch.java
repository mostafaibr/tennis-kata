package org.mostafaibr.tennis.kata;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

import static org.mostafaibr.tennis.kata.TennisGame.*;

@Getter
@Setter
public class TennisMatch {
    private int limit;
    private Map<Character, Integer> wins;
    private TennisGame tennisGame;
    private char matchWinner;

    /**
     *
     * A constructor that initialize a multi-game tennis match
     *
     * @param limit the limit of the number of games to be played in one match
     */
    public TennisMatch(int limit){
        this.limit = limit;
        this.matchWinner = NO_WINNER;
        this.wins = new HashMap<>() {{
            put(PLAYER_A, 0);
            put(PLAYER_B, 0);
        }};
        this.tennisGame = new TennisGame();
    }

    /**
     *
     * A function to play a single game and record the winner
     *
     * @param input the scores played in a single game
     * @return winner of the match if there is any yet
     */
    char recordWin(String input){
        char gameWinner = tennisGame.play(input);
        wins.compute(gameWinner, (key, oldValue) -> (oldValue == null) ? 1 : oldValue + 1);
        return wins.get(gameWinner) > (limit/2) ? gameWinner : NO_WINNER;
    }

    /**
     *
     * A function to play multiple games and determine the winner
     *
     * @param inputs the scores of all the games played in this match
     * @return score to be display at the end of match
     *
     */
    String play(String[] inputs){
        for(int i=0; i<limit; i++){
            if(i>= inputs.length){
                break;
            }
            System.out.printf("Game %d:\n", i+1);
            matchWinner = recordWin(inputs[i]);
            // In case one player won and players continue to play, remaining scores are ignored
            if(matchWinner != NO_WINNER){
                break;
            }
            // Reset scores after each game done
            tennisGame.resetScores();
        }

        if(matchWinner != NO_WINNER){
            return String.format("Player %c wins the whole match.", matchWinner);
        }
        return "No winner! no enough inputs to determine the winner of the whole match.";
    }

    /**
     *
     * A function to display the score on the scoring board
     *
     * @param inputs the scores of all the games played in this match
     */
    public void displayScore(String[] inputs){
        System.out.println("\n" + play(inputs));
    }
}
