package com.codingfreeks.RedditClone.security;

import com.codingfreeks.RedditClone.exceptions.SpringRedditException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JwtProvider {

    private static final String SECRET_KEY = "EqjWQ7MYqS4VkF2vZLNOOH9r4XVa3XrIsibni4cJ6eEsAphTKHvnMoRmZdfC0PLD";
    private KeyStore keyStore;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceStream, "keystore123456".toCharArray());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return Jwts.builder().setSubject(user.getUsername()).
                signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    private String getPrivateKey() {
        try {
            Key temp = keyStore.getKey("springblog", "keystore123456".toCharArray());
            String encodedKey = Base64.getEncoder().encodeToString(temp.getEncoded());
            return encodedKey;
        } catch (Exception exc) {
            exc.printStackTrace();
            throw new SpringRedditException("Exception occurred while retriving key from keystore");
        }
    }

    public boolean validateToken(String jwt) {
        parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt);
        return true;
    }

//    private String getPublicKey() {
//        try {
//            Certificate certificate =  keyStore.getCertificate("springblog");
//            byte[] encodedCertKey = certificate.getEncoded();
//            secretKey = TextCodec.BASE64URL.encode(certificate.getPublicKey());
//            return b64CertKey;
//        } catch (Exception exc) {
//            exc.printStackTrace();
//            throw new SpringRedditException("Exception occurred while retriving Public key from keystore");
//        }
//    }

    public String getUserNameFromJwt(String token){
        Claims claims=parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
