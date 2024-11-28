package com.sooraj.scoreboard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ScoreboardTest {
    @Test
    void testScoresAreZeroWhenMatchStarts() {
        Match match = new Match();
        match.start();
        assertThat(0, equalTo(match.getHomeScore()));
        assertThat(0, equalTo(match.getAwayScore()));
    }
}
