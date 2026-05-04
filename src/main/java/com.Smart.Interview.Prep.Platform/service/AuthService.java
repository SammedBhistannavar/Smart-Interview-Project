package com.Smart.Interview.Prep.Platform.service;

import com.Smart.Interview.Prep.Platform.dto.UserRequestDTO;
import com.Smart.Interview.Prep.Platform.dto.UserResponseDTO;
import com.Smart.Interview.Prep.Platform.dto.AuthResponseDTO;
import com.Smart.Interview.Prep.Platform.dto.LoginRequestDTO;
import com.Smart.Interview.Prep.Platform.entity.User;
import com.Smart.Interview.Prep.Platform.repository.UserRepository;
import com.Smart.Interview.Prep.Platform.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);


    public UserResponseDTO register(UserRequestDTO request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
        logger.info("User attempting to Register: {}", request.getName());

        User saved = userRepository.save(user);

        return UserResponseDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .email(saved.getEmail())
                .role(saved.getRole())
                .build();
    }


    //    Login
    public ResponseEntity<AuthResponseDTO> login(LoginRequestDTO request) {

        logger.info("Login attempt for email: {}", request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password")
                );

        if (!request.getPassword().equals(user.getPassword())) {
            logger.warn("Invalid password attempt for email: {}", request.getEmail());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(AuthResponseDTO.builder()
                            .msg("Invalid email or password")
                            .build());
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(
                AuthResponseDTO.builder()
                        .token(token)
                        .build()
        );
    }
}