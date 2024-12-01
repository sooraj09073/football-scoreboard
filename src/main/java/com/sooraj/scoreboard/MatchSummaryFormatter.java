package com.sooraj.scoreboard;

public class MatchSummaryFormatter {
    private MatchSummaryFormatter() {
    }

    public static String format(Match match){
        return match.getTeams().get(0).getName() + " " + match.getHomeScore() + " - "
                + match.getTeams().get(1).getName() + " " + match.getAwayScore();
    }
}
