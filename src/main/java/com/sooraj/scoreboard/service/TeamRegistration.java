package com.sooraj.scoreboard.service;

import com.sooraj.scoreboard.domain.Match;
import com.sooraj.scoreboard.domain.Team;

import java.util.List;

public class TeamRegistration {
    public void register(Match match, List<Team> teams) {
        match.validateTeams(teams);
        match.setTeams(teams);
    }
}
