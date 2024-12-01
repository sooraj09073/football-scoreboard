package com.sooraj.scoreboard.football;

import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.service.FootballTeamRegistration;
import com.sooraj.scoreboard.service.ScoreBoard;
import com.sooraj.scoreboard.validator.FootballMatchManager;

public class FootballMatch extends Match {
    public FootballMatch(ScoreBoard scoreBoard) {
        super(new FootballTeamRegistration(),new FootballMatchManager(scoreBoard));
    }
}
