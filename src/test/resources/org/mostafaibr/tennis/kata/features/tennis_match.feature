Feature: Tennis Match

  As a chair umpire
  I want to keep track of the score during a tennis match with a limit of games
  So that I can determine the winner of the whole match

  Scenario Outline: Record wins and determine match winner

    Given a tennis match of <nb_of_games>-games limit started
    When player <player> reaches <wins> wins
    Then player <match_winner> should be match winner
    And the final score should be announced as <score>

    Examples:

      | nb_of_games | player | wins | score                           | match_winner |
      | 3    | "A"    | 2    | "Player A wins the whole match." | "A"          |
      | 3    | "B"    | 2    | "Player B wins the whole match." | "B"          |
      | 5    | "A"    | 3    | "Player A wins the whole match." | "A"          |
      | 5    | "B"    | 3    | "Player B wins the whole match." | "B"          |


  Scenario: Match not completed
    Given a tennis match of 5-games limit started
    When inputs ends before having a winner
    Then the final score should be announced as "No winner! no enough inputs to determine the winner of the whole match."