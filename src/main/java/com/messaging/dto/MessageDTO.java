package com.messaging.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.messaging.entity.MessageType;

import lombok.Data;

@Data
public class MessageDTO {
      private UUID id;
      private String content;
      private LocalDateTime dateSentMessage;
      private MessageType messageType;
      private UUID chatRoomID;
      private String sender;
}
