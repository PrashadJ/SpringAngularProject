package com.codingfreeks.RedditClone.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    private VoteType voteType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "postid", referencedColumnName = "postid")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private UserD user;
}
