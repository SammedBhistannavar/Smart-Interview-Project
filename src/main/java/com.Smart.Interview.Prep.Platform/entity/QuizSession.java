package com.Smart.Interview.Prep.Platform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizSession {

    @Id
    @GeneratedValue
    private Long id;

    private String userEmail;

    private String topic;

    private String difficulty;

    private int score;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;
}
