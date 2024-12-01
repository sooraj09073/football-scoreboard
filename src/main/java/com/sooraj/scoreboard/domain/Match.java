package com.sooraj.scoreboard.domain;

import com.sooraj.scoreboard.service.TeamRegistration;
import com.sooraj.scoreboard.validator.MatchRegulator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.*;

public abstract class Match {
    private List<Team> teams;
    private final TeamRegistration teamRegistration;
    private final MatchRegulator matchRegulator;
    private final ScoreUpdater scoreUpdater;
    private boolean hasStarted = false;

    protected Match(TeamRegistration teamRegistration, MatchRegulator matchRegulator, ScoreUpdater scoreUpdater) {
        this.teamRegistration = teamRegistration;
        this.matchRegulator = matchRegulator;
        this.scoreUpdater = scoreUpdater;
    }

    public int getHomeScore() {
        return scoreUpdater.getHomeScore();
    }

    public int getAwayScore() {
        return scoreUpdater.getAwayScore();
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void setHomeScore(int homeScore) {
        scoreUpdater.setHomeScore(homeScore);
    }

    public void setAwayScore(int awayScore) {
        scoreUpdater.setAwayScore(awayScore);
    }

    public void hasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public boolean hasStarted() {
        return hasStarted;
    }

    public List<Team> getTeams() {
        return Optional.ofNullable(teams)
                .map(Collections::unmodifiableList)
                .orElse(emptyList());
    }

    public void start() {
        matchRegulator.start(this);
    }

    public void updateScore(int homeScore, int awayScore) {
        matchRegulator.updateScore(this, homeScore, awayScore);
    }

    public void finish() {
        matchRegulator.finish(this);
    }

    public final void register(List<Team> teams) {
        teamRegistration.register(this, teams);
    }

    public boolean canStart(){
        return matchRegulator.canStart(this);
    }
}
