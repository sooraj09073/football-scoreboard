package com.sooraj.scoreboard.service;

import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.domain.Team;
import com.sooraj.scoreboard.validator.TeamValidator;

import java.util.List;

public abstract class TeamRegistration implements TeamValidator {
    public final void register(Match match, List<Team> teams) {
        validateTeams(teams);
        match.setTeams(teams);
    }
}
