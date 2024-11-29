package com.sooraj.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

class ScoreboardTest {

    @Test
    void shouldSetScoresToInvalidBeforeMatchStart() {
        Match match = createMatch(createTeams("MyTeam"));
        assertThat(match.getHomeScore(), lessThan(0));
        assertThat(match.getAwayScore(), lessThan(0));
    }

    @Test
    void shouldInitializeScoresToZeroWhenMatchStarts() {
        Match match = createMatch(createTeams("MyTeam"));
        match.start();
        assertThat(match.getHomeScore(), equalTo(0));
        assertThat(match.getAwayScore(), equalTo(0));
    }

    @Test
    void shouldUpdateScoresCorrectly(){
        Match match = createMatch(createTeams("MyTeam"));
        match.start();
        match.updateScore(2,3);
        assertThat(match.getHomeScore(), equalTo(2));
        assertThat(match.getAwayScore(), equalTo(3));
    }

    @Test
    void shouldFinishMatchAndResetScoresToInvalid(){
        Match match = createMatch(createTeams("MyTeam"));
        match.start();
        match.finish();
        assertThat(match.getHomeScore(), lessThan(-1));
        assertThat(match.getAwayScore(), lessThan(-1));
    }

    @Test
    void shouldRegisterTeamsForMatch() {
        List<Team> teams = createTeams("TeamA", "TeamB");
        Match match = createMatch(teams);
        assertThat(match.getTeams(), equalTo(teams));
    }

    @Test
    void testRegisterFootballTeam(){
        Team homeTeam = new FootBallTeam("Mexico");
        Team awayTeam = new FootBallTeam("Canada");
        Match footBallMatch = new FootBallMatch();
        footBallMatch.register(homeTeam, awayTeam);
        assertThat(footBallMatch.getTeams(), equalTo(List.of(homeTeam, awayTeam)));
    }

    private Match createMatch(List<Team> teams) {
        Match match = new FootBallMatch();
        match.register(teams);
        return match;
    }

    private List<Team> createTeams(String... teamNames){
        return Arrays.stream(teamNames)
                .map(Team::new)
                .toList();
    }
}
