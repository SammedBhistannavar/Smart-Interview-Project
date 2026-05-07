package com.Smart.Interview.Prep.Platform.dto.Quiz;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuizQuestionDTO {
    private Long id;
    private String question;
    private List<String> options;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
}