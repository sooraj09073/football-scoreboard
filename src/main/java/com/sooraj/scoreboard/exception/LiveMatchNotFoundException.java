package com.sooraj.scoreboard.exception;

public class LiveMatchNotFoundException extends RuntimeException {
    public LiveMatchNotFoundException(String message) {
        super(message);
    }
}
