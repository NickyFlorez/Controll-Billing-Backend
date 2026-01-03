package com.nicky.controlBilling.domain.use_case.user;

import com.nicky.controlBilling.domain.exceptions.UserAlreadyExistsException;
import com.nicky.controlBilling.domain.model.Role;
import com.nicky.controlBilling.domain.model.User;
import com.nicky.controlBilling.domain.port.user.UserPort;

import java.util.ArrayList;

public class RegisterUserUseCase {

    private final UserPort userPort;

    public RegisterUserUseCase(UserPort userPort) {
        this.userPort = userPort;
    }

    public User execute(String fullName, String email, String password, Role role) {
        if (fullName == null || email == null || password == null || role == null) {
            throw new IllegalArgumentException("data couldn't be null");
        }

        if (this.userPort.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("User is already registered.");
        }

        return this.userPort.save(
                new User(null, fullName, email, password, role, new ArrayList<>())
        );
    }
}
