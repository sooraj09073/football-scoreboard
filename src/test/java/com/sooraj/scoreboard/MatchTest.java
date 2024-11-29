package com.sooraj.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatchTest {

    @Test
    void shouldRegisterTwoFootballTeamsForMatch(){
        Team homeTeam = new FootBallTeam("Mexico");
        Team awayTeam = new FootBallTeam("Canada");
        Match footBallMatch = new FootBallMatch();
        footBallMatch.register(List.of(homeTeam, awayTeam));
        assertThat(footBallMatch.getTeams(), equalTo(List.of(homeTeam, awayTeam)));
    }

    @Test
    void shouldNotRegisterThreeFootballTeamsForMatch(){
        Team homeTeam = new FootBallTeam("Mexico");
        Team awayTeam = new FootBallTeam("Canada");
        Team thirdTeam = new FootBallTeam("France");
        Match footBallMatch = new FootBallMatch();
        List<Team> teams = List.of(homeTeam, awayTeam, thirdTeam);
        assertThrows(IllegalArgumentException.class, () -> footBallMatch.register(teams));
    }
}
