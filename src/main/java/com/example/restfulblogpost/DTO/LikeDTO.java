package com.example.restfulblogpost.DTO;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDTO {
    private Long id;
    private Long post;
    private Long user;
}