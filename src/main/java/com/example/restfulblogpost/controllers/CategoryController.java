package com.example.restfulblogpost.controllers;

import com.example.restfulblogpost.DTO.ApiResponse;
import com.example.restfulblogpost.DTO.CategoryDTO;
import com.example.restfulblogpost.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory (@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createCategory = this.categoryService.createCategory(categoryDTO);

        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDTO> updateCategory (@Valid @RequestBody CategoryDTO categoryDTO,
                                                       @PathVariable Long catId) {
        CategoryDTO updatedCategory = this.categoryService.updateCategory(categoryDTO, catId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory (@PathVariable Long catId) {
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<>(new ApiResponse("Category is deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity <CategoryDTO> getCategory(@PathVariable Long catId) {
        CategoryDTO categoryDTO = this.categoryService.getCategory(catId);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getCategory () {
        List<CategoryDTO> categories = this.categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }
}
