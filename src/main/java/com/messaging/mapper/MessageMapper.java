package com.messaging.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.messaging.dto.MessageDTO;
import com.messaging.entity.ChatRoom;
import com.messaging.entity.Message;
import com.messaging.entity.User;

@Service
public class MessageMapper {

      public MessageDTO toMessageDTO(Message message) {
            MessageDTO messageDTO = new MessageDTO();
            BeanUtils.copyProperties(message, messageDTO);
            messageDTO.setChatRoomID(message.getChatRoom().getId());
            messageDTO.setSender(message.getUser().getUsername());
            return messageDTO;
      }

      public Message toMessage(MessageDTO messageDTO) {
            Message message = new Message();
            BeanUtils.copyProperties(messageDTO, message);
            if (messageDTO.getChatRoomID() != null) {
                  ChatRoom chatRoom = new ChatRoom();
                  chatRoom.setId(messageDTO.getChatRoomID());
                  message.setChatRoom(chatRoom);
            }
            if (messageDTO.getSender() != null) {
                  User user = new User();
                  user.setUsername(messageDTO.getSender());
                  message.setUser(user);
            }
            return message;
      }

}
