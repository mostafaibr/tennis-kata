package org.mostafaibr.tennis.kata;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mostafaibr.tennis.kata.TennisGame.PLAYER_A;

public class TennisGameStepDefs {
    private static TennisGame tennisGame;
    private char winner;
    private String exception;

    @Before
    @Given("a tennis game is started")
    public void initializeTennisGame() {
        tennisGame = new TennisGame();
    }

    @Given("player {string} has score {int}")
    public void playerHasScore(String player, int score) {
        getDesiredPlayerHelper(player).setScore(score);
    }

    @Given("player {string} has advantage")
    public void playerHasAdvantage(String player) {
        getDesiredPlayerHelper(player).setAdvantage(true);
    }

    @When("player {string} scores a point")
    public void playerScoresPoint(String player) {
        try{
            winner = tennisGame.recordPoint(player.charAt(0));
        } catch (Exception e){
            exception = e.getMessage();
        }
    }

    @Then("player {string} should have advantage")
    public void playerShouldHaveAdvantage(String player) {
        Assertions.assertTrue(getDesiredPlayerHelper(player).hasAdvantage());
    }

    @Then("player {string} should loose advantage")
    public void playerShouldLooseAdvantage(String player) {
        Assertions.assertFalse(getDesiredPlayerHelper(player).hasAdvantage());
    }
    @Then("player {string} should be winner")
    public void playerShouldBeWinner(String player) {
        assertEquals(winner, player.charAt(0));
    }

    @Then("the score should be announced as {string}")
    public void theScoreShouldBeAnnouncedAs(String score) {
        assertEquals(score, tennisGame.translateScore(winner));
    }


    @Then("an exception should be thrown with the message {string}")
    public void anExceptionShouldBeThrownWithTheMessage(String errorMessage) {
        assertEquals(errorMessage, exception);
    }

    private static Player getDesiredPlayerHelper(String player){
        return player.charAt(0) == PLAYER_A ? tennisGame.getPlayerA() : tennisGame.getPlayerB();
    }

}