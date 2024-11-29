package com.sooraj.scoreboard;

import java.util.List;

public class FootBallMatch extends Match {
    @Override
    protected void validateTeams(List<Team> teams) {
        if (teams == null || teams.size() != 2) {
            throw new IllegalArgumentException("A football match must have exactly 2 teams.");
        }
    }
}
