package com.sooraj.scoreboard.domain;

public interface ScoreUpdater {
    int getHomeScore();

    void setHomeScore(int homeScore);

    int getAwayScore();

    void setAwayScore(int awayScore);
}
