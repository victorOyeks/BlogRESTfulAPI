package com.example.restfulblogpost.services;

import com.example.restfulblogpost.DTO.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);

    void deleteCategory (Long categoryId);

    CategoryDTO getCategory (Long categoryId);

    List<CategoryDTO> getCategories();


}
