package com.messaging.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.messaging.dto.UserDTO;
import com.messaging.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

      private final UserService userService;

      @PostMapping("/registration")
      public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDTO));
      }

      @GetMapping
      public ResponseEntity<List<UserDTO>> getAllUsers() {
            return ResponseEntity.ok(userService.getAllUsers());
      }

      @PatchMapping("/update")
      public ResponseEntity<UserDTO> updateUser(@RequestParam String username, @RequestBody UserDTO userDTO) {
            return ResponseEntity.ok(userService.updateUser(username, userDTO));
      }

      @GetMapping("/{username}")
      public ResponseEntity<UserDTO> getMethodName(@RequestParam String username) {
            return ResponseEntity.ok(userService.getUserByUsername(username));
      }

}
