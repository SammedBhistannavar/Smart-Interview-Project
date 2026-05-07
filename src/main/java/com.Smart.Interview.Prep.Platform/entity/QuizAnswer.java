package com.Smart.Interview.Prep.Platform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizAnswer {

    @Id
    @GeneratedValue
    private Long id;

    private Long questionId;
    private String selectedAnswer;
    private String correctAnswer;

    private boolean isCorrect;

    @ManyToOne
    private QuizSession quizSession;
}
