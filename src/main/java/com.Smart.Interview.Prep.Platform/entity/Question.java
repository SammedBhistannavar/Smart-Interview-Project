package com.Smart.Interview.Prep.Platform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questions;

    @Column(length = 2000)
    private String description;
    private String Answer;
    private String topic;
    private String difficulty;
    private String createdBy;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
}
