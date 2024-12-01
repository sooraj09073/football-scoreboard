package com.sooraj.scoreboard.util;

import com.sooraj.scoreboard.domain.MatchDetails;

public class MatchSummaryFormatter {
    private MatchSummaryFormatter() {
    }

    public static String format(MatchDetails matchDetails) {
        return matchDetails.getMatch().getTeams().get(0).getName() + " " + matchDetails.getScoreUpdater().getHomeScore() + " - "
                + matchDetails.getMatch().getTeams().get(1).getName() + " " + matchDetails.getScoreUpdater().getAwayScore();
    }
}
