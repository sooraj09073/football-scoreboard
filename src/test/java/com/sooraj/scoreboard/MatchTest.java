package com.sooraj.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class MatchTest {

    @Test
    void shouldRegisterFootballTeamsForMatch(){
        Team homeTeam = new FootBallTeam("Mexico");
        Team awayTeam = new FootBallTeam("Canada");
        Match footBallMatch = new FootBallMatch();
        footBallMatch.register(homeTeam, awayTeam);
        assertThat(footBallMatch.getTeams(), equalTo(List.of(homeTeam, awayTeam)));
    }

}
