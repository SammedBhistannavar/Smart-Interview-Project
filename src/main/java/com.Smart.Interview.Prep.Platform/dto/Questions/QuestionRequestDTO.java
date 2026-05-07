package com.Smart.Interview.Prep.Platform.dto.Questions;

import lombok.Data;

@Data
public class QuestionRequestDTO {
    private String question;
    private String description;
    private String topic;
    private String difficulty;
}
