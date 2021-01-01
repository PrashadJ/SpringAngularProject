package com.codingfreeks.RedditClone.repository;

import com.codingfreeks.RedditClone.model.SubReddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRedditRepository extends JpaRepository<SubReddit, Long> {
}
