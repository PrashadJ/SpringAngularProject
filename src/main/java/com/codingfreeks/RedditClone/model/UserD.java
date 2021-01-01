package com.codingfreeks.RedditClone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Userame can't be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Email
    @NotEmpty(message = "Email is required")
    private String email;

    private Instant created;
    private boolean enaabled;

}
