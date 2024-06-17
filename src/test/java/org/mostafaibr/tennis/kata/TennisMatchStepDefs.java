package org.mostafaibr.tennis.kata;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mostafaibr.tennis.kata.TennisGame.NO_WINNER;

public class TennisMatchStepDefs {
    TennisMatch match;
    String finalScore;

    @Given("a tennis match of {int}-games limit started")
    public void aTennisMatchGameLimitStarted(int limit) {
        match = new TennisMatch(limit);
    }

    @When("player {string} reaches {int} wins")
    public void playerReachesWinScore(String player, int winsCount) {
        String[] inputs = reachWinHelper(player, winsCount);
        finalScore = match.play(inputs);
    }

    @When("inputs ends before having a winner")
    public void inputsEnds() {
        String[] inputs = new String[]{"AAAA"};
        finalScore = match.play(inputs);
        assertEquals(NO_WINNER, match.getMatchWinner());
    }

    @Then("player {string} should be match winner")
    public void playerShouldBeWinner(String player) {
        assertEquals(player.charAt(0), match.getMatchWinner());
    }

    @Then("the final score should be announced as {string}")
    public void theFinalScoreShouldBeAnnouncedAs(String announcement) {
        assertEquals(announcement, finalScore);
    }

    private static String[] reachWinHelper(String player, int winsCount){
        String inputToWin = String.valueOf(player).repeat(4);
        String[] inputs = new String[winsCount];
        Arrays.fill(inputs, inputToWin);
        return inputs;
    }
}
