package com.codingfreeks.RedditClone.repository;

import com.codingfreeks.RedditClone.model.Post;
import com.codingfreeks.RedditClone.model.UserD;
import com.codingfreeks.RedditClone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, UserD user);

}
