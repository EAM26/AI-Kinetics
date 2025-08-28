package com.emcode.aikinetics.sporttype.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SportTypeRequest(
        @Size(min = 3, max = 25, message = "Type name must be between 3 and 25 characters long.")
        String keyName,
        @NotNull
        Long accountId) {
}
