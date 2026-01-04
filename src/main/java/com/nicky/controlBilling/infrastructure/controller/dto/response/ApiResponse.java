package com.nicky.controlBilling.infrastructure.controller.dto.response;

public record ApiResponse(
        String status,
        Object body,
        String message,
        Boolean error
) {

    public ApiResponse(String status, Object body, String message) {
        this(status, body, message, null);
    }
}
