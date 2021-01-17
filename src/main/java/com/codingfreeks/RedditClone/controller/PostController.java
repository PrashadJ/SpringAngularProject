package com.codingfreeks.RedditClone.controller;

import com.codingfreeks.RedditClone.dto.PostRequest;
import com.codingfreeks.RedditClone.dto.PostResponse;
import com.codingfreeks.RedditClone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    public final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<String> createPosts(@RequestBody PostRequest postRequest){
        PostResponse post=postService.save(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(post.getId()+ "Created");
    }

    @GetMapping("/BySubReddit/{subRedditId}")
    public ResponseEntity<List<PostResponse>> getPostsBySubRedditId(@PathVariable Long subRedditId){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostBySubRedditId(subRedditId));
    }


    @GetMapping("/ByUser/{userName}")
    public ResponseEntity<List<PostResponse>> getPostsByUserName(@PathVariable String userName){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostByUserName(userName));
    }

}
