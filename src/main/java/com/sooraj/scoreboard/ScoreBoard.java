package com.sooraj.scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public abstract class ScoreBoard {
    List<Match> matchList = new ArrayList<>();

    public void setScore(Match matchList) {
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
}
