package com.example.restfulblogpost.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotEmpty
    @Size(min = 4, message = "Username must be minimum of 4 characters")
    private String name;

    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password should not be less than 3 characters and more than 10 characters")
    private String password;

    @NotNull
    private String about;
}
