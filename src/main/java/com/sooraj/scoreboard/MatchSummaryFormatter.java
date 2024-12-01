package com.sooraj.scoreboard;

import com.sooraj.scoreboard.domain.Match;

public class MatchSummaryFormatter {
    private MatchSummaryFormatter() {
    }

    public static String format(Match match){
        return match.getTeams().get(0).getName() + " " + match.getHomeScore() + " - "
                + match.getTeams().get(1).getName() + " " + match.getAwayScore();
    }
}
