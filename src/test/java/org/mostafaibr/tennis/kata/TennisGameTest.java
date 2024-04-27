package org.mostafaibr.tennis.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mostafaibr.tennis.kata.TennisGame.*;

public class TennisGameTest {

    TennisGame game;
    @BeforeEach
    public void initializeTennisGame(){
        game = new TennisGame();
    }
    @Test
    @DisplayName("Test translateScore when game is in progress and no winner yet")
    public void testTranslateScore_GameInProgress() {
        game.getPlayerA().setScore(15);
        game.getPlayerB().setScore(30);
        assertEquals("Player A: 15 / Player B: 30", game.translateScore(NO_WINNER));
    }

    @Test
    @DisplayName("Test translateScore when a player wins")
    public void testTranslateScore_PlayerWins() {
        assertEquals("Player A wins the game", game.translateScore(PLAYER_A));
    }

    @Test
    @DisplayName("Test recordPoint and score when no winner yet and no advantage")
    public void testRecordPoint_NoAdvantage() {
        char winner = game.recordPoint(PLAYER_A);
        assertEquals(15, game.getPlayerA().getScore());
        assertEquals(0, game.getPlayerB().getScore());
        assertEquals(NO_WINNER, winner);
        assertEquals("Player A: 15 / Player B: 0", game.translateScore(winner));
    }

    @Test
    @DisplayName("Test recordPoint and score when a player wins")
    public void testRecordPoint_PlayerWins() {
        game.getPlayerA().setScore(40);
        game.getPlayerB().setScore(30);
        char winner = game.recordPoint(PLAYER_A);
        assertEquals(PLAYER_A, winner);
        assertEquals("Player A wins the game", game.translateScore(winner));
    }

    @Test
    @DisplayName("Test recordPoint and score when game in deuce and a player get advantage")
    public void testRecordPoint_Deuce_Advantage() {
        game.getPlayerA().setScore(40);
        game.getPlayerB().setScore(40);
        char winner = game.recordPoint(PLAYER_A);
        assertTrue(game.getPlayerA().hasAdvantage());
        assertFalse(game.getPlayerB().hasAdvantage());
        assertEquals(NO_WINNER, winner);
        assertEquals("Player A: adv / Player B: 40", game.translateScore(winner));
    }

    @Test
    @DisplayName("Test recordPoint when a player has advantage in deuce and loose it")
    public void testRecordPoint_Advantage_LooseAdvantage() {
        game.getPlayerA().setScore(40);
        game.getPlayerB().setScore(40);
        game.getPlayerA().setAdvantage(true);
        game.recordPoint(PLAYER_B);
        assertFalse(game.getPlayerA().hasAdvantage());
        assertTrue(game.getPlayerB().hasAdvantage());
    }

    @Test
    @DisplayName("Test recordPoint and score when a player has advantage in deuce and win the game")
    public void testRecordPoint_Advantage_PlayerWins() {
        game.getPlayerA().setScore(40);
        game.getPlayerB().setScore(40);
        game.recordPoint(PLAYER_A);
        assertTrue(game.getPlayerA().hasAdvantage());
        char winner = game.recordPoint(PLAYER_A);
        assertEquals(PLAYER_A, winner);
        assertEquals("Player A wins the game", game.translateScore(winner));
    }

    @Test
    @DisplayName("Test recordPoint when illegal player character inputted")
    public void testRecordPoint_IllegalCharacter() {
        assertThrows(IllegalArgumentException.class, () -> game.recordPoint('C'), "Illegal player: C");
    }

    @Test
    @DisplayName("Test play output when game in progress")
    public void testPlay_15Love_30Love() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        game.play("AA");
        StringBuilder expected = new StringBuilder()
                .append("Player A: 15 / Player B: 0\n")
                .append("Player A: 30 / Player B: 0\n");
        assertEquals(expected.toString(), out.toString());
    }

    @Test
    @DisplayName("Test play output when a player wins")
    public void testPlay_PlayerWins() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        game.play("AABBBB");
        StringBuilder expected = new StringBuilder()
                .append("Player A: 15 / Player B: 0\n")
                .append("Player A: 30 / Player B: 0\n")
                .append("Player A: 30 / Player B: 15\n")
                .append("Player A: 30 / Player B: 30\n")
                .append("Player A: 30 / Player B: 40\n")
                .append("Player B wins the game\n");

        assertEquals(expected.toString(), out.toString());
    }

    @Test
    @DisplayName("Test play output when game in deuce and players gain and loose advantage")
    public void testPlay_DeuceAndAdvantage() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        game.play("ABABABABB");
        StringBuilder expected = new StringBuilder()
                .append("Player A: 15 / Player B: 0\n")
                .append("Player A: 15 / Player B: 15\n")
                .append("Player A: 30 / Player B: 15\n")
                .append("Player A: 30 / Player B: 30\n")
                .append("Player A: 40 / Player B: 30\n")
                .append("Player A: 40 / Player B: 40\n")
                .append("Player A: adv / Player B: 40\n")
                .append("Player A: 40 / Player B: adv\n")
                .append("Player B wins the game\n");
        assertEquals(expected.toString(), out.toString());
    }

    @Test
    @DisplayName("Test play output when a player wins and ignore the rest of points")
    public void testPlay_GameAlreadyEnd() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        game.play("AABBBBAAAAAAAAAAAAAAAAAAAAAA");
        StringBuilder expected = new StringBuilder()
                .append("Player A: 15 / Player B: 0\n")
                .append("Player A: 30 / Player B: 0\n")
                .append("Player A: 30 / Player B: 15\n")
                .append("Player A: 30 / Player B: 30\n")
                .append("Player A: 30 / Player B: 40\n")
                .append("Player B wins the game\n");

        assertEquals(expected.toString(), out.toString());
    }

    @Test
    @DisplayName("Test play when an illegal player scores a point")
    public void testPlay_IllegalPlayerInInput() {
        assertThrows(IllegalArgumentException.class, () -> game.play("AX"), "Illegal player: X");
    }
}