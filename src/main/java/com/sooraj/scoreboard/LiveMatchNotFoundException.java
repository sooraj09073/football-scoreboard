package com.sooraj.scoreboard;

public class LiveMatchNotFoundException extends RuntimeException {
    public LiveMatchNotFoundException(String message) {
        super(message);
    }
}
