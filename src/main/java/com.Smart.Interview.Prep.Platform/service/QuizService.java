package com.Smart.Interview.Prep.Platform.service;

import com.Smart.Interview.Prep.Platform.dto.Quiz.QuizQuestionDTO;
import com.Smart.Interview.Prep.Platform.dto.Quiz.QuizResultDTO;
import com.Smart.Interview.Prep.Platform.dto.Quiz.SubmitRequest;
import com.Smart.Interview.Prep.Platform.entity.Question;
import com.Smart.Interview.Prep.Platform.entity.QuizAnswer;
import com.Smart.Interview.Prep.Platform.entity.QuizSession;
import com.Smart.Interview.Prep.Platform.repository.QuestionRepository;
import com.Smart.Interview.Prep.Platform.repository.QuizSessionRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class QuizService {

    private final QuestionRepository questionRepository;
    private final QuizSessionRepository quizSessionRepository

    public List<QuizQuestionDTO> startQuiz(String topic, String difficulty, String email) {

        List<Question> questions = questionRepository.findRandomQuestions(topic, difficulty);

        QuizSession session = new QuizSession();
        session.setUserEmail(email);
        session.setTopic(topic);
        session.setDifficulty(difficulty);
        session.setStartedAt(LocalDateTime.now());

        quizSessionRepository.save(session);

        return questions.stream()
                .map(q -> mapToDTO(q))
                .toList();
    }

    public QuizResultDTO submitQuiz(SubmitRequest.QuizSubmitRequest request) {

        QuizSession session = quizSessionRepository
                .findById(request.getQuizSessionId())
                .orElseThrow();

        int score = 0;

        List<ResultDetailDTO> details = new ArrayList<>();

        for (SubmitRequest.AnswerDTO ans : request.getAnswers()) {

            Question q = questionRepository.findById(ans.getQuestionId())
                    .orElseThrow();

            boolean isCorrect = q.getCorrectAnswer().equals(ans.getSelectedAnswer());

            if (isCorrect) score++;

            QuizAnswer qa = new QuizAnswer();
            qa.setQuestionId(q.getId());
            qa.setSelectedAnswer(ans.getSelectedAnswer());
            qa.setCorrectAnswer(q.getCorrectAnswer());
            qa.setCorrect(isCorrect);
            qa.setQuizSession(session);

            quizAnswerRepository.save(qa);

            details.add(new ResultDetailDTO(q.getQuestion(), isCorrect));
        }

        session.setScore(score);
        session.setCompletedAt(LocalDateTime.now());
        quizSessionRepository.save(session);

        return new QuizResultDTO(score, request.getAnswers().size(), details);
    }

    public QuizResultDTO getResult(Long sessionId) {

        QuizSession session = quizSessionRepository.findById(sessionId)
                .orElseThrow();

        List<QuizAnswer> answers =
                quizAnswerRepository.findByQuizSession(session);

        return mapToResultDTO(session, answers);
    }
}
