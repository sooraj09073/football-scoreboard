package com.sooraj.scoreboard;

import java.util.List;

public class TestUtils {
    public static Match createFootBallMatch() {
        FootballTeam teamA = new FootballTeam("TeamA");
        FootballTeam teamB = new FootballTeam("TeamB");
        ScoreBoard scoreBoard = new FootballScoreBoard();
        Match match = new FootballMatch(scoreBoard);
        match.register(List.of(teamA, teamB));
        return match;
    }
}
