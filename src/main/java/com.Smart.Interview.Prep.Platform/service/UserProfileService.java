package com.Smart.Interview.Prep.Platform.service;

import com.Smart.Interview.Prep.Platform.dto.Profile.UserProfileResponseDTO;
import com.Smart.Interview.Prep.Platform.dto.Profile.UserProfileUpdateDTO;
import com.Smart.Interview.Prep.Platform.entity.User;
import com.Smart.Interview.Prep.Platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;

    public UserProfileResponseDTO getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToDTO(user);
    }

    public UserProfileResponseDTO updateProfile(String email, UserProfileUpdateDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        user.setBio(dto.getBio());
        user.setSkills(dto.getSkills());
        user.setResumeUrl(dto.getResumeUrl());
        userRepository.save(user);

        return mapToDTO(user);
    }

    private UserProfileResponseDTO mapToDTO(User user) {
        return UserProfileResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .bio(user.getBio())
                .skills(user.getSkills())
                .resumeUrl(user.getResumeUrl())
                .build();
    }
}