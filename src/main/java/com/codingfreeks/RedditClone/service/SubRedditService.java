package com.codingfreeks.RedditClone.service;

import com.codingfreeks.RedditClone.dto.SubRedditDto;
import com.codingfreeks.RedditClone.exceptions.SpringRedditException;
import com.codingfreeks.RedditClone.mapper.SubRedditMapper;
import com.codingfreeks.RedditClone.model.SubReddit;
import com.codingfreeks.RedditClone.model.UserD;
import com.codingfreeks.RedditClone.repository.SubRedditRepository;
import com.codingfreeks.RedditClone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubRedditService {

    private final SubRedditRepository subRedditRepository;
    private final SubRedditMapper subRedditMapper;
    private final UserRepository userRepository;

    @Transactional
    public SubRedditDto save(SubRedditDto subRedditDto) {
        SubReddit subReddit = mapSubRedditToDto(subRedditDto);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserD> userFromDb = userRepository.findByUsername(userDetails.getUsername());
        userFromDb.orElseThrow(() -> new SpringRedditException("No user found with username: " + userDetails.getUsername()));
        UserD user = userFromDb.get();
        subReddit.setUser(user);
        subReddit.setCreatedDate(Instant.now());
        SubReddit save = subRedditRepository.save(subReddit);
        subRedditDto.setId(save.getId());
        return subRedditDto;
    }

    private SubReddit mapSubRedditToDto(SubRedditDto subRedditDto) {
        return subRedditMapper.mapToSubReddit(subRedditDto);
    }

    public SubRedditDto getSubRedditById(String id) {
        return subRedditMapper.mapSubRedditToDto(subRedditRepository.getOne(Long.parseLong(id)));
    }

    @Transactional
    public List<SubRedditDto> getAll() {
        return subRedditRepository.findAll().stream().map(subRedditMapper::mapSubRedditToDto).collect(Collectors.toList());
    }

    private SubRedditDto mapToDto(SubReddit subReddit) {
        return subRedditMapper.mapSubRedditToDto(subReddit);
    }
}
