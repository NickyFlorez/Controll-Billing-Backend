package com.nicky.controlBilling.domain.use_case.account;

import com.nicky.controlBilling.domain.exceptions.UserNotFoundException;
import com.nicky.controlBilling.domain.model.Account;
import com.nicky.controlBilling.domain.port.account.AccountPort;
import com.nicky.controlBilling.domain.port.user.UserPort;

public class SaveAccountUseCase {

    private final AccountPort accountPort;
    private final UserPort userPort;

    public SaveAccountUseCase(AccountPort accountPort, UserPort userPort) {
        this.accountPort = accountPort;
        this.userPort = userPort;
    }

    public Account execute(Account account){
        this.userPort.findById(account.user().id()).orElseThrow(() -> new UserNotFoundException("User not found"));
        return this.accountPort.saveAccount(account);
    }
}
