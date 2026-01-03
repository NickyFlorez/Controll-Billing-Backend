package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.repository;

import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryJpa extends JpaRepository<UserDbo, UUID> {

    Optional<UserDbo> findByEmail(String email);
}
