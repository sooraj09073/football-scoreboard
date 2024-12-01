package com.sooraj.scoreboard.domain;

public abstract class Team {
    private final String name;
    protected Team(String teamName) {
        this.name = teamName;
    }

    public String getName() {
        return name;
    }
}
