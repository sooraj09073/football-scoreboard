package com.sooraj.scoreboard.service;

import com.sooraj.scoreboard.domain.Match;

public interface Scoring {
    void updateScore(Match match, int homeScore, int awayScore);
}
