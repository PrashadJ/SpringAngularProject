package com.codingfreeks.RedditClone.controller;


import com.codingfreeks.RedditClone.dto.CommentsDto;
import com.codingfreeks.RedditClone.service.CommentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentsDto commentsDto) {
        boolean isSave = commentsService.save(commentsDto);
        return ResponseEntity.
                status(isSave ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR).
                build();
    }


    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentsService.getAllCommentsForPost(postId));
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@PathVariable String userName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentsService.getAllCommentsForUser(userName));
    }

}
