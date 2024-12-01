package com.sooraj.scoreboard.domain;

public class MatchScore implements ScoreUpdater {
    private int homeScore=-1;
    private int awayScore=-1;

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }
}
