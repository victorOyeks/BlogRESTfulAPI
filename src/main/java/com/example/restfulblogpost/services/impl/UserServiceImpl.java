package com.example.restfulblogpost.services.impl;

import com.example.restfulblogpost.entities.User;
import com.example.restfulblogpost.exceptions.ResourceNotFoundException;
import com.example.restfulblogpost.DTO.UserDTO;
import com.example.restfulblogpost.repositories.UserRepository;
import com.example.restfulblogpost.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = this.dtoToUser(userDTO);
        User savedUser = this.userRepository.save(user);
        return this.userToDTO(savedUser);
    }

    @Override
    public User logInUser(User user) {

        return userRepository.findByNameAndPassword(user.getEmail(), user.getPassword());
    }

    @Override
    public UserDTO editUserDetails(UserDTO userDTO, Long userId) {

        User user = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        User updateUser = this.userRepository.save(user);

        return this.userToDTO(updateUser);
    }

    @Override
    public UserDTO getUserById(Long userId) {

        User user = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));

        return this.userToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepository.findAll();

        List<UserDTO> userDTOs = users.stream().map(user -> this.userToDTO(user)).collect(Collectors.toList());
        return userDTOs;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        this.userRepository.delete(user);
    }

    public User dtoToUser (UserDTO userDTO){
        return this.modelMapper.map(userDTO, User.class);
    }

    public UserDTO userToDTO (User user) {
        return this.modelMapper.map(user, UserDTO.class);
    }

}
