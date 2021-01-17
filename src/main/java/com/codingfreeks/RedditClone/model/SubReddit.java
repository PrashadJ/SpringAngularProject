package com.codingfreeks.RedditClone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SubReddit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message ="Community name is reuqired")
    String name;

    @NotBlank(message ="description is reuqired")
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;

    //created_date timestamp NULL,
    @CreatedDate
    @Column(columnDefinition = "timestamp default now()")
    private Instant createdDate=Instant.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private UserD user;


}
