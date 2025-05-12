package com.messaging.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.messaging.entity.ChatParticipants;
import com.messaging.entity.ChatRoomParticipantKey;

public interface ChatParticipantsRepository extends JpaRepository<ChatParticipants, ChatRoomParticipantKey>{

}
