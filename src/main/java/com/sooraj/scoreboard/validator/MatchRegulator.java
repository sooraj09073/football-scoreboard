package com.sooraj.scoreboard.validator;

import com.sooraj.scoreboard.domain.Match;

public interface MatchRegulator {
    boolean canStart(Match match);
    void start(Match match);
    void finish(Match match);
    void updateScore(Match match, int homeScore, int awayScore);
}
