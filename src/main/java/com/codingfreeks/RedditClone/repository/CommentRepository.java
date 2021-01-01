package com.codingfreeks.RedditClone.repository;

import com.codingfreeks.RedditClone.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
