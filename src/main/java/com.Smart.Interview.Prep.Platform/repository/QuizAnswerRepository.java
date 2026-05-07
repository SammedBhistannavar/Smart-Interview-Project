package com.Smart.Interview.Prep.Platform.repository;

import com.Smart.Interview.Prep.Platform.entity.QuizAnswer;
import com.Smart.Interview.Prep.Platform.entity.QuizSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizAnswerRepository extends JpaRepository<QuizAnswer, Long> {

    List<QuizAnswer> findByQuizSession(QuizSession session);
}
