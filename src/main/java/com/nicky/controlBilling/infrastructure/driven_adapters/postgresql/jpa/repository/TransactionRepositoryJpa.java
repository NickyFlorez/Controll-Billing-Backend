package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.repository;

import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity.TransactionDbo;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TransactionRepositoryJpa extends JpaRepository<@NonNull TransactionDbo,@NonNull UUID>, JpaSpecificationExecutor<@NonNull TransactionDbo> {
}
