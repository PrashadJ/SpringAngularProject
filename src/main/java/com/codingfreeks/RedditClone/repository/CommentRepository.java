package com.codingfreeks.RedditClone.repository;

import com.codingfreeks.RedditClone.model.Comment;
import com.codingfreeks.RedditClone.model.Post;
import com.codingfreeks.RedditClone.model.UserD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findBypost(Post post);
    List<Comment> findByuser(UserD user);

}
