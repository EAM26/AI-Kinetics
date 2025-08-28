package com.emcode.aikinetics.account;

import lombok.Builder;

@Builder
public record AccountResponse(
        Long id,
        String name,
        String email) {


}
