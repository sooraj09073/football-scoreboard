package com.sooraj.scoreboard;

import java.util.List;

public class FootballMatch extends Match {
    protected FootballMatch(ScoreBoard scoreBoard) {
        super(scoreBoard);
    }

    @Override
    protected void validateTeams(List<Team> teams) {
        if (teams == null || teams.size() != 2) {
            throw new IllegalArgumentException("A football match must have exactly 2 teams.");
        }
    }
}
