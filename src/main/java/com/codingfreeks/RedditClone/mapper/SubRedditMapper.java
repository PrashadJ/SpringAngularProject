package com.codingfreeks.RedditClone.mapper;

import com.codingfreeks.RedditClone.dto.SubRedditDto;
import com.codingfreeks.RedditClone.model.Post;
import com.codingfreeks.RedditClone.model.SubReddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubRedditMapper {

    @Mappings({@Mapping(target = "numberOfPosts" , expression = "java(mapPosts(subReddit.getPosts()))"),
            @Mapping(target = "subredditname",source = "name")})
    SubRedditDto mapSubRedditToDto(SubReddit subReddit);

    default Integer mapPosts(List<Post> postList)
    {
        return postList.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    SubReddit mapToSubReddit(SubRedditDto subRedditDto);
}
