package com.sooraj.scoreboard;

import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.domain.MatchScore;
import com.sooraj.scoreboard.domain.ScoreUpdater;
import com.sooraj.scoreboard.exception.LiveMatchNotFoundException;
import com.sooraj.scoreboard.football.FootballMatch;
import com.sooraj.scoreboard.football.FootballScoreBoard;
import com.sooraj.scoreboard.football.FootballTeam;
import com.sooraj.scoreboard.service.FootballTeamRegistration;
import com.sooraj.scoreboard.service.ScoreBoard;
import com.sooraj.scoreboard.validator.FootballMatchManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreboardTest {

    Match match;
    ScoreBoard scoreBoard;
    FootballTeamRegistration footballTeamRegistration;
    FootballMatchManager footballMatchManager;
    ScoreUpdater scoreUpdater;

    @BeforeEach
    void setUp() {
        scoreUpdater = new MatchScore();
        scoreBoard = new FootballScoreBoard();
        footballTeamRegistration = new FootballTeamRegistration();
        footballMatchManager = new FootballMatchManager(scoreBoard, scoreUpdater);
    }

    @Test
    void shouldInitializeScoresCorrectly() {
        match = createAndRegisterMatch("Mexico", "Canada");
        // Before match starts, scores should be invalid
        assertThat("Home score should be invalid before the match starts",
                scoreUpdater.getHomeScore(), lessThan(0));
        assertThat("Away score should be invalid before the match starts",
                scoreUpdater.getAwayScore(), lessThan(0));

        // After match starts, scores should be initialized to zero
        match.start();
        assertThat("Match Started", match.hasStarted());
        assertThat("Home score should be initialized to 0 when match starts",
                scoreUpdater.getHomeScore(), equalTo(0));
        assertThat("Away score should be initialized to 0 when match starts",
                scoreUpdater.getAwayScore(), equalTo(0));
    }

    @Test
    void shouldUpdateScoresCorrectlyToScoreBoard(){
        match = createAndRegisterMatch("Mexico", "Canada");
        match.start();
        match.updateScore(2,3);
        assertThat("Started match should be in scoreboard", scoreBoard.isMatchLive("Mexico", "Canada"));
        assertThat(scoreUpdater.getHomeScore(), equalTo(2));
        assertThat(scoreUpdater.getAwayScore(), equalTo(3));
    }

    @Test
    void shouldNotUpdateInvalidScoresToScoreBoard(){
        match = createAndRegisterMatch("Mexico", "Canada");
        match.start();
        assertThrows(IllegalArgumentException.class, () -> match.updateScore(-1,-1),
                "Expected IllegalArgumentException when invalid scores are updated.");
    }

    @Test
    void shouldShowLiveMatchInScoreboard(){
        match = createAndRegisterMatch("Mexico", "Canada");
        match.start();
        assertThat("Expected live match to be displayed in the scoreboard", scoreBoard.isMatchLive("Mexico", "Canada"));
    }

    @Test
    void shouldThrowExceptionWhenLiveMatchCannotFindInScoreboard(){
        assertThrows(LiveMatchNotFoundException.class, () -> scoreBoard.findLiveMatch("NoTeam"),
                "Expected IllegalStateException when starting without registering 2 teams");
    }

    @Test
    void shouldRemoveMatchFromScoreBoardWhenMatchEnds(){
        match = createAndRegisterMatch("Mexico", "Canada");
        match.start();
        match.finish();
        boolean matchExist = scoreBoard.isMatchLive("Mexico", "Canada");
        assertThat(matchExist, equalTo(false));
    }

    @Test
    void testScoreSummary(){
        List<MatchSetup> matchSetups = List.of(
                new MatchSetup("Mexico", "Canada", 0,5),
                new MatchSetup("Spain", "Brazil", 10, 2),
                new MatchSetup("Germany", "France", 2, 2),
                new MatchSetup("Uruguay", "Italy", 6, 6),
                new MatchSetup("Argentina", "Australia", 3, 1)
        );

        for (MatchSetup setup : matchSetups) {
            registerAndStartMatch(setup);
        }

        String summary = scoreBoard.getSummary();

        assertThat(summary, equalTo(""" 
                Uruguay 6 - Italy 6 
                Spain 10 - Brazil 2 
                Mexico 0 - Canada 5 
                Argentina 3 - Australia 1 
                Germany 2 - France 2 
                """
        ));
    }

    private Match createAndRegisterMatch(String homeTeamName, String awayTeamName) {
        FootballTeam homeTeam = new FootballTeam(homeTeamName);
        FootballTeam awayTeam = new FootballTeam(awayTeamName);
        FootballTeamRegistration teamRegistration = new FootballTeamRegistration();
        teamRegistration.register(List.of(homeTeam, awayTeam));

        return new FootballMatch(teamRegistration, footballMatchManager);
    }

    private void registerAndStartMatch(MatchSetup setup) {
        match = createAndRegisterMatch(setup.homeTeam, setup.awayTeam);
        match.start();
        match.updateScore(setup.homeScore, setup.awayScore);
    }

    private static class MatchSetup {
        String homeTeam;
        String awayTeam;
        int homeScore;
        int awayScore;

        MatchSetup(String homeTeam, String awayTeam, int homeScore, int awayScore) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.homeScore = homeScore;
            this.awayScore = awayScore;
        }
    }
}
