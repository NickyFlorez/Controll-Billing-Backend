package com.nicky.controlBilling.infrastructure.controller.dto.response;

import com.nicky.controlBilling.domain.model.Role;
import com.nicky.controlBilling.domain.model.User;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String fullName,
        String email,
        String password,
        Role role
) {


    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.id(),
                user.fullName(),
                user.email(),
                user.password(),
                user.role()
        );
    }
}
