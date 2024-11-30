package com.sooraj.scoreboard;

public class ScoreManager implements Scoring {
    public void updateScore(Match match, int homeScore, int awayScore) {
        if(homeScore<0 || awayScore<0) {
            throw new IllegalArgumentException("Invalid score");
        }
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
    }
}
