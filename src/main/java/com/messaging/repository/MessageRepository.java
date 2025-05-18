package com.messaging.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messaging.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID>{
      Optional<Message> findTopByChatRoomIdOrderByDateSentMessageDesc(UUID chatRoomId);
}
