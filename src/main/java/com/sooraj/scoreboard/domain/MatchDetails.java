package com.sooraj.scoreboard.domain;

public class MatchDetails {
    private Match match;
    private final ScoreUpdater scoreUpdater;

    public MatchDetails(Match match, ScoreUpdater scoreUpdater) {
        this.scoreUpdater = scoreUpdater;
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }

    public ScoreUpdater getScoreUpdater() {
        return scoreUpdater;
    }
}
