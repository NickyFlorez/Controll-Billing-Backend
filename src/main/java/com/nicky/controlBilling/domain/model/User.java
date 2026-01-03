package com.nicky.controlBilling.domain.model;

import java.util.List;
import java.util.UUID;

public record User(
        UUID id,
        String fullName,
        String email,
        String password,
        Role role,
        List<Transaction> transactions
) {
}
