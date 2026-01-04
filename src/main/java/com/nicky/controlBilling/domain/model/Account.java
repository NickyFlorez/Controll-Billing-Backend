package com.nicky.controlBilling.domain.model;

import java.util.List;
import java.util.UUID;

public record Account(
        UUID id,
        String bank,
        String logoUrl,
        String numberAccount,
        User user,
        List<Transaction> transactions
) {
}
