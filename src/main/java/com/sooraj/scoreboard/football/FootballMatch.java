package com.sooraj.scoreboard.football;

import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.service.ScoreBoard;
import com.sooraj.scoreboard.service.ScoreManager;
import com.sooraj.scoreboard.service.TeamRegistration;
import com.sooraj.scoreboard.validator.FootballTeamValidator;

public class FootballMatch extends Match {
    public FootballMatch(ScoreBoard scoreBoard) {
        super(scoreBoard, new FootballTeamValidator(), new TeamRegistration(), new ScoreManager());
    }
}
