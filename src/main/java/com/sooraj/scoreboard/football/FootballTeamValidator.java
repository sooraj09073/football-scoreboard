package com.sooraj.scoreboard.football;

import com.sooraj.scoreboard.domain.Team;
import com.sooraj.scoreboard.TeamValidator;

import java.util.List;

public class FootballTeamValidator implements TeamValidator {
    @Override
    public void validateTeams(List<Team> teams) {
        if (teams == null || teams.size() != 2) {
            throw new IllegalArgumentException("A football match must have exactly 2 teams.");
        }
    }
}