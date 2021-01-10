package com.codingfreeks.RedditClone.service;

import com.codingfreeks.RedditClone.dto.SubRedditDto;
import com.codingfreeks.RedditClone.model.SubReddit;
import com.codingfreeks.RedditClone.repository.SubRedditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubRedditService {

    private final SubRedditRepository subRedditRepository;

    @Transactional
    public SubRedditDto save(SubRedditDto subRedditDto) {
        SubReddit subReddit = mapSubRedditToDto(subRedditDto);
        SubReddit save = subRedditRepository.save(subReddit);
        subRedditDto.setId(save.getId());
        return subRedditDto;
    }

    private SubReddit mapSubRedditToDto(SubRedditDto subRedditDto) {
        return SubReddit.builder().
                name(subRedditDto.getSubredditname()).
                description(subRedditDto.getDescription()).
                build();
    }

    @Transactional
    public List<SubRedditDto> getAll() {
        return subRedditRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private SubRedditDto mapToDto(SubReddit subReddit) {
        return SubRedditDto.builder().subredditname(subReddit.getName()).
                id(subReddit.getId()).description(subReddit.getDescription()).
                numberOfPosts(subReddit.getPosts().size()).
                build();
    }
}
