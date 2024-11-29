package com.sooraj.scoreboard;

import java.util.Arrays;
import java.util.List;

public class TestUtils {
    public static Match createMatch(List<Team> teams) {
        Match match = new FootBallMatch();
        match.register(teams);
        return match;
    }

    public static List<Team> createTeams(String... teamNames){
        return Arrays.stream(teamNames)
                .map(Team::new)
                .toList();
    }
}
