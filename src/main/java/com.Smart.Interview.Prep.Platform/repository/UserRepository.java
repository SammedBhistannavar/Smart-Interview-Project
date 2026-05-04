package com.Smart.Interview.Prep.Platform.repository;
import com.Smart.Interview.Prep.Platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}