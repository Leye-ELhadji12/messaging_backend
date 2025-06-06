package com.messaging.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.messaging.dto.ChatParticipantsDTO;
import com.messaging.entity.ChatParticipants;
import com.messaging.mapper.ChatParticipantsMapper;
import com.messaging.repository.ChatParticipantsRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatParticipantsService {

      private final ChatParticipantsMapper chatParticipantsMapper;
      private final ChatParticipantsRepository chatParticipantsRepository;

      @Transactional
      public List<ChatParticipantsDTO> getChatRoomId(UUID chatRoomUuid) {
            return chatParticipantsRepository.findByChatRoomId(chatRoomUuid)
                  .stream()
                  .map(chatParticipantsMapper::toChatParticipantsDTO)
                  .toList();
      }

      @Transactional
      public ChatParticipantsDTO updateLastSeen(UUID chatRoomUuid, String username) {
            ChatParticipants participants = chatParticipantsRepository.findByChatRoomIdAndUserUsername(chatRoomUuid, username);
            participants.setLastSeen(LocalDateTime.now());
            return chatParticipantsMapper.toChatParticipantsDTO(chatParticipantsRepository.save(participants));
      }

}
