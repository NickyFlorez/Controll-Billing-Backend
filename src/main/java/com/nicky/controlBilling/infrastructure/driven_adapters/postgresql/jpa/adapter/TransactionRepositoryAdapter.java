package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter;

import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.model.TransactionFilter;
import com.nicky.controlBilling.domain.port.transaction.TransactionPort;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity.TransactionDbo;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.repository.TransactionRepositoryJpa;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;
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
    public Transaction saveTransaction(Transaction transaction) {
        return this.transactionRepositoryJpa.save(TransactionDbo.fromDomain(transaction)).toDomain();
    }

    @Override
    public List<Transaction> findTransactionsByFilter(TransactionFilter filter) {

        Specification<@NonNull TransactionDbo> spec = Specification
                .where(TransactionJpaSpecifications.byUserId(filter.userId()))
                .and(TransactionJpaSpecifications.byType(filter.type()))
                .and(TransactionJpaSpecifications.byMonth(filter.month()));

        return this.transactionRepositoryJpa.findAll(spec)
                .stream()
                .map(TransactionDbo::toDomain)
                .toList();
    }
}
