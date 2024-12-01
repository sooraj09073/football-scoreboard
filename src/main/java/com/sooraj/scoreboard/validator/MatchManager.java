package com.sooraj.scoreboard.validator;

import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.domain.ScoreUpdater;
import com.sooraj.scoreboard.exception.MatchStateException;
import com.sooraj.scoreboard.service.ScoreBoard;

public abstract class MatchManager implements MatchRegulator {
    private final ScoreBoard scoreBoard;
    private final ScoreUpdater scoreUpdater;

    protected MatchManager(ScoreBoard scoreBoard, ScoreUpdater scoreUpdater) {
        this.scoreBoard = scoreBoard;
        this.scoreUpdater = scoreUpdater;
    }

    @Override
    public void start(Match match) {
        if(!canStart(match)){
            throw new MatchStateException("Match cannot be started");
        }
        scoreUpdater.setHomeScore(0);
        scoreUpdater.setAwayScore(0);
        scoreBoard.addMatch(match,scoreUpdater);
        match.hasStarted(true);
    }

    @Override
    public void finish(Match match) {
        scoreBoard.removeMatch(match);
    }

    @Override
    public void updateScore(Match match, int homeScore, int awayScore) {
            if(homeScore<0 || awayScore<0) {
                throw new IllegalArgumentException("Invalid score");
            }
        scoreUpdater.setHomeScore(homeScore);
        scoreUpdater.setAwayScore(awayScore);
    }
}
