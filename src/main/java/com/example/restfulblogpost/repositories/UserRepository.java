package com.example.restfulblogpost.repositories;

import com.example.restfulblogpost.DTO.UserDTO;
import com.example.restfulblogpost.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByNameAndPassword(String email, String password);
}
