package com.sooraj.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class TeamTest {

    @Test
    void shouldAddTeamsToMatch(){
        List<Team> teams = List.of(new Team("A"));
        Match match = new FootBallMatch();
        match.register(teams);
        assertThat(match.getTeams(), equalTo(teams));
    }
}
