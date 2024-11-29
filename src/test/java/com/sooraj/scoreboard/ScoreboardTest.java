package com.sooraj.scoreboard;

import com.sooraj.scoreboard.football.FootballMatch;
import com.sooraj.scoreboard.football.FootballScoreBoard;
import com.sooraj.scoreboard.football.FootballTeam;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sooraj.scoreboard.TestUtils.createFootBallMatch;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

class ScoreboardTest {

    @Test
    void shouldSetScoresToInvalidBeforeFootballMatchStart() {
        Match match = createFootBallMatch();
        assertThat(match.getHomeScore(), lessThan(0));
        assertThat(match.getAwayScore(), lessThan(0));
    }

    @Test
    void shouldInitializeScoresToZeroWhenFootballMatchStarts() {
        Match match = createFootBallMatch();
        match.start();
        assertThat(match.getHomeScore(), equalTo(0));
        assertThat(match.getAwayScore(), equalTo(0));
    }

    @Test
    void shouldUpdateScoresCorrectly(){
        Match match = createFootBallMatch();
        match.start();
        match.updateScore(2,3);
        assertThat(match.getHomeScore(), equalTo(2));
        assertThat(match.getAwayScore(), equalTo(3));
    }

    @Test
    void shouldFinishMatchAndResetScoresToInvalid(){
        Match match = createFootBallMatch();
        match.start();
        match.finish();
        assertThat(match.getHomeScore(), lessThan(0));
        assertThat(match.getAwayScore(), lessThan(0));
    }
    @Test
    void shouldUpdateScoreBoardWhenMatchStarts(){
        FootballTeam teamA = new FootballTeam("TeamA");
        FootballTeam teamB = new FootballTeam("TeamB");
        ScoreBoard scoreBoard = new FootballScoreBoard();
        Match match = new FootballMatch(scoreBoard);
        match.register(List.of(teamA, teamB));
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
}
