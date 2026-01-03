package com.nicky.controlBilling.domain.port.transaction;

import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.model.TransactionType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionPort {
    Optional<Transaction> findTransactionsById(UUID id);
    List<Transaction> findAllTransactionsByUserId(UUID userId);
    Transaction saveTransaction(Transaction transaction);
    List<Transaction> findTransactionsByTypeAndUserId(TransactionType type, UUID userId);
}
