package com.messaging.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messaging.entity.ChatParticipants;
import com.messaging.entity.ChatRoomParticipantKey;

@Repository
public interface ChatParticipantsRepository extends JpaRepository<ChatParticipants, ChatRoomParticipantKey>{
      List<ChatParticipants> findByChatRoomId(UUID chatRoomId);
      ChatParticipants findByChatRoomIdAndUserUsername(UUID chatRoomUuid, String username);
}
