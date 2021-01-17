package com.codingfreeks.RedditClone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private Long id;
    private String userName;
    private String description;
    private String url;
    private String subRedditName;
    private String postName;


}
