package com.emcode.aikinetics.api.dto.account;

import lombok.Builder;

@Builder
public record AccountResponse(
        Long id,
        String name,
        String email) {


}
