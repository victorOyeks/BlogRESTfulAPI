package com.example.restfulblogpost.services.impl;

import com.example.restfulblogpost.DTO.CommentDTO;
import com.example.restfulblogpost.entities.Comment;
import com.example.restfulblogpost.entities.Post;
import com.example.restfulblogpost.exceptions.ResourceNotFoundException;
import com.example.restfulblogpost.repositories.CommentRepository;
import com.example.restfulblogpost.repositories.PostRepository;
import com.example.restfulblogpost.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post Id", postId));
        Comment comment = this.modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepository.save(comment);
        return this.modelMapper.map(savedComment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Long commentId) {

        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment", "comment Id", commentId));
        this.commentRepository.delete(comment);
    }
}
