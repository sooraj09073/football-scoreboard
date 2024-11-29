package com.sooraj.scoreboard;

import java.util.List;

public class FootBallMatch extends Match {
    @Override
    public void register(Team homeTeam, Team awayTeam) {
        register(List.of(homeTeam, awayTeam));
    }
}
