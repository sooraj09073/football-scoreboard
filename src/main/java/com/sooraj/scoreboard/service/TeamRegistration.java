package com.sooraj.scoreboard.service;

import com.sooraj.scoreboard.domain.Team;
import com.sooraj.scoreboard.validator.TeamValidator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public abstract class TeamRegistration implements TeamValidator {
    private List<Team> teams;

    public final void register(List<Team> teams) {
        validateTeams(teams);
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return Optional.ofNullable(teams)
                .map(Collections::unmodifiableList)
                .orElse(emptyList());
    }
}
