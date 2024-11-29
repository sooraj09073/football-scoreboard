package com.sooraj.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

class ScoreboardTest {

    @Test
    void testScoresAreInvalidBeforeMatchStarts() {
        Match match = setMatch(getTeams());
        assertThat(match.getHomeScore(), lessThan(0));
        assertThat(match.getAwayScore(), lessThan(0));
    }


    @Test
    void testScoresAreZeroWhenMatchStarts() {
        Match match = setMatch(getTeams());
        match.start();
        assertThat(match.getHomeScore(), equalTo(0));
        assertThat(match.getAwayScore(), equalTo(0));
    }

    @Test
    void testUpdateScore(){
        Match match = setMatch(getTeams());
        match.start();
        match.updateScore(0,1);
        assertThat(match.getHomeScore(), equalTo(0));
        assertThat(match.getAwayScore(), equalTo(1));
    }

    @Test
    void testFinishMatch(){
        Match match = setMatch(getTeams());
        match.start();
        match.finish();
        assertThat(match.getHomeScore(), equalTo(-1));
        assertThat(match.getAwayScore(), equalTo(-1));
    }

    @Test
    void testAddTeam(){
        List<Team> teams = getTeams();
        Match match = setMatch(teams);
        assertThat(match.getTeams(), equalTo(teams));
    }

    @Test
    void testTeamName(){
       Team myTeam = new Team("MyTeam");
        assertThat(myTeam.getName(), equalTo("MyTeam"));
    }

    @Test
    void testRegisterFootballTeam(){
        Team homeTeam = new FootBallTeam("Mexico");
        Team awayTeam = new FootBallTeam("Canada");
        Match footBallMatch = new FootBallMatch();
        footBallMatch.register(homeTeam, awayTeam);
        assertThat(footBallMatch.getTeams(), equalTo(List.of(homeTeam, awayTeam)));
    }

    private Match setMatch(List<Team> teams) {
        Match match = new FootBallMatch();
        match.register(teams);
        return match;
    }

    private List<Team> getTeams(){
        return List.of(new Team("MyTeam"));
    }
}
