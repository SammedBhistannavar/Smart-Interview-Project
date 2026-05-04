package com.Smart.Interview.Prep.Platform.controller;

import com.Smart.Interview.Prep.Platform.dto.Profile.UserProfileResponseDTO;
import com.Smart.Interview.Prep.Platform.dto.Profile.UserProfileUpdateDTO;
import com.Smart.Interview.Prep.Platform.service.AuthService;
import com.Smart.Interview.Prep.Platform.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponseDTO> getProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(userProfileService.getProfile(email));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileResponseDTO> updateProfile(
            @RequestBody UserProfileUpdateDTO dto) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(userProfileService.updateProfile(email, dto));
    }
}