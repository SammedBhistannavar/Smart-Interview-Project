package com.Smart.Interview.Prep.Platform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
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
