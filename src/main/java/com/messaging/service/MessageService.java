package com.messaging.service;

import org.springframework.stereotype.Service;

import com.messaging.mapper.MessageMapper;
import com.messaging.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
      private final MessageMapper messageMapper;
      private final MessageRepository messageRepository;

      
}
