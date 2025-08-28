package com.emcode.aikinetics.api.dto.sporttype;

import lombok.Builder;

@Builder
public record SportTypeResponse(Long id, String keyName, Long accountId) {
}
