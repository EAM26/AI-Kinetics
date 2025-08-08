package com.emcode.aikinetics.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountDto {

    @NotBlank(message = "Name should not be empty.")
    @Size(min = 3, message = "Name should contain three or more characters.")
    private String name;

    @Email
    private String email;

    @NotBlank(message = "Password should not be empty.")
    @Size(min = 8, message = "Password should contain eight or more characters.")
//    todo create password requirements with pattern
    private String password;

}
