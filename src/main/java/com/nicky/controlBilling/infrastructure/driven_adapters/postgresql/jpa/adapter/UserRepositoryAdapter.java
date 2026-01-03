package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter;

import com.nicky.controlBilling.domain.model.User;
import com.nicky.controlBilling.domain.port.user.UserPort;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity.UserDbo;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.repository.UserRepositoryJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserRepositoryAdapter implements UserPort {

    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepositoryJpa.findByEmail(email).map(UserDbo::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return this.userRepositoryJpa.findById(id).map(UserDbo::toDomain);
    }

    @Override
    public User save(User user) {
        return this.userRepositoryJpa.save(UserDbo.fromDomain(user)).toDomain();
    }
}
