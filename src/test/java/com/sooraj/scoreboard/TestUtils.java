package com.sooraj.scoreboard;

import com.sooraj.scoreboard.football.FootballMatch;
import com.sooraj.scoreboard.football.FootballScoreBoard;
import com.sooraj.scoreboard.football.FootballTeam;

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
