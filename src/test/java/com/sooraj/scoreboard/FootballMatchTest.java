package com.sooraj.scoreboard;

import com.sooraj.scoreboard.football.FootballMatch;
import com.sooraj.scoreboard.football.FootballScoreBoard;
import com.sooraj.scoreboard.football.FootballTeam;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FootballMatchTest {

    @Test
    void shouldRegisterTwoFootballTeamsForMatch(){
        Team homeTeam = new FootballTeam("Mexico");
        Team awayTeam = new FootballTeam("Canada");
        ScoreBoard scoreBoard = new FootballScoreBoard();
        Match footBallMatch = new FootballMatch(scoreBoard);
        footBallMatch.register(List.of(homeTeam, awayTeam));
        assertThat(footBallMatch.getTeams(), equalTo(List.of(homeTeam, awayTeam)));
    }

    @Test
    void shouldNotRegisterThreeFootballTeamsForMatch(){
        Team homeTeam = new FootballTeam("Mexico");
        Team awayTeam = new FootballTeam("Canada");
        Team thirdTeam = new FootballTeam("France");
        ScoreBoard scoreBoard = new FootballScoreBoard();
        Match footBallMatch = new FootballMatch(scoreBoard);
        List<Team> teams = List.of(homeTeam, awayTeam, thirdTeam);
        assertThrows(IllegalArgumentException.class, () -> footBallMatch.register(teams));
    }

    @Test
    void shouldNotRegisterSingleTeamForFootballMatch(){
        Team homeTeam = new FootballTeam("Mexico");
        ScoreBoard scoreBoard = new FootballScoreBoard();
        Match footBallMatch = new FootballMatch(scoreBoard);
        List<Team> teams = List.of(homeTeam);
        assertThrows(IllegalArgumentException.class, () -> footBallMatch.register(teams));
    }

    @Test
    void shouldNotRegisterEmptyTeamForFootballMatch(){
        ScoreBoard scoreBoard = new FootballScoreBoard();
        Match footBallMatch = new FootballMatch(scoreBoard);
        List<Team> teams = List.of();
        assertThrows(IllegalArgumentException.class, () -> footBallMatch.register(teams));
    }
}
