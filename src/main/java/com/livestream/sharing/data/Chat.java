package com.livestream.sharing.data;

public class Chat {

    private final String sessionId;
    private final String message;

    public Chat(String sessionId, String message) {
        this.sessionId = sessionId;
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getMessage() {
        return message;
    }
}
