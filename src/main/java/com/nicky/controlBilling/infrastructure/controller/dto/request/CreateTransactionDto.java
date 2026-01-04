package com.nicky.controlBilling.infrastructure.controller.dto.request;

import com.nicky.controlBilling.domain.model.Month;
import com.nicky.controlBilling.domain.model.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTransactionDto(
        String title,
        String description,
        BigDecimal value,
        Month month,
        TransactionType type,
        UUID userId,
        UUID accountId
) {
}
