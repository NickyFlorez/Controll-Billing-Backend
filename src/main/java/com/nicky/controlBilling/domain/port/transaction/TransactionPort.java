package com.nicky.controlBilling.domain.port.transaction;

import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.model.TransactionFilter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionPort {
    List<Transaction> findTransactionsByFilter(TransactionFilter filter);
    Optional<Transaction> findTransactionsById(UUID id);
    Transaction saveTransaction(Transaction transaction);
}
