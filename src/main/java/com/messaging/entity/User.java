package com.messaging.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Builder @Table(name = "_user")
public class User {

    @Id
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime lastLogin = LocalDateTime.now();

    @OneToMany(mappedBy = "createdBy")
    private List<ChatRoom> chatRooms;

    @OneToMany(mappedBy = "user")
    private List<ChatParticipants> chatParticipants;
}
