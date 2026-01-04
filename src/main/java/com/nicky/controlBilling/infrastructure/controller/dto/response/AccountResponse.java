package com.nicky.controlBilling.infrastructure.controller.dto.response;

import com.nicky.controlBilling.domain.model.Account;

import java.util.UUID;

public record AccountResponse(
        UUID id,
        String bank,
        String logoUrl,
        String numberAccount
) {

    public static AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.id(),
                account.bank(),
                account.logoUrl(),
                account.numberAccount()
        );
    }
}
