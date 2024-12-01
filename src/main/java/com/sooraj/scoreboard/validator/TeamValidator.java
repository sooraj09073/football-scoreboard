package com.sooraj.scoreboard.validator;

import com.sooraj.scoreboard.domain.Team;

import java.util.List;

public interface TeamValidator {
    void validateTeams(List<Team> teams);
}
