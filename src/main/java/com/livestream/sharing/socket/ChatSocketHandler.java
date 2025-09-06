package com.livestream.sharing.socket;

import com.google.gson.Gson;
import com.livestream.sharing.data.Chat;
import com.livestream.sharing.service.ChatRoomService;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@ServerEndpoint(value = "/signal/ws/chat")
public class ChatSocketHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ChatSocketHandler.class);
    private static final Gson GSON = new Gson();

    private final ChatRoomService chatRoomService;

    public ChatSocketHandler(final ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @OnOpen
    public void onOpen(Session session) {
        LOG.info("Session id {}", session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Chat chatData = GSON.fromJson(message, Chat.class);
        switch (chatData.getMessageType()) {
            case ROOM_ALLOCATION -> chatRoomService.addUserInRoom(chatData.getRoomNumber(), session);
            case CHAT_MESSAGE -> {
                String sessionId = session.getId();
                String clientMessage = chatData.getMessage();
                LOG.info("Session ID {} and message {}", sessionId, clientMessage);
                List<Session> sessionList = chatRoomService.sessionListForbroadcastMessage(sessionId);
                for (Session ss : sessionList) {
                    ss.getBasicRemote().sendText(clientMessage);
                }
            }
        }
    }

    @OnClose
    public void onClose(CloseReason closeReason) {

    }

    @OnError
    public void onError(Throwable t) {

    }
}
