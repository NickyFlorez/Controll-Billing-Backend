package com.nicky.controlBilling.infrastructure.controller.dto.request;

import com.nicky.controlBilling.domain.model.Role;

public record RegisterUserDto(
        String fullName,
        String email,
        String password,
        Role role
) {
}
