package com.sooraj.scoreboard.validator;

import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.service.ScoreBoard;

public abstract class MatchManager implements MatchRegulator {
    private final ScoreBoard scoreBoard;

    protected MatchManager(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    @Override
    public void start(Match match) {
        if(!canStart(match)){
            throw new IllegalStateException("Match cannot be started");
        }
        match.setHomeScore(0);
        match.setAwayScore(0);
        scoreBoard.addMatch(match);
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
            match.setHomeScore(homeScore);
            match.setAwayScore(awayScore);
    }
}