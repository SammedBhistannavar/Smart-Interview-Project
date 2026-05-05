package com.Smart.Interview.Prep.Platform.dto.Quiz;

import java.util.List;

public class SubmitRequest {
    public class QuizSubmitRequest {
        private Long quizSessionId;
        private List<AnswerDTO> answers;
    }

    public class AnswerDTO {
        private Long questionId;
        private String selectedAnswer;
    }
}
