package com.sooraj.scoreboard.util;

import com.sooraj.scoreboard.domain.MatchDetails;

import java.util.Comparator;
import java.util.List;

public class MatchScoreComparator implements Comparator<MatchDetails> {
    private final List<MatchDetails> matchDetailsList;

    public MatchScoreComparator(List<MatchDetails> matchDetailsList) {
        this.matchDetailsList = matchDetailsList;
    }

    @Override
    public int compare(MatchDetails m1, MatchDetails m2) {
        int sum1 = m1.getScoreUpdater().getHomeScore() + m1.getScoreUpdater().getAwayScore();
        int sum2 = m2.getScoreUpdater().getHomeScore() + m2.getScoreUpdater().getAwayScore();
        return (sum1 == sum2)
                ? Integer.compare(matchDetailsList.lastIndexOf(m2), matchDetailsList.lastIndexOf(m1))
                : Integer.compare(sum2, sum1);
    }
}
