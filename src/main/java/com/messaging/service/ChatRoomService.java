package com.messaging.service;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.messaging.dto.ChatRoomDTO;
import com.messaging.dto.MessageDTO;
import com.messaging.dto.ChatParticipantsDTO;
import com.messaging.entity.ChatRoom;
import com.messaging.entity.ChatParticipants;
import com.messaging.entity.Message;
import com.messaging.entity.User;
import com.messaging.exception.ChatRoomNotFoundException;
import com.messaging.exception.UserNotFoundException;
import com.messaging.mapper.ChatRoomMapper;
import com.messaging.repository.ChatRoomRepository;
import com.messaging.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMapper chatRoomMapper;
    private final UserRepository userRepository;
    private final MessageService messageService;
    private final ChatParticipantsService chatParticipantsService;

    @Transactional
    public ChatRoomDTO findChatRoomByMembers(List<String> members) {
        return chatRoomRepository.findChatRoomByParticipants(members, members.size())
                .map(chatRoomMapper::toChatRoomDTO)
                .orElseThrow(() -> new ChatRoomNotFoundException(members + " not exits"));
    }

    @Transactional
    public ChatRoomDTO createChatRoom(List<String> members, String username) {
        final User user = userRepository.findById(username)
                            .orElseThrow(() -> new UserNotFoundException(username + " not exits"));

        ChatRoom chatRoom = ChatRoom.builder()
                .isGroup(members.size() > 2)
                .createdBy(user)
                .participants(new ArrayList<>())
                .messages(new ArrayList<>())
                .build();

        final List<User> users = userRepository.findAllByUsernameIn(members);

        users.forEach(u -> {
            final ChatParticipants participant = ChatParticipants.builder()
                    .chatRoom(chatRoom)
                    .user(u)
                    .isAdmin(u.getUsername().equals(username))
                    .lastSeen(LocalDateTime.now())
                    .build();
            chatRoom.getParticipants().add(participant);
        });

        Message message = Message.builder()
                .content("Hi")
                .dateSentMessage(LocalDateTime.now())
                .chatRoom(chatRoom)
                .user(user)
                .build();

        chatRoom.getMessages().add(message);

        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

        return chatRoomMapper.toChatRoomDTO(savedChatRoom);
    }

    @Transactional
    public List<ChatRoomDTO> findChatRoomAtLeastOneContent(final String username) {
        return chatRoomRepository.findChatRoomAtLeastOneContent(username)
                .stream()
                .map(chatRoom -> {
                    final ChatRoomDTO chatRoomDTO = chatRoomMapper.toChatRoomDTO(chatRoom);
                    final MessageDTO lastMessage = messageService.lastMessage(chatRoom.getId());
                    chatRoomDTO.setMessages(lastMessage);
                    final List<ChatParticipantsDTO> participants = chatParticipantsService.getChatRoomId(chatRoom.getId());
                    chatRoomDTO.setParticipants(participants);
                    return chatRoomDTO;
                })
                .toList();
    }
}