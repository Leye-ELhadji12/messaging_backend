package com.messaging.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messaging.dto.MessageDTO;
import com.messaging.service.MessageService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
      private final MessageService messageService;

      @PostMapping("/save")
      public ResponseEntity<MessageDTO> saveMessage(@RequestBody MessageDTO message) {
            return ResponseEntity.status(HttpStatus.CREATED).body(messageService.saveMessage(message));
      }

      @GetMapping
      public ResponseEntity<List<MessageDTO>> getMessagesByChatRoom(@RequestParam UUID chatRoomId) {
            return ResponseEntity.ok(messageService.messagesByChatRoom(chatRoomId));
      }

      @GetMapping("/{chatRoomId}")
      public ResponseEntity<MessageDTO> getLastMessage(@RequestParam UUID chatRoomId) {
            return ResponseEntity.ok(messageService.lastMessage(chatRoomId));
      }

}
