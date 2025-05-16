package com.messaging.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ChatRoomDTO {
      private UUID id;
      private String name;
      private Boolean isGroup;
      private LocalDateTime createdDate; 
      private String createdById;
      private List<ChatParticipantsDTO> participants;
      private MessageDTO messages;
}
