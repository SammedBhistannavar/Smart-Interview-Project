package com.Smart.Interview.Prep.Platform.dto;

import com.Smart.Interview.Prep.Platform.entity.Enums.Role;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private Role role;
}
