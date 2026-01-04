package com.nicky.controlBilling.infrastructure.controller.exception;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.nicky.controlBilling.domain.exceptions.PasswordDontMatchException;
import com.nicky.controlBilling.domain.exceptions.UserAlreadyExistsException;
import com.nicky.controlBilling.domain.exceptions.UserNotFoundException;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<@NonNull String> userAlreadyExistsExceptionHandler(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(PasswordDontMatchException.class)
    public ResponseEntity<@NonNull String> passwordDontMatchExceptionHandler(PasswordDontMatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<@NonNull String> signatureVerificationExceptionHandler(SignatureVerificationException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<@NonNull String> userNotFoundExceptionHandler(UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFoundException.getMessage());
    }
}
