package com.sooraj.scoreboard.football;

import com.sooraj.scoreboard.Match;
import com.sooraj.scoreboard.ScoreBoard;
import com.sooraj.scoreboard.Team;

import java.util.List;
import java.util.Optional;

public class FootballMatch extends Match {
    public FootballMatch(ScoreBoard scoreBoard) {
        super(scoreBoard);
    }

    @Override
    protected void validateTeams(List<Team> teams) {
        if (teams == null || teams.size() != 2) {
            throw new IllegalArgumentException("A football match must have exactly 2 teams.");
        }
    }

    @Override
    public boolean isReadyToStart() {
        return Optional.ofNullable(getTeams())
                        .map(teams -> teams.size()==2)
                        .orElse(false);
    }
}
