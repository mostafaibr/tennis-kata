package org.mostafaibr.tennis.kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mostafaibr.tennis.kata.TennisGame.*;

public class TennisMatchTest {

    @Test
    @DisplayName("Test recordWin with no winner yet in 3-games limited match")
    public void testRecordWin_3GamesLimit_NoWinner() {
        TennisMatch tennisMatch = new TennisMatch(3);
        tennisMatch.getWins().put(PLAYER_A, 0);
        tennisMatch.getWins().put(PLAYER_B, 0);
        char winner = tennisMatch.recordWin("AAAA");
        assertEquals(NO_WINNER, winner);
    }

    @Test
    @DisplayName("Test recordWin when Player A wins a 3-games limited match")
    public void testRecordWin_3GamesLimit_PlayerAWins() {
        TennisMatch tennisMatch = new TennisMatch(3);
        tennisMatch.getWins().put(PLAYER_A, 1);
        tennisMatch.getWins().put(PLAYER_B, 0);
        char winner = tennisMatch.recordWin("AAAA");
        assertEquals(PLAYER_A, winner);
    }

    @Test
    @DisplayName("Test recordWin when Player B wins a 3-games limited match")
    public void testRecordWin_3GamesLimit_PlayerBWins() {
        TennisMatch tennisMatch = new TennisMatch(3);
        tennisMatch.getWins().put(PLAYER_A, 1);
        tennisMatch.getWins().put(PLAYER_B, 1);
        char winner = tennisMatch.recordWin("BBBB");
        assertEquals(PLAYER_B, winner);
    }

    @Test
    @DisplayName("Test recordWin with no winner yet in 5-games limited match")
    public void testRecordWin_5GamesLimit_NoWinner() {
        TennisMatch tennisMatch = new TennisMatch(5);
        tennisMatch.getWins().put(PLAYER_A, 1);
        tennisMatch.getWins().put(PLAYER_B, 0);
        char winner = tennisMatch.recordWin("AAAA");
        assertEquals(NO_WINNER, winner);
    }

    @Test
    @DisplayName("Test recordWin when Player A wins 5-games limited match")
    public void testRecordWin_5GamesLimit_PlayerAWins() {
        TennisMatch tennisMatch = new TennisMatch(5);
        tennisMatch.getWins().put(PLAYER_A, 2);
        tennisMatch.getWins().put(PLAYER_B, 2);
        char winner = tennisMatch.recordWin("AAAA");
        assertEquals(PLAYER_A, winner);
    }

    @Test
    @DisplayName("Test recordWin when Player B reaches wins 5-games limited match")
    public void testRecordWin_5GamesLimit_PlayerBWins() {
        TennisMatch tennisMatch = new TennisMatch(3);
        tennisMatch.getWins().put(PLAYER_A, 1);
        tennisMatch.getWins().put(PLAYER_B, 2);
        char winner = tennisMatch.recordWin("BBBB");
        assertEquals(PLAYER_B, winner);
    }

    @Test
    @DisplayName("Test play 3-games limited match with no winner as no enough input")
    public void testPlay_3GamesLimit_NoEnoughInputs() {
        TennisMatch tennisMatch = new TennisMatch(3);
        String output = tennisMatch.play(new String[]{"AAAA", "BBBB"});
        String expected = "No winner! no enough inputs to determine the winner of the whole match.";
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("Test play 5-games limited match with no winner as no enough input")
    public void testPlay_5GamesLimit_NoEnoughInputs() {
        TennisMatch tennisMatch = new TennisMatch(5);
        String output = tennisMatch.play(new String[]{"AAAA", "BBBB", "AAAA"});
        String expected = "No winner! no enough inputs to determine the winner of the whole match.";
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("Test play 3-games limited match when Player A wins")
    public void testPlay_3GamesLimit_PlayerAWins() {
        TennisMatch tennisMatch = new TennisMatch(3);
        String output = tennisMatch.play(new String[]{"AAAA", "BBBB", "AAAA"});
        String expected = "Player A wins the whole match.";
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("Test play 3-games limited match when Player A wins early")
    public void testPlay_3GamesLimit_PlayerAWins_Early() {
        TennisMatch tennisMatch = new TennisMatch(3);
        String output = tennisMatch.play(new String[]{"AAAA", "AAAA"});
        String expected = "Player A wins the whole match.";
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("Test play 5-games limited match when Player B wins")
    public void testPlay_5GamesLimit_PlayerBWins() {
        TennisMatch tennisMatch = new TennisMatch(5);
        String output = tennisMatch.play(new String[]{"BBBB", "AAAA", "BBBB", "AAAA", "BBBB"});
        String expected = "Player B wins the whole match.";
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("Test play 5-games limited match when Player B wins early")
    public void testPlay_5GamesLimit_PlayerBWins_Early() {
        TennisMatch tennisMatch = new TennisMatch(5);
        String output = tennisMatch.play(new String[]{"BBBB", "BBBB", "BBBB"});
        String expected = "Player B wins the whole match.";
        assertEquals(expected, output);
    }


}