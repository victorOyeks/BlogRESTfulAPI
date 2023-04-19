package com.example.restfulblogpost.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "post_title", length = 100, nullable = false)
    private String title;

    @Column(length = 1000)
    private String content;

    private String imageName;

    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @Column(name = "likeCount")
    private Long likeCount;

    @OneToMany(mappedBy = "blogPost")
    @ToString.Exclude
    private List<Like> likes = new ArrayList<>();

}
