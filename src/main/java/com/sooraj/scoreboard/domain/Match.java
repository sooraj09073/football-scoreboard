package com.sooraj.scoreboard.domain;

import com.sooraj.scoreboard.service.TeamRegistration;
import com.sooraj.scoreboard.validator.MatchRegulator;

import java.util.List;

public abstract class Match {

    private final MatchRegulator matchRegulator;
    private final TeamRegistration teamRegistration;
    private boolean hasStarted = false;

    protected Match(TeamRegistration teamRegistration,MatchRegulator matchRegulator) {
        this.matchRegulator = matchRegulator;
        this.teamRegistration = teamRegistration;
    }

    public void hasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public boolean hasStarted() {
        return hasStarted;
    }

    public List<Team> getTeams() {
        return teamRegistration.getTeams();
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

    public boolean canStart(){
        return matchRegulator.canStart(this);
    }
}
