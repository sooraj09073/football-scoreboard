package com.sooraj.scoreboard;

public class ScoreManager implements Scoring {
    public void updateScore(Match match, int homeScore, int awayScore) {
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
    }
}
