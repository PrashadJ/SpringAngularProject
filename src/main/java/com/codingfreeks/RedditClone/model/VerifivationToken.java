package com.codingfreeks.RedditClone.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "token")
public class VerifivationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    private UserD user;

    private Instant expiryDate;
}
