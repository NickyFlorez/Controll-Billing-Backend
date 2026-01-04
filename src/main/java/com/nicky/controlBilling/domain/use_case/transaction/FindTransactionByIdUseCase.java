package com.nicky.controlBilling.domain.use_case.transaction;

import com.nicky.controlBilling.domain.exceptions.TransactionNotFoundException;
import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.port.transaction.TransactionPort;

import java.util.UUID;

public class FindTransactionByIdUseCase {

    private final TransactionPort transactionPort;

    public FindTransactionByIdUseCase(TransactionPort transactionPort) {
        this.transactionPort = transactionPort;
    }

    public Transaction execute(UUID id) {
        return this.transactionPort.findTransactionsById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
    }
}
