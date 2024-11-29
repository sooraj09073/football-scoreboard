package com.sooraj.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

class ScoreboardTest {

    @Test
    void testScoresAreInvalidBeforeMatchStarts() {
        Match match = setMatch(List.of(new Team()));
        assertThat(match.getHomeScore(), lessThan(0));
        assertThat(match.getAwayScore(), lessThan(0));
    }


    @Test
    void testScoresAreZeroWhenMatchStarts() {
        Match match = setMatch(List.of(new Team()));
        match.start();
        assertThat(match.getHomeScore(), equalTo(0));
        assertThat(match.getAwayScore(), equalTo(0));
    }

    @Test
    void testUpdateScore(){
        Match match = setMatch(List.of(new Team()));
        match.start();
        match.updateScore(0,1);
        assertThat(match.getHomeScore(), equalTo(0));
        assertThat(match.getAwayScore(), equalTo(1));
    }

    @Test
    void testFinishMatch(){
        Match match = setMatch(List.of(new Team()));
        match.start();
        match.finish();
        assertThat(match.getHomeScore(), equalTo(-1));
        assertThat(match.getAwayScore(), equalTo(-1));
    }

    @Test
    void testAddTeam(){
        List<Team> teams = List.of(new Team());
        Match match = setMatch(teams);
        assertThat(match.getTeams(), equalTo(teams));
    }

    private Match setMatch(List<Team> teams) {
        Match match = new Match();
        match.setTeams(teams);
        return match;
    }
}
