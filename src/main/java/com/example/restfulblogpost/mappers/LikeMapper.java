package com.example.restfulblogpost.mappers;

import com.example.restfulblogpost.DTO.LikeDTO;
import com.example.restfulblogpost.entities.Like;

public class LikeMapper {

    public static LikeDTO buildForPost(Like like){
        var post = like.getPost();
        var user = like.getUser();
        return LikeDTO.builder()
                .post(post.getPostId())
                .user(user.getUserId())
                .build();
    }

//    public static LikeDTO buildForComment(Like like){
//        var comment = like.getComment();
//        var user = like.getUser();
//        return LikeDTO.builder()
//                .comment(comment.getId())
//                .user(user.getId())
//                .build();

}
