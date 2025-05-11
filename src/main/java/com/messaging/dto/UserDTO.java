package com.messaging.dto;

import com.messaging.entity.Status;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Status status;
    private String avatar;
}
