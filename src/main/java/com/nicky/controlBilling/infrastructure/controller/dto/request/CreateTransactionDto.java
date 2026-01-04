package com.nicky.controlBilling.infrastructure.controller.dto.request;

import com.nicky.controlBilling.domain.model.Month;
import com.nicky.controlBilling.domain.model.TransactionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTransactionDto(
        @Min(value = 3, message = "Title must be at least 3 characters")
        @Pattern(regexp = "^[A-Za-z0-9ÁÉÍÓÚÜÑáéíóúüñ ]{3,}$", message = "Title should be only alphanumeric")
        @NotBlank
        String title,

        @Min(value = 10, message = "Description must be at least 10 characters")
        @Pattern(regexp = "^[A-Za-z0-9ÁÉÍÓÚÜÑáéíóúüñ ]{3,}$", message = "Description should be only alphanumeric")
        @NotBlank
        String description,

        @Min(value = 0, message = "Value must be greater than 0")
        @NotBlank
        BigDecimal value,
        Month month,
        TransactionType type,
        UUID userId,
        UUID accountId
) {
}
