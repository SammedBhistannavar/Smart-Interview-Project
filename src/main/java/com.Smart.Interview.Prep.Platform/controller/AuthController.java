package com.Smart.Interview.Prep.Platform.controller;

import com.Smart.Interview.Prep.Platform.dto.UserRequestDTO;
import com.Smart.Interview.Prep.Platform.dto.UserResponseDTO;
import com.Smart.Interview.Prep.Platform.dto.AuthResponseDTO;
import com.Smart.Interview.Prep.Platform.dto.LoginRequestDTO;
import com.Smart.Interview.Prep.Platform.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Smart.Interview.Prep.Platform.service.UserProfileService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRequestDTO request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }
}