package com.sooraj.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sooraj.scoreboard.TestUtils.createMatch;
import static com.sooraj.scoreboard.TestUtils.createTeams;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class MatchTest {

    @Test
    void shouldRegisterTeamsForMatch() {
        List<Team> teams = createTeams("TeamA", "TeamB");
        Match match = createMatch(teams);
        assertThat(match.getTeams(), equalTo(teams));
    }

    @Test
    void shouldRegisterFootballTeamsForMatch(){
        Team homeTeam = new FootBallTeam("Mexico");
        Team awayTeam = new FootBallTeam("Canada");
        Match footBallMatch = new FootBallMatch();
        footBallMatch.register(homeTeam, awayTeam);
        assertThat(footBallMatch.getTeams(), equalTo(List.of(homeTeam, awayTeam)));
    }

    @Test
    void shouldRegisterTwoTeamsForFootballMatch() {
        List<Team> teams = createTeams("TeamA", "TeamB");
        Match footBallMatch = new FootBallMatch();
        footBallMatch.register(teams);

    }
}
