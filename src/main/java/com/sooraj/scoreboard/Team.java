package com.sooraj.scoreboard;

public abstract class Team {
    private String name;
    protected Team(String teamName) {
        this.name = teamName;
    }

    public String getName() {
        return name;
    }
}
