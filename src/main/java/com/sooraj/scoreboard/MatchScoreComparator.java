package com.sooraj.scoreboard;

import java.util.Comparator;
import java.util.List;

public class MatchScoreComparator implements Comparator<Match> {
    private final List<Match> matchList;

    public MatchScoreComparator(List<Match> matchList) {
        this.matchList = matchList;
    }

    @Override
    public int compare(Match m1, Match m2) {
        int sum1 = m1.getHomeScore() + m1.getAwayScore();
        int sum2 = m2.getHomeScore() + m2.getAwayScore();
        return (sum1 == sum2)
                ? Integer.compare(matchList.lastIndexOf(m2), matchList.lastIndexOf(m1))
                : Integer.compare(sum2, sum1);
    }
}
