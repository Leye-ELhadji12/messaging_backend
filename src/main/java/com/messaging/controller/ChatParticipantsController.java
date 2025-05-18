package com.messaging.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messaging.repository.ChatParticipantsRepository;
import com.messaging.service.ChatParticipantsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/chatparticipants")
@RequiredArgsConstructor
public class ChatParticipantsController {

      private final ChatParticipantsRepository chatParticipantsRepository;
      private final ChatParticipantsService chatParticipantsService;

}
