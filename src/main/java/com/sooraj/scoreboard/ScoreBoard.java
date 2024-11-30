package com.sooraj.scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public abstract class ScoreBoard {
    private final List<Match> matchList = new ArrayList<>();

    public void addMatch(Match matchList) {
        this.matchList.add(matchList);
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public boolean isMatchLive(String... teams) {
       Set<String> testTeamSet = Stream.of(teams).collect(toSet());
        return matchList.stream()
                .anyMatch(match -> {
                    Set<String> teamSet = match.getTeams()
                            .stream()
                            .map(Team::getName).collect(toSet());
                    return teamSet.equals(testTeamSet);
                });
    }

    public void removeMatch(Match match) {
        matchList.remove(match);
    }

    public Match findLiveMatch(String... teams) {
        Set<String> testTeamSet = Stream.of(teams).collect(toSet());
        return matchList.stream()
                .filter(match -> {
                    Set<String> matchTeamNames = match.getTeams().stream()
                            .map(Team::getName)
                            .collect(Collectors.toSet());
                    // Check if the team names match exactly
                    return matchTeamNames.equals(testTeamSet);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Live match not found with the specified team names"));
    }

    public String getSummary() {
        AtomicInteger counter = new AtomicInteger(0);
        return matchList
                .stream()
                .sorted((m1,m2)->{
                    int sum1 = m1.getHomeScore() + m1.getAwayScore();
                    int sum2 = m2.getHomeScore() + m2.getAwayScore();
                    return (sum1==sum2)?
                            Integer.compare(matchList.lastIndexOf(m2), matchList.lastIndexOf(m1))
                            :Integer.compare(sum2, sum1);
                })
                .map(match-> counter.incrementAndGet() + ". " + match.getTeams().get(0).getName() + " " +match.getHomeScore() + " - "
                        + match.getTeams().get(1).getName() + " " + match.getAwayScore()
                ).collect(Collectors.joining("\n", "", "\n"));
    }
}
