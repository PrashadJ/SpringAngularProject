package com.codingfreeks.RedditClone.model;

import java.util.Arrays;

public enum VoteType {
    UPVOTE(1), DOWNVOTE(2);
    private int direction;

    VoteType(int direction) {
        this.direction = direction;
    }

    boolean lookup() {
        return direction == 1 ? true : false;
    }

    public static VoteType getVoteType(int directionVar){
        return Arrays.asList(VoteType.values()).
                stream().
                filter(val->val.direction==directionVar).
                findAny().
                get();
    }
}
