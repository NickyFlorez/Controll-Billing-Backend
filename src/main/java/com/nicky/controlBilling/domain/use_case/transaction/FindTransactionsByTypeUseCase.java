package com.nicky.controlBilling.domain.use_case.transaction;

import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.model.TransactionType;
import com.nicky.controlBilling.domain.port.transaction.TransactionPort;

import java.util.List;
import java.util.UUID;

public class FindTransactionsByTypeUseCase {

    private final TransactionPort transactionPort;

    public FindTransactionsByTypeUseCase(TransactionPort transactionPort) {
        this.transactionPort = transactionPort;
    }

    public List<Transaction> execute(TransactionType type, UUID userId){
        return this.transactionPort.findTransactionsByTypeAndUserId(type, userId);
    }
}
