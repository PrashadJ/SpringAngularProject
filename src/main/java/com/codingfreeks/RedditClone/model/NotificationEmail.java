package com.codingfreeks.RedditClone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail {
    private String subjct;
    private String recipient;
    private String body;

}
