package com.nicky.controlBilling.infrastructure.driven_adapters.security.credentials;

import com.nicky.controlBilling.domain.port.security.SecurityPort;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SecurityAdapter implements SecurityPort {

    BCryptPasswordEncoder passwordEncoder;

    public SecurityAdapter() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean checkEqualsPasswords(String rawPassword, String encryptPassword) {
        return passwordEncoder.matches(rawPassword, encryptPassword);
    }
}
