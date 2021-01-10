package com.codingfreeks.RedditClone.repository;

import com.codingfreeks.RedditClone.model.UserD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserD, Long> {
    Optional<UserD> findByUsername(String username);
}
