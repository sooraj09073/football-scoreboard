package com.sooraj.scoreboard;

import java.util.List;

public class TeamRegistration {
    public void register(Match match, List<Team> teams) {
        match.validateTeams(teams);
        match.setTeams(teams);
    }
}
