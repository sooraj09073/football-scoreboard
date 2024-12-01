package com.sooraj.scoreboard;

import com.sooraj.scoreboard.domain.Match;
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

    @BeforeEach
    void setUp() {
        FootballTeam teamA = new FootballTeam("Mexico");
        FootballTeam teamB = new FootballTeam("Canada");
        scoreBoard = new FootballScoreBoard();
        footballTeamRegistration = new FootballTeamRegistration();
        footballMatchManager = new FootballMatchManager(scoreBoard);

        match = new FootballMatch(footballTeamRegistration, footballMatchManager);
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
        assertThat("Match Started", match.hasStarted());
        assertThat("Home score should be initialized to 0 when match starts",
                match.getHomeScore(), equalTo(0));
        assertThat("Away score should be initialized to 0 when match starts",
                match.getAwayScore(), equalTo(0));
    }

    @Test
    void shouldUpdateScoresCorrectlyToScoreBoard(){
        match.start();
        // Update scores and validate
        match.updateScore(2,3);
        Match myLiveMatch = scoreBoard.findLiveMatch("Mexico","Canada");
        assertThat(myLiveMatch.getHomeScore(), equalTo(2));
        assertThat(myLiveMatch.getAwayScore(), equalTo(3));
    }

    @Test
    void shouldNotUpdateInvalidScoresToScoreBoard(){
        match.start();
        assertThrows(IllegalArgumentException.class, () -> match.updateScore(-1,-1),
                "Expected IllegalArgumentException when invalid scores are updated.");
    }

    @Test
    void shouldShowLiveMatchInScoreboard(){
        match.start();
        assertThat("Expected live match to be displayed in the scoreboard", scoreBoard.isMatchLive("Mexico", "Canada"));
    }

    @Test
    void shouldRemoveMatchFromScoreBoardWhenMatchEnds(){
        match.start();
        match.finish();
        boolean matchExist = scoreBoard.isMatchLive("Mexico", "Canada");
        assertThat(matchExist, equalTo(false));
    }

    @Test
    void testScoreSummary(){
        match.start();
        match.updateScore(0,5);

        FootballTeam spain = new FootballTeam("Spain");
        FootballTeam brazil = new FootballTeam("Brazil");
        Match match2 = new FootballMatch(footballTeamRegistration,footballMatchManager);
        match2.register(List.of(spain, brazil));
        match2.start();
        match2.updateScore(10,2);

        FootballTeam germany = new FootballTeam("Germany");
        FootballTeam france = new FootballTeam("France");
        Match match3 = new FootballMatch(footballTeamRegistration,footballMatchManager);
        match3.register(List.of(germany, france));
        match3.start();
        match3.updateScore(2,2);

        FootballTeam uruguay = new FootballTeam("Uruguay");
        FootballTeam italy = new FootballTeam("Italy");
        Match match4 = new FootballMatch(footballTeamRegistration,footballMatchManager);
        match4.register(List.of(uruguay, italy));
        match4.start();
        match4.updateScore(6,6);

        FootballTeam argentina = new FootballTeam("Argentina");
        FootballTeam australia = new FootballTeam("Australia");
        Match match5 = new FootballMatch(footballTeamRegistration,footballMatchManager);
        match5.register(List.of(argentina, australia));
        match5.start();
        match5.updateScore(3,1);

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
}
