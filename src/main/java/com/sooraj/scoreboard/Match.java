package com.sooraj.scoreboard;

public class Match {
    private int homeScore = -1;
    private int awayScore = -1;

    public void start() {
        homeScore = 0;
        awayScore = 0;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void updateScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }
}
