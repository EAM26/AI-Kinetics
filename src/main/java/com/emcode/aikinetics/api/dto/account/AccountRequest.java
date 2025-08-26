package com.emcode.aikinetics.api.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AccountRequest(
        @Size(min = 3, message = "Name should contain three or more characters.")
        String name,
        @Email
        String email) {
}
