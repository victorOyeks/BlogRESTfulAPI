package com.example.restfulblogpost.controllers;

import com.example.restfulblogpost.DTO.ApiResponse;
import com.example.restfulblogpost.DTO.PostDTO;
import com.example.restfulblogpost.services.FileService;
import com.example.restfulblogpost.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,
                                              @PathVariable Long userId,
                                              @PathVariable Long categoryId){
        PostDTO createPost = this.postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Long userId) {
        List<PostDTO> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Long categoryId) {
        List<PostDTO> posts = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPost() {
        List<PostDTO> allPost = this.postService.getAllPost();
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

//    @GetMapping("/posts")
//    public ResponseEntity<List<PostDTO>> getAllPost(
//            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = "5", required = false)Integer pageSize
//    ) {
//         List<PostDTO> allPost = this.postService.getAllPost(pageNumber, pageSize);
//         return new ResponseEntity<>(allPost, HttpStatus.OK);
//    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
        PostDTO postDTO = this.postService.getPostById(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Long postId) {
        this.postService.deletePost(postId);
        return new ApiResponse("Post is deleted successfully", true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost (@RequestBody PostDTO postDTO, @PathVariable Long postId) {
        PostDTO updatePost = this.postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable("keywords") String keywords) {
        List<PostDTO> result = this.postService.searchPosts(keywords);
        return  new ResponseEntity<List<PostDTO>>(result, HttpStatus.OK);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image")MultipartFile image, @PathVariable Long postId) throws IOException {
        PostDTO postDTO = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDTO.setImageName(fileName);
        PostDTO updatePost = this.postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }
}