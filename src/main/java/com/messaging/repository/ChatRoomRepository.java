package com.messaging.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messaging.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID>{

}
