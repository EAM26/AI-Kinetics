package com.emcode.aikinetics.api.dto.sporttype;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SportTypeRequest(@NotBlank String keyName, @NotNull Long accountId) {
}
