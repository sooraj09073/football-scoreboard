package com.sooraj.scoreboard;

import java.util.List;

public abstract class Match {
    private List<Team> teams;
    private final ScoreBoard scoreBoard;
    private final TeamValidator teamValidator;
    private final TeamRegistration teamRegistration;
    private final Scoring scoring;
    private int homeScore = -1;
    private int awayScore = -1;

    protected Match(ScoreBoard scoreBoard, TeamValidator teamValidator,
                    TeamRegistration teamRegistration, Scoring scoring) {
        this.scoreBoard = scoreBoard;
        this.teamValidator = teamValidator;
        this.teamRegistration = teamRegistration;
        this.scoring = scoring;
    }

    public void start() {
        if (!isReadyToStart()) {
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

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public void updateScore(int homeScore, int awayScore) {
        scoring.updateScore(this, homeScore, awayScore);
    }

    public void finish() {
        this.homeScore = -1;
        this.awayScore = -1;
        scoreBoard.removeMatch(this);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public final void register(List<Team> teams) {
        teamRegistration.register(this, teams);
    }

    protected void validateTeams(List<Team> teams) {
        teamValidator.validateTeams(teams);
    }

    protected boolean isReadyToStart() {
        return teams != null && teams.size() == 2;
    }
}
