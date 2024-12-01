package com.sooraj.scoreboard.domain;

public abstract class Team {
    private final String name;
    private boolean isReady;
    protected Team(String teamName) {
        this.name = teamName;
    }

    public String getName() {
        return name;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
