package com.example.restfulblogpost.controllers;

import com.example.restfulblogpost.DTO.ApiResponse;
import com.example.restfulblogpost.DTO.PostDTO;
import com.example.restfulblogpost.DTO.UserDTO;
import com.example.restfulblogpost.entities.User;
import com.example.restfulblogpost.services.PostService;
import com.example.restfulblogpost.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @PostMapping("/")
    private ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createUserDTO = this.userService.registerUser(userDTO);
        return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> homeController(@RequestBody User user, HttpServletRequest httpServletRequest){
        User userDTO1 = userService.logInUser(user);
        if(userDTO1 == null || !user.getPassword().equals(userDTO1.getPassword())){
            System.out.println("Login failed: email or password not correct");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or password not correct");
        }
            System.out.println("Login successful: user = " + userDTO1);
            List<PostDTO> posts = this.postService.getAllPost();
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("userId", posts);
            return ResponseEntity.ok(posts);
        }


    @GetMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpSession session) {
        session.removeAttribute("userId");
        return new ResponseEntity<>(new ApiResponse("Logged out!!!", true), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long userId) {
        UserDTO updatedUser =  this.userService.editUserDetails(userDTO, userId);
        return  ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public  ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Long uid) {
        this.userService.deleteUser(uid);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getSingleUsers(@PathVariable Long userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
}
