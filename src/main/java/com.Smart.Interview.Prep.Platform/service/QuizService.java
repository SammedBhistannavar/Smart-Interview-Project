package com.Smart.Interview.Prep.Platform.service;

import com.Smart.Interview.Prep.Platform.dto.Quiz.*;
import com.Smart.Interview.Prep.Platform.entity.Question;
import com.Smart.Interview.Prep.Platform.entity.QuizAnswer;
import com.Smart.Interview.Prep.Platform.entity.QuizSession;
import com.Smart.Interview.Prep.Platform.repository.QuestionRepository;
import com.Smart.Interview.Prep.Platform.repository.QuizAnswerRepository;
import com.Smart.Interview.Prep.Platform.repository.QuizSessionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class QuizService {

    private final QuestionRepository questionRepository;
    private final QuizSessionRepository quizSessionRepository;
    private final QuizAnswerRepository quizAnswerRepository;

    public List<QuizQuestionDTO> startQuiz(QuizStartRequest request,String email) {

        List<Question> questions = questionRepository.findRandomQuestions(request.getTopic(), request.getDifficulty());
        log.info("Questions {}",questions);

        QuizSession session = new QuizSession();
        session.setUserEmail(email);
        session.setTopic(request.getTopic());
        session.setDifficulty(request.getDifficulty());
        session.setStartedAt(LocalDateTime.now());

        quizSessionRepository.save(session);

        return questions.stream()
                .map(q -> mapToDTO(q))
                .toList();
    }

    public QuizResultDTO submitQuiz(QuizSubmitRequest request) {

        QuizSession session = quizSessionRepository
                .findById(request.getQuizSessionId())
                .orElseThrow();

        int score = 0;

        List<ResultDetailDTO> details = new ArrayList<>();

        for (AnswerDTO ans : request.getAnswers()) {

            Question q = questionRepository.findById(ans.getQuestionId())
                    .orElseThrow();

            boolean isCorrect = q.getAnswer().equals(ans.getSelectedAnswer());

            if (isCorrect) score++;

            QuizAnswer qa = new QuizAnswer();
            qa.setQuestionId(q.getId());
            qa.setSelectedAnswer(ans.getSelectedAnswer());
            qa.setCorrectAnswer(q.getAnswer());
            qa.setCorrect(isCorrect);
            qa.setQuizSession(session);

            quizAnswerRepository.save(qa);

            details.add(new ResultDetailDTO(
                    q.getId(),
                    q.getQuestions(),
                    ans.getSelectedAnswer(),
                    q.getAnswer(),
                    isCorrect
            ));
        }

        session.setScore(score);
        session.setCompletedAt(LocalDateTime.now());
        quizSessionRepository.save(session);

        return new QuizResultDTO(score, request.getAnswers().size(), details);
    }

    public QuizResultDTO getResult(Long sessionId) {

        QuizSession session = quizSessionRepository.findById(sessionId)
                .orElseThrow();

        List<QuizAnswer> answers = quizAnswerRepository.findByQuizSession(session);

        return mapToResultDTO(session, answers);
    }


private QuizQuestionDTO mapToDTO(Question q) {

    List<String> options = new ArrayList<>();

    if (q.getOptionA() != null) options.add(q.getOptionA());
    if (q.getOptionB() != null) options.add(q.getOptionB());
    if (q.getOptionC() != null) options.add(q.getOptionC());
    if (q.getOptionD() != null) options.add(q.getOptionD());

    return QuizQuestionDTO.builder()
            .id(q.getId())
            .question(q.getQuestions())
            .options(options)
            .build();
}

    private QuizResultDTO mapToResultDTO(
            QuizSession session,
            List<QuizAnswer> answers
    ) {

        List<ResultDetailDTO> details = answers.stream()
                .map(answer -> {

                    return ResultDetailDTO.builder()
                            .questionId(answer.getId())
                            .selectedAnswer(answer.getSelectedAnswer())
                            .correctAnswer(answer.getCorrectAnswer())
                            .isCorrect(answer.isCorrect())
                            .build();
                })
                .toList();

        return QuizResultDTO.builder()
                .score(session.getScore())
                .totalQuestions(answers.size())
                .details(details)
                .build();
    }
}
