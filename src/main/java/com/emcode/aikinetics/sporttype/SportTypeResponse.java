package com.emcode.aikinetics.sporttype;

import lombok.Builder;

@Builder
public record SportTypeResponse(Long id, String keyName, Long accountId) {
}
