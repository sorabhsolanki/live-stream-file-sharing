package com.livestream.sharing.service;

import jakarta.websocket.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChatRoomService {

    private final InMemorySocket inMemorySocket;

    public ChatRoomService(InMemorySocket inMemorySocket) {
        this.inMemorySocket = inMemorySocket;
    }

    public void addUserInRoom(String room, Session session) {
        inMemorySocket.putUserToRoom(room, session.getId());
        inMemorySocket.putSessionIdAndSession(session.getId(), session);
    }

    public List<Session> sessionListForbroadcastMessage(String sessionId) {
        Set<String> sessionIDs = inMemorySocket.getAllSessionIdsForChat(sessionId);
        List<Session> sessionList = new ArrayList<>();
        for (String id : sessionIDs) {
            sessionList.add(inMemorySocket.getSessionForSessionID(id));
        }
        return sessionList;
    }
}
