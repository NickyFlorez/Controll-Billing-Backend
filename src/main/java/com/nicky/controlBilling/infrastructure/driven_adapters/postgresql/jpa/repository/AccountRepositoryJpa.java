package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.repository;

import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity.AccountDbo;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AccountRepositoryJpa extends JpaRepository<@NonNull AccountDbo, @NonNull UUID>, JpaSpecificationExecutor<@NonNull AccountDbo> {
}
