package com.Smart.Interview.Prep.Platform.service;

import com.Smart.Interview.Prep.Platform.dto.Questions.QuestionRequestDTO;
import com.Smart.Interview.Prep.Platform.dto.Questions.QuestionResponseDTO;
import com.Smart.Interview.Prep.Platform.entity.Question;
import com.Smart.Interview.Prep.Platform.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    // ✅ Admin: Add Question
    public QuestionResponseDTO addQuestion(QuestionRequestDTO dto, String adminEmail) {

        Question question = Question.builder()
                .tittle(dto.getTittle())
                .description(dto.getDescription())
                .topic(dto.getTopic())
                .difficulty(dto.getDifficulty())
                .createdBy(adminEmail)
                .build();

        Question saved = questionRepository.save(question);

        return mapToResponse(saved);
    }

    // ✅ User: Fetch Questions
    public List<QuestionResponseDTO> getQuestions(String topic, String difficulty) {
        return questionRepository
                .findByTopicAndDifficulty(topic, difficulty)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private QuestionResponseDTO mapToResponse(Question q) {
        return QuestionResponseDTO.builder()
                .id(q.getId())
                .tittle(q.getTittle())
                .description(q.getDescription())
                .topic(q.getTopic())
                .difficulty(q.getDifficulty())
                .build();
    }
}