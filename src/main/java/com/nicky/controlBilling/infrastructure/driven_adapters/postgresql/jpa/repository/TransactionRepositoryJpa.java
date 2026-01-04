package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.repository;

import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity.TransactionDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TransactionRepositoryJpa extends JpaRepository<TransactionDbo, UUID>, JpaSpecificationExecutor<TransactionDbo> {
}
