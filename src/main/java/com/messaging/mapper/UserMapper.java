package com.messaging.mapper;

import com.messaging.dto.UserDTO;
import com.messaging.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toUser(final UserDTO dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }

    public UserDTO toUserDTO(final User user) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }
}
