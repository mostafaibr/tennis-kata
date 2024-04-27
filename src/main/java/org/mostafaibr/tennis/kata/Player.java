package org.mostafaibr.tennis.kata;

import lombok.Getter;
import lombok.Setter;


@Setter
class Player {
    @Getter
    private int score;

    private boolean advantage;

    public Player() {
        this.score = 0;
        this.advantage = false;
    }

    public boolean hasAdvantage() {
        return this.advantage;
    }

    public void incrementScore() {
        if(score == 30){
            score += 10;
        } else {
            score += 15;
        }
    }

    public boolean hasWinningScore(){
        return score == 40;
    }
}