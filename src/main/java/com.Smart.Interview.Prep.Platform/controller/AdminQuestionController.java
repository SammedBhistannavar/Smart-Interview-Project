    package com.Smart.Interview.Prep.Platform.controller;

    import com.Smart.Interview.Prep.Platform.dto.Questions.QuestionRequestDTO;
    import com.Smart.Interview.Prep.Platform.dto.Questions.QuestionResponseDTO;
    import com.Smart.Interview.Prep.Platform.service.QuestionService;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.Authentication;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @Slf4j
    @RestController
    @RequestMapping("/admin")
    @RequiredArgsConstructor
    public class AdminQuestionController {

        private final QuestionService questionService;

        @PostMapping("/questions")
        public ResponseEntity<QuestionResponseDTO> addQuestion(
                @RequestBody QuestionRequestDTO dto,
                Authentication authentication
        ) {
            String adminEmail = authentication.getName();
            log.info("Added the Questions {}",adminEmail);

            return ResponseEntity.ok(
                    questionService.addQuestion(dto, adminEmail)
            );
        }
    }