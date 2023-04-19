package com.example.restfulblogpost.services;

import com.example.restfulblogpost.DTO.CommentDTO;

public interface CommentService {

    CommentDTO createComment (CommentDTO commentDTO, Long postId);
    void  deleteComment (Long commentId);
}
