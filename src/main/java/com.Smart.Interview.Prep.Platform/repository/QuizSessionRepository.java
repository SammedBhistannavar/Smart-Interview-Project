package com.Smart.Interview.Prep.Platform.repository;

import com.Smart.Interview.Prep.Platform.entity.QuizSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizSessionRepository extends JpaRepository<QuizSession,Long> {
    
}
