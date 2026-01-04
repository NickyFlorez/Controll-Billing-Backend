package com.nicky.controlBilling.infrastructure.controller.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record CreateAccountDto(
        @Min(value = 2, message = "Bank name must be at least 2 characters")
        @Pattern(regexp = "^[A-Za-z0-9ÁÉÍÓÚÜÑáéíóúüñ ]{2,}$", message = "Bank name should be only alphanumeric")
        @NotBlank
        String bank,
        String logoUrl,
        @NotBlank
        @Pattern(regexp = "^[0-9-]{4,}$", message = "Number account should be only numbers")
        String numberAccount,
        UUID userId
) {
}
