package com.nicky.controlBilling.infrastructure.controller.dto.request;

import java.util.UUID;

public record CreateAccountDto(
        UUID id,
        String bank,
        String logoUrl,
        String numberAccount,
        UUID userId
) {
}
