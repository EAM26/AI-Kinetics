package com.emcode.aikinetics.api.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountRequest {

    @NotBlank(message = "Name should not be empty.")
    @Size(min = 3, message = "Name should contain three or more characters.")
    private String name;

    @Email
    private String email;

}
