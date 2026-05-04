package com.Smart.Interview.Prep.Platform.dto.Profile;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponseDTO {
    private String name;
    private String email;
    private String phone;
    private String bio;
    private String skills;
    private String resumeUrl;
}