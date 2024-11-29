package com.sooraj.scoreboard;

import java.util.List;

public abstract class Match {
    private List<Team> teams;
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
        return teams;
    }

    public final void register(List<Team> teams) {
        this.teams = teams;
    }

    public abstract void register(Team homeTeam, Team awayTeam);
}
