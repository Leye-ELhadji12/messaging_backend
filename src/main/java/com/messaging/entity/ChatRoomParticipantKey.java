package com.messaging.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatRoomParticipantKey implements Serializable {
    private ChatRoom chatRoom;
    private User user;
}
