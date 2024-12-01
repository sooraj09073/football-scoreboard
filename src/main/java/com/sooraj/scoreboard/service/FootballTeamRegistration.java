package com.sooraj.scoreboard.service;

import com.sooraj.scoreboard.domain.Team;

import java.util.List;

public class FootballTeamRegistration extends TeamRegistration {
    @Override
    public void validateTeams(List<Team> teams) {
        if (teams == null || teams.size() != 2) {
            throw new IllegalArgumentException("A football match must have exactly 2 teams.");
        }
    }
}
