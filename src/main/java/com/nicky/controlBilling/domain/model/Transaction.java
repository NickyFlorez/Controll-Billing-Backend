package com.nicky.controlBilling.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Transaction(
        UUID id,
        String title,
        String description,
        BigDecimal value,
        Month month,
        TransactionType type,
        User user
) {
}
