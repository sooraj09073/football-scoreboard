package com.sooraj.scoreboard;

import java.util.List;

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

    public void finish() {
        this.homeScore = -1;
        this.awayScore = -1;
    }

    public List<Team> getTeams() {
        return null;
    }

    public void setTeams(List<Team> teams) {
    }
}
