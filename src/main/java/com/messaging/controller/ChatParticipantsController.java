package com.messaging.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messaging.dto.ChatParticipantsDTO;
import com.messaging.service.ChatParticipantsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/chatparticipants")
@RequiredArgsConstructor
public class ChatParticipantsController {

      private final ChatParticipantsService chatParticipantsService;

      @PostMapping("/update-last-seen/{chatroomId}/{username}")
      public ResponseEntity<ChatParticipantsDTO> updateLastSeen(@PathVariable UUID chatroomId,
                                                            @PathVariable String username) {
            return ResponseEntity.ok(chatParticipantsService.updateLastSeen(chatroomId, username));
      }

}
