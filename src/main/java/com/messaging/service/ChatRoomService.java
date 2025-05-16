// package com.messaging.service;

// import java.util.List;
// import java.util.UUID;

// import org.springframework.stereotype.Service;

// import com.messaging.dto.ChatRoomDTO;
// import com.messaging.entity.ChatRoom;
// import com.messaging.mapper.ChatParticipantsMapper;
// import com.messaging.mapper.ChatRoomMapper;
// import com.messaging.repository.ChatRoomRepository;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class ChatRoomService {

//       private final ChatRoomMapper chatRoomMapper;
//       private final ChatRoomRepository chatRoomRepository;

//       public ChatRoomDTO findChatRoomByParticipants(List<String> participants) {
//             ChatRoom chatRoom = chatRoomRepository.findChatRoomByParticipants(participants, (long) participants.size());
//             return chatRoom != null ? chatRoomMapper.toChatRoomDTO(chatRoom) : null;
//       }

//       public ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDTO) {
//             ChatRoom chatRoom = chatRoomMapper.tChatRoom(chatRoomDTO);
//             ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
//             return chatRoomMapper.toChatRoomDTO(savedChatRoom);
//       }

//       public ChatRoomDTO getChatRoomById(UUID id) {
//             return chatRoomRepository.findById(id)
//                   .map(chatRoomMapper::toChatRoomDTO)
//                   .orElseThrow(() -> new RuntimeException("Chat room not found"));
//       }

//       public List<ChatRoomDTO> getAllChatRooms() {
//             return chatRoomRepository.findAll()
//                   .stream()
//                   .map(chatRoomMapper::toChatRoomDTO)
//                   .toList();
//       }

//       public void deleteChatRoom(UUID id) {
//             chatRoomRepository.deleteById(id);
//       }

//       public ChatRoomDTO updateChatRoom(UUID id, ChatRoomDTO chatRoomDTO) {
//             return chatRoomRepository.findById(id)
//                   .map(existingChatRoom -> {
//                         ChatRoom updatedChatRoom = chatRoomMapper.tChatRoom(chatRoomDTO);
//                         updatedChatRoom.setId(id);
//                         ChatRoom savedChatRoom = chatRoomRepository.save(updatedChatRoom);
//                         return chatRoomMapper.toChatRoomDTO(savedChatRoom);
//                   })
//                   .orElseThrow(() -> new RuntimeException("Chat room not found"));
//       }

//       public List<ChatRoomDTO> getChatRoomsByUser(String username) {
//             return chatRoomRepository.findChatRoomsByParticipant(username)
//                   .stream()
//                   .map(chatRoomMapper::toChatRoomDTO)
//                   .toList();
//       }

//       public List<ChatRoomDTO> getGroupChatRooms() {
//             return chatRoomRepository.findAllGroupChatRooms()
//                   .stream()
//                   .map(chatRoomMapper::toChatRoomDTO)
//                   .toList();
//       }

//       public List<ChatRoomDTO> getPrivateChatRooms() {
//             return chatRoomRepository.findAllPrivateChatRooms()
//                   .stream()
//                   .map(chatRoomMapper::toChatRoomDTO)
//                   .toList();
//       }

//       public List<ChatRoomDTO> getChatRoomsCreatedByUser(String username) {
//             return chatRoomRepository.findByCreatedByUsername(username)
//                   .stream()
//                   .map(chatRoomMapper::toChatRoomDTO)
//                   .toList();
//       }

//       public List<ChatRoomDTO> getMessagesWithContent(String username) {
//             return chatRoomRepository.findMessageAtLeastOneContent(username)
//                   .stream()
//                   .map(chatRoomMapper::toChatRoomDTO)
//                   .toList();
//       }
// }


package com.messaging.service;

import java.util.List;
import java.util.UUID;
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

    public ChatRoomDTO findChatRoomByMembers(List<String> members) {
        return chatRoomRepository.findChatRoomByParticipants(members, members.size())
                .map(chatRoomMapper::toChatRoomDTO)
                .orElse(null);
    }

    @Transactional
    public ChatRoomDTO createChatRoom(List<String> members, String username) {
        final User user = userRepository.findById(username).orElseThrow();

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