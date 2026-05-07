package com.Smart.Interview.Prep.Platform.dto.Quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResultDetailDTO {

    private Long questionId;
    private String question;
    private String selectedAnswer;
    private String correctAnswer;
    private boolean isCorrect;
}
