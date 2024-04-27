Feature: Tennis Game

  As a chair umpire
  I want to keep track of the score during a tennis game
  So that I can determine the winner

  Background:

    Given a tennis game is started

  Scenario Outline: Record points and announce winner

    Given player "A" has score <current_A>
    And player "B" has score <current_B>
    When player <scoring_player> scores a point
    Then the score should be announced as <score>
    And player <winner> should be winner

    Examples:

      | current_A | current_B | scoring_player | score                         | winner |
      | 0         | 0         | "A"            | "Player A: 15 / Player B: 0"  | " "    |
      | 15        | 0         | "B"            | "Player A: 15 / Player B: 15" | " "    |
      | 15        | 15        | "A"            | "Player A: 30 / Player B: 15" | " "    |
      | 30        | 15        | "A"            | "Player A: 40 / Player B: 15" | " "    |
      | 40        | 15        | "A"            | "Player A wins the game"      | "A"    |
      | 15        | 40        | "B"            | "Player B wins the game"      | "B"    |

  Scenario: Record point when deuce

    Given player "A" has score 40
    And player "B" has score 40
    When player "A" scores a point
    Then player "A" should have advantage
    And the score should be announced as "Player A: adv / Player B: 40"


  Scenario: Loose Advantage

    Given player "A" has score 40
    And player "B" has score 40
    And player "A" has advantage
    When player "B" scores a point
    Then player "A" should loose advantage
    And player "B" should have advantage
    And the score should be announced as "Player A: 40 / Player B: adv"

  Scenario: Invalid player scoring

    When player "X" scores a point
    Then an exception should be thrown with the message "Illegal player: X"