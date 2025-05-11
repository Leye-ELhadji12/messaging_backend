package com.messaging.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Message {

    @Id @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id;

    private String content;

    @CreatedDate
    private LocalDateTime dateSentMessage;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
}
