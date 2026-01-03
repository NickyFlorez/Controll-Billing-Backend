package com.nicky.controlBilling.domain.port.authentication;

public interface TokenPort {
    String generateToken(String username, String role);
}
