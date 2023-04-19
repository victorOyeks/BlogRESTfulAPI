package com.example.restfulblogpost.services;

import com.example.restfulblogpost.DTO.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO, Long userId, Long categoryId);
    PostDTO updatePost (PostDTO postDTO, Long PostId);
    void deletePost (Long postId);
    List<PostDTO> getAllPost();
    PostDTO getPostById (Long postId);
    List<PostDTO> getPostByCategory (Long categoryId);
    List<PostDTO> getPostsByUser (Long userId);
    List<PostDTO> searchPosts (String keyword);
}
