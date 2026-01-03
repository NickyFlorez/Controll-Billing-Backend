package com.nicky.controlBilling.domain.use_case.transaction;

import com.nicky.controlBilling.domain.exceptions.UserNotFoundException;
import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.port.transaction.TransactionPort;
import com.nicky.controlBilling.domain.port.user.UserPort;

public class SaveTransactionUseCase {

    private final TransactionPort transactionPort;
    private final UserPort userPort;

    public SaveTransactionUseCase(TransactionPort transactionPort, UserPort userPort) {
        this.transactionPort = transactionPort;
        this.userPort = userPort;
    }

    public Transaction execute(Transaction transaction){
        this.userPort.findById(transaction.user().id()).orElseThrow(() -> new UserNotFoundException("User not found"));
        return this.transactionPort.saveTransaction(transaction);
    }
}
