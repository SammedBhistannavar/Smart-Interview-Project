package com.Smart.Interview.Prep.Platform.repository;

import com.Smart.Interview.Prep.Platform.dto.Quiz.QuizStartRequest;
import com.Smart.Interview.Prep.Platform.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByTopicAndDifficulty(String topic,String difficulty);

    @Query(value = "SELECT * FROM question WHERE topic = :topic AND difficulty = :difficulty ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Question> findRandomQuestions(@Param("topic") String topic,
                                       @Param("difficulty") String difficulty);

}
