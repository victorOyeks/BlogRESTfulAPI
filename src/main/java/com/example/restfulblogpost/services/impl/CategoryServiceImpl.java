package com.example.restfulblogpost.services.impl;

import com.example.restfulblogpost.entities.Category;
import com.example.restfulblogpost.exceptions.ResourceNotFoundException;
import com.example.restfulblogpost.DTO.CategoryDTO;
import com.example.restfulblogpost.repositories.CategoryRepository;
import com.example.restfulblogpost.services.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDTO createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = this.modelMapper.map(categoryDTO, Category.class);
        Category addedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(addedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));

        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());

        Category updatedCategory = this.categoryRepository.save(category);

        return this.modelMapper.map(updatedCategory, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO getCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));

        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories.stream().map((category -> this.modelMapper.map(category, CategoryDTO.class))).collect(Collectors.toList());
    }
}