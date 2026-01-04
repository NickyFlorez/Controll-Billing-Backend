package com.nicky.controlBilling.domain.use_case.account;

import com.nicky.controlBilling.domain.exceptions.AccountNotFoundException;
import com.nicky.controlBilling.domain.model.Account;
import com.nicky.controlBilling.domain.port.account.AccountPort;

import java.util.UUID;

public class FindAccountByIdUseCase {

    private final AccountPort accountPort;

    public FindAccountByIdUseCase(AccountPort accountPort) {
        this.accountPort = accountPort;
    }

    public Account execute(UUID id){
        return this.accountPort.findAccountById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }
}
