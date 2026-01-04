package com.nicky.controlBilling.domain.use_case.account;

import com.nicky.controlBilling.domain.model.Account;
import com.nicky.controlBilling.domain.model.AccountFilter;
import com.nicky.controlBilling.domain.port.account.AccountPort;

import java.util.List;

public class FindAccountsByFilterUseCase {

    private final AccountPort accountPort;

    public FindAccountsByFilterUseCase(AccountPort accountPort) {
        this.accountPort = accountPort;
    }

    public List<Account> execute(AccountFilter filter){
        return this.accountPort.findAccountsByFilter(filter);
    }
}
