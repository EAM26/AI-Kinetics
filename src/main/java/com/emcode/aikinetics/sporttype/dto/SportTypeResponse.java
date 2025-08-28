package com.emcode.aikinetics.sporttype.dto;

import lombok.Builder;

@Builder
public record SportTypeResponse(Long id, String keyName, Long accountId) {
}
