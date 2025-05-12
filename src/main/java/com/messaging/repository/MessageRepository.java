package com.messaging.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messaging.entity.Message;

public interface MessageRepository extends JpaRepository<Message, UUID>{
      /**
       * Finds the last message sent in a chat room.
       * 
       * @param chatRoomId the ID of the chat room
       * @return the last message sent in the chat room, or null if no messages exist
       */
      Optional<Message> findTopByChatRoomIdOrderByDateSentMessageDesc(UUID chatRoomId);
}
