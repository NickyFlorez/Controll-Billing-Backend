package com.nicky.controlBilling.domain.port.account;

import com.nicky.controlBilling.domain.model.Account;
import com.nicky.controlBilling.domain.model.AccountFilter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountPort {
    List<Account> findAccountsByFilter(AccountFilter filter);
    Optional<Account> findAccountById(UUID id);
    Account saveAccount(Account transaction);
}
