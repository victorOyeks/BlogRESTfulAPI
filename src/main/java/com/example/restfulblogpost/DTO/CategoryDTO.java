package com.example.restfulblogpost.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Long categoryId;

    @NotBlank
    @Size(min = 4, message = "Min. size of category title is 4")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10, message = "Min siz of category description is 10")
    private String categoryDescription;
}
