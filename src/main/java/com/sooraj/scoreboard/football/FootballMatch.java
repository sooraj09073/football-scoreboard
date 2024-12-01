package com.sooraj.scoreboard.football;

import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.domain.ScoreUpdater;
import com.sooraj.scoreboard.service.FootballTeamRegistration;
import com.sooraj.scoreboard.validator.FootballMatchManager;

public class FootballMatch extends Match {
    public FootballMatch(FootballTeamRegistration footballTeamRegistration, FootballMatchManager footballMatchManager, ScoreUpdater scoreUpdater) {
        super(footballTeamRegistration, footballMatchManager, scoreUpdater);
    }
}
