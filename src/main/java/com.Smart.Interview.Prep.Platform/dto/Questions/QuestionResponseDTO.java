package com.Smart.Interview.Prep.Platform.dto.Questions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionResponseDTO {
    private Long id;
    private String questions;
    private String description;
    private String topic;
    private String difficulty;
}
