package com.messaging.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.messaging.dto.MessageDTO;
import com.messaging.entity.Message;

@Service
public class MessageMapper {

      public MessageDTO toMessageDTO(Message message) {
            MessageDTO messageDTO = new MessageDTO();
            BeanUtils.copyProperties(message, messageDTO);
            if (message.getChatRoom() != null) {
                  messageDTO.setChatRoomID(message.getChatRoom().getId());
            }
            if (message.getUser() != null) {
                  messageDTO.setSender(message.getUser().getUsername());
            }
            return messageDTO;
      }

      public Message toMessage(MessageDTO messageDTO) {
            Message message = new Message();
            BeanUtils.copyProperties(messageDTO, message);
            return message;
      }

}
