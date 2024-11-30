package com.sooraj.scoreboard;

import java.util.List;

public abstract class Match {
    private List<Team> teams;
    private final ScoreBoard scoreBoard;
    private int homeScore = -1;
    private int awayScore = -1;

    protected Match(ScoreBoard scoreBoard){
        this.scoreBoard = scoreBoard;
    }

    public void start() {
        if(!isReadyToStart()){
            throw new IllegalStateException("Cannot start the match: Teams registration is not complete.");
        }

        homeScore = 0;
        awayScore = 0;
        scoreBoard.setScore(this);
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
        validateTeams(teams);
        this.teams = teams;
    }

    // Hook method for subclasses to enforce team constraints
    protected abstract void validateTeams(List<Team> teams);

    public abstract boolean isReadyToStart();
}
