package com.nicky.controlBilling.domain.model;

import java.util.UUID;

public record TransactionFilter(
        UUID userId,
        TransactionType type,
        Month month
) {
    public TransactionFilter {
        if (userId == null) {
            throw new IllegalArgumentException("userId is required");
        }
    }
}
