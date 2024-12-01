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
    private int homeScore = -1;
    private int awayScore = -1;
    private boolean hasStarted = false;

    protected Match(TeamRegistration teamRegistration, MatchRegulator matchRegulator) {
        this.teamRegistration = teamRegistration;
        this.matchRegulator = matchRegulator;
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
