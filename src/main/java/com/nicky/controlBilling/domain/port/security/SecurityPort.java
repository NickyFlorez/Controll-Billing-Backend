package com.nicky.controlBilling.domain.port.security;

public interface SecurityPort {
    String encryptPassword(String rawPassword);
    boolean checkEqualsPasswords(String rawPassword, String encryptPassword);
}
