package com.sooraj.scoreboard.football;

import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.ScoreBoard;
import com.sooraj.scoreboard.ScoreManager;
import com.sooraj.scoreboard.TeamRegistration;

public class FootballMatch extends Match {
    public FootballMatch(ScoreBoard scoreBoard) {
        super(scoreBoard, new FootballTeamValidator(), new TeamRegistration(), new ScoreManager());
    }
}
