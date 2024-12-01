package com.sooraj.scoreboard.service;

import com.sooraj.scoreboard.Scoring;
import com.sooraj.scoreboard.domain.Match;

public class ScoreManager implements Scoring {
    public void updateScore(Match match, int homeScore, int awayScore) {
        if(homeScore<0 || awayScore<0) {
            throw new IllegalArgumentException("Invalid score");
        }
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
    }
}
