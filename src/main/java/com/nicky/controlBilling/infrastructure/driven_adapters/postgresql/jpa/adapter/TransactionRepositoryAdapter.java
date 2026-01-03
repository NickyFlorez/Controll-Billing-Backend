package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter;

import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.model.TransactionType;
import com.nicky.controlBilling.domain.port.transaction.TransactionPort;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity.TransactionDbo;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.repository.TransactionRepositoryJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TransactionRepositoryAdapter implements TransactionPort {

    private final TransactionRepositoryJpa transactionRepositoryJpa;

    @Override
    public Optional<Transaction> findTransactionsById(UUID id) {
        return this.transactionRepositoryJpa.findById(id).map(TransactionDbo::toDomain);
    }

    @Override
    public List<Transaction> findAllTransactionsByUserId(UUID userId) {
        return this.transactionRepositoryJpa.findByUserId(userId).stream()
                .map(TransactionDbo::toDomain).toList();
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return this.transactionRepositoryJpa.save(TransactionDbo.fromDomain(transaction)).toDomain();
    }

    @Override
    public List<Transaction> findTransactionsByTypeAndUserId(TransactionType type, UUID userId) {
        return this.transactionRepositoryJpa.findByTypeAndUserId(type, userId).stream().map(TransactionDbo::toDomain).toList();
    }
}
