package com.messaging.service;

import com.messaging.dto.UserDTO;
import com.messaging.entity.User;
import com.messaging.exception.UserNotFoundException;
import com.messaging.mapper.UserMapper;
import com.messaging.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.getUsername())) {
            throw new UserNotFoundException("Username already exists");
        }
        User user = userMapper.toUser(userDTO);
        return userMapper.toUserDTO(userRepository.save(user));
    }

    @Transactional
    public List<UserDTO> getAllUsers() {
        List<User> uList = userRepository.findAll();
        return uList.stream()
                .map(userMapper::toUserDTO)
                .toList();
    }

    @Transactional
    public UserDTO getUserByUsername(String username) {
        User existingUser = userRepository.findById(username)
                .orElseThrow(() -> new UserNotFoundException(username + " Not found"));
        return userMapper.toUserDTO(existingUser);
    }

    @Transactional
    public UserDTO updateUser(String username, UserDTO userUpdating) {
        User existingUser = userRepository.findById(username)
                .orElseThrow(() -> new UserNotFoundException(username + " Not existing"));
        existingUser.setPassword(userUpdating.getPassword());
        existingUser.setFirstname(userUpdating.getFirstname());
        existingUser.setLastname(userUpdating.getLastname());
        existingUser.setStatus(userUpdating.getStatus());
        return userMapper.toUserDTO(userRepository.save(existingUser));
    }

}
