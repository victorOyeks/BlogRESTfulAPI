package com.example.restfulblogpost.repositories;

import com.example.restfulblogpost.entities.Category;
import com.example.restfulblogpost.entities.Post;
import com.example.restfulblogpost.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);
    List<Post> findByCategory(Category category);

//    @Query("select p from Post p where p.title like : key")
    List<Post> searchByTitle(String title);

}
