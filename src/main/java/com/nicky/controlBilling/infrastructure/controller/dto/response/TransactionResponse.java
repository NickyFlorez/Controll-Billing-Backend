package com.nicky.controlBilling.infrastructure.controller.dto.response;

import com.nicky.controlBilling.domain.model.Month;
import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.model.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        String title,
        String description,
        BigDecimal value,
        Month month,
        TransactionType type,
        AccountResponse account
) {

    public static TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.id(),
                transaction.title(),
                transaction.description(),
                transaction.value(),
                transaction.month(),
                transaction.type(),
                new AccountResponse(
                        transaction.account().id(),
                        transaction.account().bank(),
                        transaction.account().logoUrl(),
                        transaction.account().numberAccount()
                )
        );
    }
}
