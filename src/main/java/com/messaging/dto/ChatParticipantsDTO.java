package com.messaging.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class ChatParticipantsDTO {
      private UUID chatRoom;
      private String participant;
      private Boolean isAdmin;
      private LocalDateTime lastSeen;
      private LocalDateTime lastLogin;
}
