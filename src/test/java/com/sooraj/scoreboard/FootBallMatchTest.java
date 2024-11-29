package com.sooraj.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FootBallMatchTest {

    @Test
    void shouldRegisterTwoFootballTeamsForMatch(){
        Team homeTeam = new FootBallTeam("Mexico");
        Team awayTeam = new FootBallTeam("Canada");
        ScoreBoard scoreBoard = new FootballScoreBoard();
        Match footBallMatch = new FootBallMatch(scoreBoard);
        footBallMatch.register(List.of(homeTeam, awayTeam));
        assertThat(footBallMatch.getTeams(), equalTo(List.of(homeTeam, awayTeam)));
    }

    @Test
    void shouldNotRegisterThreeFootballTeamsForMatch(){
        Team homeTeam = new FootBallTeam("Mexico");
        Team awayTeam = new FootBallTeam("Canada");
        Team thirdTeam = new FootBallTeam("France");
        ScoreBoard scoreBoard = new FootballScoreBoard();
        Match footBallMatch = new FootBallMatch(scoreBoard);
        List<Team> teams = List.of(homeTeam, awayTeam, thirdTeam);
        assertThrows(IllegalArgumentException.class, () -> footBallMatch.register(teams));
    }

    @Test
    void shouldNotRegisterSingleTeamForFootballMatch(){
        Team homeTeam = new FootBallTeam("Mexico");
        ScoreBoard scoreBoard = new FootballScoreBoard();
        Match footBallMatch = new FootBallMatch(scoreBoard);
        List<Team> teams = List.of(homeTeam);
        assertThrows(IllegalArgumentException.class, () -> footBallMatch.register(teams));
    }

    @Test
    void shouldNotRegisterEmptyTeamForFootballMatch(){
        ScoreBoard scoreBoard = new FootballScoreBoard();
        Match footBallMatch = new FootBallMatch(scoreBoard);
        List<Team> teams = List.of();
        assertThrows(IllegalArgumentException.class, () -> footBallMatch.register(teams));
    }
}
