package com.Smart.Interview.Prep.Platform.dto.Profile;

import lombok.Data;

@Data
public class UserProfileUpdateDTO {
    private String name;
    private String phone;
    private String bio;
    private String skills;
    private String resumeUrl;
}