package com.codingfreeks.RedditClone.service;


import com.codingfreeks.RedditClone.dto.VoteDto;
import com.codingfreeks.RedditClone.model.Post;
import com.codingfreeks.RedditClone.model.UserD;
import com.codingfreeks.RedditClone.model.Vote;
import com.codingfreeks.RedditClone.repository.PostRepository;
import com.codingfreeks.RedditClone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId()).get();
        UserD user = authService.getCurrentUser();
        Optional<Vote> voteOP = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, user);

        if (voteOP.isPresent() &&
                voteOP.get().
                        getVoteType().
                        equals(voteDto.getVoteType())) {
            return;
        }

        Vote.VoteBuilder vbd = Vote.builder();
        vbd.voteType(voteDto.getVoteType())
                .post(post)
                .user(user);

        voteRepository.save(vbd.build());
    }
}
