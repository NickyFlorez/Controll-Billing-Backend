package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.repository;

import com.nicky.controlBilling.domain.model.TransactionType;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity.TransactionDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepositoryJpa extends JpaRepository<TransactionDbo, UUID> {
    List<TransactionDbo> findByUserId(UUID userId);
    List<TransactionDbo> findByTypeAndUserId(TransactionType type, UUID userId);
}
