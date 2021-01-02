package com.codingfreeks.RedditClone.repository;

import com.codingfreeks.RedditClone.model.VerifivationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerifivationToken, Long> {
    Optional<VerifivationToken> findByToken(String token);
}
