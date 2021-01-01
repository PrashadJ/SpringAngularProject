package com.codingfreeks.RedditClone.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "postid", referencedColumnName = "postid")
    private Post post;

    private Instant cratedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "userid", referencedColumnName = "userid")
    private UserD user;

}
