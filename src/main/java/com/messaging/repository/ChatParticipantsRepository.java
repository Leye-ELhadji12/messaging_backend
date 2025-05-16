package com.messaging.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messaging.entity.ChatParticipants;
import com.messaging.entity.ChatRoomParticipantKey;

public interface ChatParticipantsRepository extends JpaRepository<ChatParticipants, ChatRoomParticipantKey>{
      List<ChatParticipants> findByChatRoomId(UUID chatRoomId);
      ChatParticipants findByChatRoomIdAndUsername(UUID chatRoomUuid, String username);
}
