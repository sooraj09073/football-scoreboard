package com.sooraj.scoreboard;

import java.util.List;

public class TestUtils {
    public static Match createFootBallMatch() {
        FootBallTeam teamA = new FootBallTeam("TeamA");
        FootBallTeam teamB = new FootBallTeam("TeamB");
        ScoreBoard scoreBoard = new FootballScoreBoard();
        Match match = new FootBallMatch(scoreBoard);
        match.register(List.of(teamA, teamB));
        return match;
    }
}
