package com.nicky.controlBilling.infrastructure.controller.exception;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.nicky.controlBilling.domain.exceptions.AccountNotFoundException;
import com.nicky.controlBilling.domain.exceptions.PasswordDontMatchException;
import com.nicky.controlBilling.domain.exceptions.UserAlreadyExistsException;
import com.nicky.controlBilling.domain.exceptions.UserNotFoundException;
import com.nicky.controlBilling.infrastructure.controller.dto.response.ApiResponse;
import jakarta.validation.UnexpectedTypeException;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<@NonNull ApiResponse> userAlreadyExistsExceptionHandler(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse(
                        HttpStatus.NOT_FOUND.name(),
                        null,
                        exception.getMessage(),
                        true
                )
        );
    }

    @ExceptionHandler(PasswordDontMatchException.class)
    public ResponseEntity<@NonNull ApiResponse> passwordDontMatchExceptionHandler(PasswordDontMatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponse(
                        HttpStatus.NOT_FOUND.name(),
                        null,
                        exception.getMessage(),
                        true
                )
        );
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<@NonNull ApiResponse> signatureVerificationExceptionHandler(SignatureVerificationException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ApiResponse(
                        HttpStatus.FORBIDDEN.name(),
                        null,
                        exception.getMessage(),
                        true
                )
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<@NonNull ApiResponse> userNotFoundExceptionHandler(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse(
                        HttpStatus.NOT_FOUND.name(),
                        null,
                        exception.getMessage(),
                        true
                )
        );
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<@NonNull ApiResponse> accountNotFoundExceptionHandler(AccountNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse(
                        HttpStatus.NOT_FOUND.name(),
                        null,
                        exception.getMessage(),
                        true
                )
        );
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<@NonNull ApiResponse> unexpectedTypeExceptionHandler(UnexpectedTypeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponse(
                        HttpStatus.BAD_REQUEST.name(),
                        null,
                        exception.getMessage(),
                        true
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<@NonNull ApiResponse> handleValidationErrors(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        String errorMessage = errors.entrySet()
                .stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ApiResponse(
                                HttpStatus.BAD_REQUEST.name(),
                                null,
                                errorMessage,
                                true
                        )
                );
    }
}
