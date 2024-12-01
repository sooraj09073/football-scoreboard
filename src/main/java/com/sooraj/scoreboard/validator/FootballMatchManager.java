package com.sooraj.scoreboard.validator;

import com.sooraj.scoreboard.domain.ScoreUpdater;
import com.sooraj.scoreboard.service.ScoreBoard;

public class FootballMatchManager extends TwoTeamMatchManager {
    public FootballMatchManager(ScoreBoard scoreBoard, ScoreUpdater scoreUpdater) {
        super(scoreBoard,scoreUpdater);
    }
}
