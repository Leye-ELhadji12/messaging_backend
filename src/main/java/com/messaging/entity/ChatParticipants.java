package com.messaging.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ChatRoomParticipantKey.class)
@Builder
public class ChatParticipants {

    @Id
    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @Id
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    private Boolean isAdmin;

    private LocalDateTime lastSeen;
}
