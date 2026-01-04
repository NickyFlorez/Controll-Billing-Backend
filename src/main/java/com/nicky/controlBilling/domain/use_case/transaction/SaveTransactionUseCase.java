package com.nicky.controlBilling.domain.use_case.transaction;

import com.nicky.controlBilling.domain.exceptions.AccountNotFoundException;
import com.nicky.controlBilling.domain.exceptions.UserNotFoundException;
import com.nicky.controlBilling.domain.model.Account;
import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.model.User;
import com.nicky.controlBilling.domain.port.account.AccountPort;
import com.nicky.controlBilling.domain.port.transaction.TransactionPort;
import com.nicky.controlBilling.domain.port.user.UserPort;

public class SaveTransactionUseCase {

    private final TransactionPort transactionPort;
    private final UserPort userPort;
    private final AccountPort accountPort;

    public SaveTransactionUseCase(TransactionPort transactionPort, UserPort userPort, AccountPort accountPort) {
        this.transactionPort = transactionPort;
        this.userPort = userPort;
        this.accountPort = accountPort;
    }

    public Transaction execute(Transaction transaction){
        User user = this.userPort.findById(transaction.user().id()).orElseThrow(() -> new UserNotFoundException("User not found"));
        Account account = this.accountPort.findAccountById(transaction.account().id()).orElseThrow(() -> new AccountNotFoundException("Account not " +
                "found"));

        Transaction transactionToSave = new Transaction(
                transaction.id(),
                transaction.title(),
                transaction.description(),
                transaction.value(),
                transaction.month(),
                transaction.type(),
                user,
                account
        );
        return this.transactionPort.saveTransaction(transactionToSave);
    }
}
