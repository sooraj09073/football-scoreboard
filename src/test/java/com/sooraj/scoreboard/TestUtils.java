package com.sooraj.scoreboard;

import java.util.List;

public class TestUtils {
    public static Match createFootBallMatch() {
        FootBallTeam teamA = new FootBallTeam("TeamA");
        FootBallTeam teamB = new FootBallTeam("TeamB");
        Match match = new FootBallMatch();
        match.register(List.of(teamA, teamB));
        return match;
    }
}
