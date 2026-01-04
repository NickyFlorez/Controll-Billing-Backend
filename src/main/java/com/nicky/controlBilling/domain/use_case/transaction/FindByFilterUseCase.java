package com.nicky.controlBilling.domain.use_case.transaction;

import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.model.TransactionFilter;
import com.nicky.controlBilling.domain.port.transaction.TransactionPort;

import java.util.List;

public class FindByFilterUseCase {

    private final TransactionPort transactionPort;

    public FindByFilterUseCase(TransactionPort transactionPort) {
        this.transactionPort = transactionPort;
    }

    public List<Transaction> execute(TransactionFilter filter) {
        return this.transactionPort.findTransactionsByFilter(filter);
    }
}
