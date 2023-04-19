package com.example.restfulblogpost.services.impl;

import com.example.restfulblogpost.entities.Category;
import com.example.restfulblogpost.entities.Post;
import com.example.restfulblogpost.entities.User;
import com.example.restfulblogpost.exceptions.ResourceNotFoundException;
import com.example.restfulblogpost.DTO.PostDTO;
import com.example.restfulblogpost.repositories.CategoryRepository;
import com.example.restfulblogpost.repositories.PostRepository;
import com.example.restfulblogpost.repositories.UserRepository;
import com.example.restfulblogpost.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO, Long userId, Long categoryId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", categoryId));
        Post post = this.modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepository.save(post);
        return this.modelMapper.map(newPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "post Id", postId));

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());

        Post updatedPost = this.postRepository.save(post);
        return this.modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post ", "post Id", postId));

        this.postRepository.delete(post);
    }

//    @Override
//    public List<PostDTO> getAllPost(Integer pageNumber, Integer pageSize) {
//        Pageable p = PageRequest.of(pageNumber, pageSize);
//        Page<Post> pagePost = this.postRepository.findAll(p);
//        List<Post> allPost = pagePost.getContent();
//        return allPost.stream().map((post -> this.modelMapper.map(post, PostDTO.class))).toList();
//    }

    @Override
    public List<PostDTO> getAllPost() {
        List<Post> allPosts = this.postRepository.findAll();
        return allPosts.stream().map((post -> this.modelMapper.map(post, PostDTO.class))).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post Id", postId));

        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostByCategory(Long categoryId) {
        Category cat = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category", "category Id", categoryId));
        List<Post> posts = this.postRepository.findByCategory(cat);
        return posts.stream().map((post-> this.modelMapper.map(post, PostDTO.class))).toList();
    }

    @Override
    public List<PostDTO> getPostsByUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user Id", userId));
        List<Post> posts = this.postRepository.findAllByUser(user);

        return posts.stream().map((post -> this.modelMapper.map(post, PostDTO.class))).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        List<Post> posts = this.postRepository.searchByTitle(keyword);
        return posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class)).toList();
    }
}
