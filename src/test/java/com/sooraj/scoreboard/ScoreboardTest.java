package com.sooraj.scoreboard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

class ScoreboardTest {

    @Test
    void testScoresAreInvalidBeforeMatchStarts() {
        Match match = new Match();
        assertThat(match.getHomeScore(), lessThan(0));
        assertThat(match.getAwayScore(), lessThan(0));
    }


    @Test
    void testScoresAreZeroWhenMatchStarts() {
        Match match = new Match();
        match.start();
        assertThat(match.getHomeScore(), equalTo(0));
        assertThat(match.getAwayScore(), equalTo(0));
    }

    @Test
    void testUpdateScore(){
        Match match = new Match();
        match.start();
        match.updateScore(0,1);
        assertThat(match.getHomeScore(), equalTo(0));
        assertThat(match.getAwayScore(), equalTo(1));
    }

    @Test
    void testFinishMatch(){
        Match match = new Match();
        match.start();
        match.finish();
        assertThat(match.getHomeScore(), equalTo(-1));
        assertThat(match.getAwayScore(), equalTo(-1));
    }
}
