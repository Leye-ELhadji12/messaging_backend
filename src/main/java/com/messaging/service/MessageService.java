package com.messaging.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.messaging.dto.MessageDTO;
import com.messaging.entity.Message;
import com.messaging.mapper.MessageMapper;
import com.messaging.repository.MessageRepository;

import jakarta.transaction.Transactional;

import com.messaging.exception.MessageNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
      private final MessageMapper messageMapper;
      private final MessageRepository messageRepository;

      @Transactional
      public MessageDTO lastMessage(UUID chatRoomUuid) {
            Optional<Message> lastmessage = messageRepository.findTopByChatRoomIdOrderByDateSentMessageDesc(chatRoomUuid);
            return lastmessage.map(messageMapper::toMessageDTO)
                        .orElseThrow(() -> new MessageNotFoundException(chatRoomUuid +" not found"));
      }

      @Transactional
      public List<MessageDTO> messagesByChatRoom(UUID chatRoomId) {
            List<MessageDTO> messagesRoom = messageRepository.findById(chatRoomId).stream()
                        .map(messageMapper::toMessageDTO)
                        .toList();
            return messagesRoom;
      }

      @Transactional
      public MessageDTO saveMessage(MessageDTO messageDTO) {
            Message message = messageMapper.toMessage(messageDTO);
            return messageMapper.toMessageDTO(messageRepository.save(message));
      }
}
