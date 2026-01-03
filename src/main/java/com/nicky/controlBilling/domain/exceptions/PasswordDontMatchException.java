package com.nicky.controlBilling.domain.exceptions;

public class PasswordDontMatchException extends RuntimeException {
    public PasswordDontMatchException(String message) {
        super(message);
    }
}
