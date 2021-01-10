package com.codingfreeks.RedditClone.service;

import com.codingfreeks.RedditClone.exceptions.SpringRedditException;
import com.codingfreeks.RedditClone.model.UserD;
import com.codingfreeks.RedditClone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserD> userFromDb = userRepository.findByUsername(s);
        userFromDb.orElseThrow(() -> new SpringRedditException("No user found with username: " + s));
        UserD user = userFromDb.get();
        return new User(user.getUsername(), user.getPassword(), user.isEnaabled(),
                true, true, true, getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
