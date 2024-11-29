package com.sooraj.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.sooraj.scoreboard.TestUtils.createMatch;
import static com.sooraj.scoreboard.TestUtils.createTeams;
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
        assertThat(match.getHomeScore(), lessThan(0));
        assertThat(match.getAwayScore(), lessThan(0));
    }
}
