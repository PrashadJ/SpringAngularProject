package com.codingfreeks.RedditClone.service;

import com.codingfreeks.RedditClone.dto.CommentsDto;
import com.codingfreeks.RedditClone.model.Comment;
import com.codingfreeks.RedditClone.model.Post;
import com.codingfreeks.RedditClone.model.UserD;
import com.codingfreeks.RedditClone.repository.CommentRepository;
import com.codingfreeks.RedditClone.repository.PostRepository;
import com.codingfreeks.RedditClone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentsService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final UserRepository userRepository;

    @Transactional
    public boolean save(CommentsDto commentsDto) {
        try {
            Comment comment = new Comment();
            Optional<Post> postYN = postRepository.findById(commentsDto.getPostId());
            Post po = postYN.get();
            UserD user = authService.getCurrentUser();
            Comment.CommentBuilder cmbd = Comment.builder();
            cmbd.cratedDate(Instant.now()).
                    text(commentsDto.getText()).
                    post(po).user(user);

            commentRepository.save(cmbd.build());
            return true;
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return false;
    }

    @Transactional(readOnly = true)
    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.getOne(postId);
        return commentRepository.
                findBypost(post).
                stream().
                map(this::maptoDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommentsDto> getAllCommentsForUser(String userName) {
        UserD user = userRepository.findByUsername(userName).get();
        return commentRepository.findByuser(user).
                stream().
                map(this::maptoDto).
                collect(Collectors.toList());
    }

    private CommentsDto maptoDto(Comment comment) {
        CommentsDto.CommentsDtoBuilder cmbd = CommentsDto.builder();

        return cmbd.text(comment.getText()).
                userName(comment.getUser().getUsername()).
                postId(comment.getPost().getPostId()).
                createdDate(comment.getCratedDate()).
                id(comment.getId()).
                build();
    }
}
