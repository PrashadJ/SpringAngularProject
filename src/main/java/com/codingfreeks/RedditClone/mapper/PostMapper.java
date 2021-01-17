package com.codingfreeks.RedditClone.mapper;

import com.codingfreeks.RedditClone.dto.PostRequest;
import com.codingfreeks.RedditClone.dto.PostResponse;
import com.codingfreeks.RedditClone.model.Post;
import com.codingfreeks.RedditClone.model.SubReddit;
import com.codingfreeks.RedditClone.model.UserD;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subReddit", source = "subReddit")
    @Mapping(target = "user", source = "user")
    Post map(PostRequest postRequest, SubReddit subReddit, UserD user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subRedditName", source = "subReddit.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(Post post);

}
