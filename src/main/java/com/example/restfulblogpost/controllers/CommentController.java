package com.example.restfulblogpost.controllers;

import com.example.restfulblogpost.DTO.ApiResponse;
import com.example.restfulblogpost.DTO.CommentDTO;
import com.example.restfulblogpost.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment (@RequestBody CommentDTO comment, @PathVariable Long postId) {

        CommentDTO createComment = this.commentService.createComment(comment, postId);
        return  new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment (@PathVariable Long commentId) {

        this.commentService.deleteComment(commentId);
        return  new ResponseEntity<>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
    }
}
