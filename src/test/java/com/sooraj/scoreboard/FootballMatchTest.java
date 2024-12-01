package com.sooraj.scoreboard;

import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.domain.Team;
import com.sooraj.scoreboard.football.FootballMatch;
import com.sooraj.scoreboard.football.FootballScoreBoard;
import com.sooraj.scoreboard.domain.FootballTeam;
import com.sooraj.scoreboard.service.ScoreBoard;
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

    private ScoreBoard footballScoreBoard;
    private Match footballMatch;

    @BeforeEach
    void setUp() {
        footballScoreBoard = new FootballScoreBoard();
        footballMatch = new FootballMatch(footballScoreBoard);
    }

    @Test
    void shouldRegisterTwoFootballTeamsForMatch(){
        Team homeTeam = new FootballTeam("Mexico");
        Team awayTeam = new FootballTeam("Canada");

        footballMatch.register(List.of(homeTeam, awayTeam));

        assertThat("The registered teams should match the expected list",
                footballMatch.getTeams(), equalTo(List.of(homeTeam, awayTeam)));
        assertTrue(footballMatch.isReadyToStart(),
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
        assertThrows(IllegalStateException.class, () -> footballMatch.start(),
                "Expected IllegalStateException when starting without registering 2 teams");
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
}
