package com.messaging.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messaging.entity.Message;

public interface MessageRepository extends JpaRepository<Message, UUID>{

}
