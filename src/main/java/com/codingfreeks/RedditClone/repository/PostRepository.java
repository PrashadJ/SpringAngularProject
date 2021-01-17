package com.codingfreeks.RedditClone.repository;

import com.codingfreeks.RedditClone.model.Post;
import com.codingfreeks.RedditClone.model.SubReddit;
import com.codingfreeks.RedditClone.model.UserD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBysubReddit(SubReddit subreddit);

    List<Post> findAllByuser(UserD id);

}