package com.livestream.sharing.socket;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/signal/ws/share")
public class SignalSocketHandler {

    @OnOpen
    public void onOpen(Session session) {

    }

    @OnMessage
    public void onMessage(String message) {

    }

    @OnClose
    public void onClose(CloseReason closeReason) {

    }

    @OnError
    public void onError(Throwable t) {

    }
}
