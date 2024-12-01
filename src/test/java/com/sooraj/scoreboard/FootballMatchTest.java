package com.sooraj.scoreboard;

import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.domain.Team;
import com.sooraj.scoreboard.exception.MatchStateException;
import com.sooraj.scoreboard.football.FootballMatch;
import com.sooraj.scoreboard.football.FootballScoreBoard;
import com.sooraj.scoreboard.football.FootballTeam;
import com.sooraj.scoreboard.service.FootballTeamRegistration;
import com.sooraj.scoreboard.service.ScoreBoard;
import com.sooraj.scoreboard.validator.FootballMatchManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FootballMatchTest {

    private Match footballMatch;

    @BeforeEach
    void setUp() {
        ScoreBoard footballScoreBoard = new FootballScoreBoard();
        FootballTeamRegistration teamRegistration = new FootballTeamRegistration();
        FootballMatchManager footballMatchManager = new FootballMatchManager(footballScoreBoard);
        footballMatch = new FootballMatch(teamRegistration, footballMatchManager);
    }

    @Test
    void shouldRegisterTwoFootballTeamsForMatch(){
        List<Team>teams = registerTwoTeamForMatch();

        assertThat("The registered teams should match the expected list",
                footballMatch.getTeams(), equalTo(teams));
        assertTrue(footballMatch.canStart(),
                "The match should be ready to start after registering two teams");
    }

    @ParameterizedTest
    @MethodSource(value = "invalidTeamCombinations")
    void shouldNotRegisterInvalidFootballTeamsForMatch(List<Team> teams) {
        assertThrows(IllegalArgumentException.class, () -> footballMatch.register(teams),
                "Expected IllegalArgumentException when registering more than two teams");
    }

    @Test
    void shouldThrowExceptionWhenStartingMatchWithoutTeams() {
        assertThrows(MatchStateException.class, () -> footballMatch.start(),
                "Expected MatchStateException when starting without registering 2 teams");
    }

    @Test
    void shouldThrowExceptionWhenStartingMatchAgainWhichIsInProgress() {
        registerTwoTeamForMatch();
        footballMatch.start();
        assertThrows(MatchStateException.class, () -> footballMatch.start(),
                "Expected MatchStateException when starting match which is already in progress");
    }

    @Test
    void shouldThrowExceptionWhenTeamsAreNotReadyToStart() {
        List <Team> teams = registerTwoTeamForMatch();
        teams.forEach(team -> team.setReady(false));
        assertThrows(MatchStateException.class, () -> footballMatch.start(),
                "Expected MatchStateException when starting match where teams are not ready");
    }

    static Stream<List<Team>> invalidTeamCombinations(){
        return Stream.of(
                List.of(),
                List.of(new FootballTeam("Mexico")),
                List.of(new FootballTeam("Mexico"),
                        new FootballTeam("Canada"),
                        new FootballTeam("France"))
        );
    }

    private List<Team> registerTwoTeamForMatch(){
        Team homeTeam = new FootballTeam("Mexico");
        Team awayTeam = new FootballTeam("Canada");
        List<Team> teams = List.of(homeTeam, awayTeam);
        footballMatch.register(teams);
        return teams;
    }
}
