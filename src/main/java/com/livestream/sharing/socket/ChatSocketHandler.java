package com.livestream.sharing.socket;

import com.google.gson.Gson;
import com.livestream.sharing.data.Chat;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/signal/ws/chat")
public class ChatSocketHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ChatSocketHandler.class);
    private static final Gson GSON = new Gson();
    private Map<String, Session> clients = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        LOG.info("Session id {}", session.getId());
        clients.put(session.getId(), session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Chat chatData = GSON.fromJson(message, Chat.class);
        String sessionId = session.getId();
        String clientMessage = chatData.getMessage();
        LOG.info("Session ID {} and message {}", sessionId, clientMessage);
        session.getBasicRemote().sendText("Echo: " + message);
    }

    @OnClose
    public void onClose(CloseReason closeReason) {

    }

    @OnError
    public void onError(Throwable t) {

    }
}
