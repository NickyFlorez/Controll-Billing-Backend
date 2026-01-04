package com.nicky.controlBilling.infrastructure.controller.dto.request;

public record LoginUserDto(
        String email,
        String password
) {
}
