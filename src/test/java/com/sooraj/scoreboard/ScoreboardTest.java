package com.sooraj.scoreboard;

import com.sooraj.scoreboard.football.FootballMatch;
import com.sooraj.scoreboard.football.FootballScoreBoard;
import com.sooraj.scoreboard.football.FootballTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

class ScoreboardTest {

    Match match;
    ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        FootballTeam teamA = new FootballTeam("TeamA", MatchSide.HOME_SIDE);
        FootballTeam teamB = new FootballTeam("TeamB", MatchSide.AWAY_SIDE);
        scoreBoard = new FootballScoreBoard();
        match = new FootballMatch(scoreBoard);
        match.register(List.of(teamA, teamB));
    }

    @Test
    void shouldInitializeScoresCorrectly() {
        // Before match starts, scores should be invalid
        assertThat("Home score should be invalid before the match starts",
                match.getHomeScore(), lessThan(0));
        assertThat("Away score should be invalid before the match starts",
                match.getAwayScore(), lessThan(0));

        // After match starts, scores should be initialized to zero
        match.start();
        assertThat("Home score should be initialized to 0 when match starts",
                match.getHomeScore(), equalTo(0));
        assertThat("Away score should be initialized to 0 when match starts",
                match.getAwayScore(), equalTo(0));
    }

    @Test
    void shouldUpdateScoresCorrectly(){
        match.start();
        // Update scores and validate
        match.updateScore(2,3);
        assertThat(match.getHomeScore(), equalTo(2));
        assertThat(match.getAwayScore(), equalTo(3));
    }

    @Test
    void shouldFinishMatchAndResetScoresToInvalid(){
        match.start();
        match.finish();
        assertThat(match.getHomeScore(), lessThan(0));
        assertThat(match.getAwayScore(), lessThan(0));
    }
    @Test
    void shouldUpdateScoreBoardWhenMatchStarts(){
        match.start();
        List<Match> currentMatch = scoreBoard
                .getMatchList()
                .stream()
                .filter(liveMatch-> {
                    List<Team> teams = liveMatch.getTeams();
                    return teams.get(0).getName().equals("TeamA")
                            && teams.get(1).getName().equals("TeamB");
                }).toList();
        assertThat(currentMatch.size(), equalTo(1));
    }

    @Test
    void shouldRemoveMatchFromScoreBoardWhenMatchEnds(){
        match.start();
        match.finish();
        boolean matchExist = scoreBoard.isMatchLive("TeamA", "TeamB");
        assertThat(matchExist, equalTo(false));
    }
}
