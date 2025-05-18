package com.messaging.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.messaging.dto.ChatRoomDTO;
import com.messaging.service.ChatRoomService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/v1/chatrooms")
@RequiredArgsConstructor
public class ChatRoomController {

      private final ChatRoomService chatRoomService;

      @PostMapping("/create-chatroom")
      public ResponseEntity<ChatRoomDTO> createChatRoom(@RequestParam List<String> participants, @RequestParam String username) {
            return ResponseEntity.status(HttpStatus.CREATED).body(chatRoomService.createChatRoom(participants, username));
      }

      @GetMapping("/chatroom")
      public ResponseEntity<ChatRoomDTO> getChatRoomByMembers(@RequestParam List<String> participants) {
            return ResponseEntity.ok(chatRoomService.findChatRoomByParticipants(participants));
      }

      @GetMapping("/{chatroomId}")
      public ResponseEntity<ChatRoomDTO> findChatRoomById(@PathVariable UUID chatroomId) {
            return ResponseEntity.ok(chatRoomService.findById(chatroomId)) ;
      }

      @GetMapping("/chatroom-least-one-content/{username}")
      public ResponseEntity<List<ChatRoomDTO>> getChatRoomLeastOneContent(@RequestParam String username) {
            return ResponseEntity.ok(chatRoomService.findChatRoomAtLeastOneContent(username));
      }
}
