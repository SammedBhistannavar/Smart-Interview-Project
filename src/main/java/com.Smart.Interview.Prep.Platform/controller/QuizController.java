package com.Smart.Interview.Prep.Platform.controller;

import com.Smart.Interview.Prep.Platform.dto.Quiz.QuizQuestionDTO;
import com.Smart.Interview.Prep.Platform.dto.Quiz.QuizResultDTO;
import com.Smart.Interview.Prep.Platform.dto.Quiz.QuizStartRequest;
import com.Smart.Interview.Prep.Platform.dto.Quiz.SubmitRequest;
import com.Smart.Interview.Prep.Platform.service.QuizService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/user/quiz/start")
    public ResponseEntity<List<QuizQuestionDTO>> startQuiz(
            @RequestBody QuizStartRequest request,
            Authentication auth
    ) {
        return ResponseEntity.ok(
                quizService.startQuiz(
                        request.getTopic(),
                        request.getDifficulty(),
                        auth.getName()
                )
        );
    }

    @PostMapping("/user/quiz/submit")
    public ResponseEntity<QuizResultDTO> submitQuiz(
            @RequestBody SubmitRequest.QuizSubmitRequest request
    ) {
        return ResponseEntity.ok(
                quizService.submitQuiz(request)
        );
    }

    @GetMapping("/user/quiz/result")
    public ResponseEntity<QuizResultDTO> getResult(
            @RequestParam Long sessionId
    ) {
        return ResponseEntity.ok(
                quizService.getResult(sessionId)
        );
    }
}
