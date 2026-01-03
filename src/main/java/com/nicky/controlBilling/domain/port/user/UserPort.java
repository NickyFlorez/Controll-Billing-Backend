package com.nicky.controlBilling.domain.port.user;

import com.nicky.controlBilling.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserPort {
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
    User save(User user);
}
