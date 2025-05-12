package com.messaging.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.messaging.dto.ChatParticipantsDTO;
import com.messaging.entity.ChatParticipants;
import com.messaging.entity.ChatRoom;
import com.messaging.entity.User;

@Service
public class ChatParticipantsMapper {

      public ChatParticipantsDTO toChatParticipantsDTO(ChatParticipants chatParticipants) {
            ChatParticipantsDTO chatParticipantsDTO = new ChatParticipantsDTO();
            BeanUtils.copyProperties(chatParticipants, chatParticipantsDTO);
            chatParticipantsDTO.setChatRoom(chatParticipants.getChatRoom().getId());
            chatParticipantsDTO.setParticipant(chatParticipants.getUser().getUsername());
            chatParticipantsDTO.setLastLogin(chatParticipants.getUser().getLastLogin());
            return chatParticipantsDTO;
      }

      public ChatParticipants toChatParticipants(ChatParticipantsDTO chatParticipantsDTO) {
            ChatParticipants participants = new ChatParticipants();
            BeanUtils.copyProperties(chatParticipantsDTO, participants);
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setId(chatParticipantsDTO.getChatRoom());
            participants.setChatRoom(chatRoom);
            User user = new User();
            user.setUsername(chatParticipantsDTO.getParticipant());
            participants.setUser(user);
            return participants;
      }
}
