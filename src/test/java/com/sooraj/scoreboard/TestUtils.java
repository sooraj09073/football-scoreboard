package com.sooraj.scoreboard;

import java.util.Arrays;
import java.util.List;

public class TestUtils {
    public static Match createFootBallMatch() {
        FootBallTeam teamA = new FootBallTeam("TeamA");
        FootBallTeam teamB = new FootBallTeam("TeamB");
        Match match = new FootBallMatch();
        match.register(List.of(teamA, teamB));
        return match;
    }

    public static List<Team> createTeams(String... teamNames){
        return Arrays.stream(teamNames)
                .map(Team::new)
                .toList();
    }
}
