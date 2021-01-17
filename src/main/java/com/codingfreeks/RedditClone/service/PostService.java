package com.codingfreeks.RedditClone.service;

import com.codingfreeks.RedditClone.dto.PostRequest;
import com.codingfreeks.RedditClone.dto.PostResponse;
import com.codingfreeks.RedditClone.mapper.PostMapper;
import com.codingfreeks.RedditClone.model.Post;
import com.codingfreeks.RedditClone.model.SubReddit;
import com.codingfreeks.RedditClone.repository.PostRepository;
import com.codingfreeks.RedditClone.repository.SubRedditRepository;
import com.codingfreeks.RedditClone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubRedditRepository subRedditRepository;
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final AuthService authService;
    private final UserRepository userRepository;

    public PostResponse save(PostRequest postService) {
        SubReddit subReddit = subRedditRepository.findByName(postService.getSubRedditName());
        Post post = postRepository.save(postMapper.map(postService, subReddit, authService.getCurrentUser()));
        return postMapper.mapToDto(post);
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream().
                map(postMapper::mapToDto).
                collect(toList());
    }

    public PostResponse getPostById(String id) {
        return postMapper.mapToDto(postRepository.getOne(Long.parseLong(id)));
    }


    public List<PostResponse> getPostBySubRedditId(Long subRedditId) {
        return postRepository.findAllBysubReddit(subRedditRepository.getOne(subRedditId)).
                stream().
                map(postMapper::mapToDto).
                collect(Collectors.toList());
    }

    public List<PostResponse> getPostByUserName(String userName) {
        return postRepository.
                findAllByuser(userRepository.findByUsername(userName).get()).
                stream().
                map(postMapper::mapToDto).collect(Collectors.toList());
    }
}
