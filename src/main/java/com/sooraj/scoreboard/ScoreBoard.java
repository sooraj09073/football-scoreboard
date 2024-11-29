package com.sooraj.scoreboard;

import java.util.ArrayList;
import java.util.List;

public abstract class ScoreBoard {
    List<Match> matchList = new ArrayList<>();

    public void setScore(Match matchList) {
        this.matchList.add(matchList);
    }

    public List<Match> getMatchList() {
        return matchList;
    }
}
