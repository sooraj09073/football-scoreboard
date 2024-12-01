package com.sooraj.scoreboard;

import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.domain.Team;
import com.sooraj.scoreboard.util.MatchScoreComparator;
import com.sooraj.scoreboard.util.MatchSummaryFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public abstract class ScoreBoard {
    private final List<Match> matchList = new ArrayList<>();

    public void addMatch(Match matchList) {
        this.matchList.add(matchList);
    }

    public List<Match> getMatchList() {
        return Collections.unmodifiableList(matchList);
    }

    public boolean isMatchLive(String... teams) {
       Set<String> testTeamSet = Stream.of(teams).collect(toSet());
        return matchList.stream()
                .anyMatch(match ->match.getTeams()
                        .stream()
                        .map(Team::getName).collect(toSet())
                        .equals(testTeamSet));
    }

    public void removeMatch(Match match) {
        matchList.remove(match);
    }

    public Match findLiveMatch(String... teams) {
        Set<String> testTeamSet = Stream.of(teams).collect(toSet());

        return matchList.stream()
                .filter(match -> match.getTeams().stream()
                        .map(Team::getName)
                        .collect(toSet())
                        .equals(testTeamSet))
                .findFirst()
                .orElseThrow(() -> new LiveMatchNotFoundException("Live match not found with the specified team names"));
    }

    public String getSummary() {
        return matchList
                .stream()
                .sorted(new MatchScoreComparator(matchList))
                .map(MatchSummaryFormatter::format).
                collect(joining("\n", "", "\n"));
    }
}