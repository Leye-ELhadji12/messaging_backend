package com.messaging.mapper;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.messaging.dto.ChatParticipantsDTO;
import com.messaging.dto.ChatRoomDTO;
import com.messaging.entity.ChatRoom;
import com.messaging.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomMapper {

      private final ChatParticipantsMapper chatParticipantsMapper;

      public ChatRoomDTO toChatRoomDTO(ChatRoom chatRoom) {
            ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
            BeanUtils.copyProperties(chatRoom, chatRoomDTO);
            chatRoomDTO.setCreatedById(chatRoom.getCreatedBy().getUsername());
            List<ChatParticipantsDTO> participants = chatRoom.getParticipants().stream()
                              .map(chatParticipantsMapper::toChatParticipantsDTO)
                              .toList();
            chatRoomDTO.setParticipants(participants);
            return chatRoomDTO;
      }

      public ChatRoom tChatRoom(ChatRoomDTO chatRoomDTO) {
            ChatRoom chatRoom = new ChatRoom();
            BeanUtils.copyProperties(chatRoomDTO, chatRoom);
            User user = new User();
            user.setUsername(chatRoomDTO.getCreatedById());
            chatRoom.setCreatedBy(user);
            List<ChatParticipantsDTO> participants = chatRoom.getParticipants().stream()
                  .map(chatParticipantsMapper::toChatParticipantsDTO)
                  .toList();
            chatRoomDTO.setParticipants(participants);
            return chatRoom;
      }
}
