package com.codingfreeks.RedditClone.service;

import com.codingfreeks.RedditClone.dto.RegisterRequest;
import com.codingfreeks.RedditClone.exceptions.SpringRedditException;
import com.codingfreeks.RedditClone.model.NotificationEmail;
import com.codingfreeks.RedditClone.model.UserD;
import com.codingfreeks.RedditClone.model.VerifivationToken;
import com.codingfreeks.RedditClone.repository.UserRepository;
import com.codingfreeks.RedditClone.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {


    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        UserD user = new UserD();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnaabled(false);

        userRepository.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please activate your account", user.getEmail(), "Thankyou for signing up" +
                "spring reddit please click the link below to activate your account: " +
                "http://localhost:8081/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(UserD user) {
        String token = UUID.randomUUID().toString();
        VerifivationToken verifivationToken = new VerifivationToken();
        verifivationToken.setToken(token);
        verifivationToken.setUser(user);
        verificationTokenRepository.save(verifivationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerifivationToken> tokenPresent = verificationTokenRepository.findByToken(token);
        tokenPresent.orElseThrow(() -> new SpringRedditException("Could not verify you sorry..."));
        enableUserInDb(tokenPresent.get());
    }

    @Transactional
    public void enableUserInDb(VerifivationToken verifivationToken) {
        Optional<UserD> userFound = userRepository.findById(verifivationToken.getUser().getUserId());
        userFound.orElseThrow(() -> new SpringRedditException("Could not find the user"));
        UserD userVar = userFound.get();
        userVar.setEnaabled(true);
        userRepository.save(userVar);
    }
}
