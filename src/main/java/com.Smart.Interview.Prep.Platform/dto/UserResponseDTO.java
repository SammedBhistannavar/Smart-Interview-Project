package com.Smart.Interview.Prep.Platform.dto;
import com.Smart.Interview.Prep.Platform.entity.Enums.Role;
import lombok.*;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
