package com.sooraj.scoreboard.validator;

import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.domain.Team;
import com.sooraj.scoreboard.exception.MatchStateException;
import com.sooraj.scoreboard.service.ScoreBoard;

public class TwoTeamMatchManager extends MatchManager{

    protected TwoTeamMatchManager(ScoreBoard scoreBoard) {
        super(scoreBoard);
    }

    @Override
    public boolean canStart(Match match) {
        if(match.getTeams() != null && match.getTeams().size() != 2){
            throw new MatchStateException("Cannot start the match: Teams registration is not complete.");
        } else if(match.hasStarted()){
            throw new MatchStateException("Cannot start the match: Match has already started.");
        }else if(!match.getTeams().stream().allMatch(Team::isReady)){
            throw new MatchStateException("Cannot start the match: Teams are not ready.");
        }
        return true;
    }
}
