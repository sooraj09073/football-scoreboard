package com.sooraj.scoreboard.service;

import com.sooraj.scoreboard.domain.MatchDetails;
import com.sooraj.scoreboard.domain.ScoreUpdater;
import com.sooraj.scoreboard.exception.LiveMatchNotFoundException;
import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.domain.Team;
import com.sooraj.scoreboard.util.MatchScoreComparator;
import com.sooraj.scoreboard.util.MatchSummaryFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public abstract class ScoreBoard {
    private final List<MatchDetails> liveMatchDetails = new ArrayList<>();

    public void addMatch(Match match, ScoreUpdater scoreUpdater) {
        liveMatchDetails.add(new MatchDetails(match,scoreUpdater));
    }

    public boolean isMatchLive(String... teams) {
       Set<String> testTeamSet = Stream.of(teams).collect(toSet());
        return liveMatchDetails.stream()
                .anyMatch(match ->match.getMatch().getTeams()
                        .stream()
                        .map(Team::getName).collect(toSet())
                        .equals(testTeamSet));
    }

    public void removeMatch(Match match) {
        Optional<MatchDetails> matchDetails = liveMatchDetails
                .stream()
                .filter(l -> l.getMatch().equals(match))
                .findFirst();

        matchDetails.ifPresent(liveMatchDetails::remove);
    }

    public Match findLiveMatch(String... teams) {
        Set<String> testTeamSet = Stream.of(teams).collect(toSet());

        return liveMatchDetails.stream()
                .filter(match -> match.getMatch().getTeams().stream()
                        .map(Team::getName)
                        .collect(toSet())
                        .equals(testTeamSet))
                .findFirst()
                .orElseThrow(() -> new LiveMatchNotFoundException("Live match not found with the specified team names"))
                .getMatch();
    }

    public String getSummary() {
        return liveMatchDetails
                .stream()
                .sorted(new MatchScoreComparator(liveMatchDetails))
                .map(MatchSummaryFormatter::format).
                collect(joining("\n", "", "\n"));
    }
}