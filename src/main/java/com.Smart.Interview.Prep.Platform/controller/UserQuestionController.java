package com.Smart.Interview.Prep.Platform.controller;

import com.Smart.Interview.Prep.Platform.dto.Questions.QuestionResponseDTO;
import com.Smart.Interview.Prep.Platform.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserQuestionController {

    private final QuestionService questionService;

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestions(
            @RequestParam String topic,
            @RequestParam String difficulty
    ) {
        return ResponseEntity.ok(
                questionService.getQuestions(topic, difficulty)
        );
    }
}
