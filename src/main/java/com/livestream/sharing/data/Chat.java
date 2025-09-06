package com.livestream.sharing.data;

public class Chat {

    private final MessageType messageType;
    private final String roomNumber;
    private final String message;

    public Chat(MessageType messageType, String roomNumber, String message) {
        this.messageType = messageType;
        this.roomNumber = roomNumber;
        this.message = message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getMessage() {
        return message;
    }
}
