package com.example.restfulblogpost.services;

import com.example.restfulblogpost.DTO.UserDTO;
import com.example.restfulblogpost.entities.User;

import java.util.List;

public interface UserService {

    UserDTO registerUser(UserDTO user);
    UserDTO editUserDetails(UserDTO user, Long userId);
    UserDTO getUserById (Long userId);
    List<UserDTO> getAllUsers();
    void deleteUser(Long userId);
    User logInUser(User userDTO);
}
