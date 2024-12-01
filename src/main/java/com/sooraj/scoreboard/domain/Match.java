package com.sooraj.scoreboard.domain;

import com.sooraj.scoreboard.service.ScoreBoard;
import com.sooraj.scoreboard.service.Scoring;
import com.sooraj.scoreboard.service.TeamRegistration;
import com.sooraj.scoreboard.validator.TeamValidator;

import java.util.List;

import static java.util.Collections.*;

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
        scoreBoard.addMatch(this);
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
        scoreBoard.removeMatch(this);
    }

    public List<Team> getTeams() {
        return unmodifiableList(teams);
    }

    public final void register(List<Team> teams) {
        teamRegistration.register(this, teams);
    }

    public void validateTeams(List<Team> teams) {
        teamValidator.validateTeams(teams);
    }

    public boolean isReadyToStart() {
        return teams != null && teams.size() == 2;
    }
}
