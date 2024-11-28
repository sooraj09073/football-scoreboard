package com.sooraj.scoreboard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ScoreboardTest {

    @Test
    void testScoresAreZeroWhenMatchStarts() {
        Match match = new Match();
        match.start();
        assertThat(match.getHomeScore(), equalTo(0));
        assertThat(match.getAwayScore(), equalTo(0));
    }


}
