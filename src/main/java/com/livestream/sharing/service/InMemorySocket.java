package com.livestream.sharing.service;

import jakarta.websocket.Session;

import java.util.*;

public class InMemorySocket {

    private final Map<String, String> sessionIDToRoom;
    private final Map<String, Set<String>> roomToSessionIds;
    private final Map<String, Session> sessionIdToSession;


    public InMemorySocket(Map<String, String> sessionIDToRoom, Map<String, Set<String>> roomToSessionIds, Map<String, Session> sessionIdToSession) {
        this.sessionIDToRoom = sessionIDToRoom;
        this.roomToSessionIds = roomToSessionIds;
        this.sessionIdToSession = sessionIdToSession;
    }

    public Set<String> getAllSessionIdsForChat(String sessionID) {
        String room = sessionIDToRoom.get(sessionID);
        return roomToSessionIds.get(room);
    }

    public Session getSessionForSessionID(String sessionID) {
        return sessionIdToSession.get(sessionID);
    }

    public void putUserToRoom(String room, String sessionID) {
        sessionIDToRoom.put(sessionID, room);
        Set<String> sessionIds = roomToSessionIds.computeIfAbsent(room, r -> new HashSet<>());
        sessionIds.add(sessionID);
    }

    public void putSessionIdAndSession(String sessionId, Session session) {
        if (!sessionIdToSession.containsKey(sessionId)) {
            sessionIdToSession.put(sessionId, session);
        }
    }
}
