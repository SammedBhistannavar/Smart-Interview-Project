package com.Smart.Interview.Prep.Platform.repository;

import com.Smart.Interview.Prep.Platform.dto.Quiz.QuizStartRequest;
import com.Smart.Interview.Prep.Platform.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByTopicAndDifficulty(String topic,String difficulty);

    List<QuizStartRequest> findRandomQuestions(String topic, String difficulty);


}
