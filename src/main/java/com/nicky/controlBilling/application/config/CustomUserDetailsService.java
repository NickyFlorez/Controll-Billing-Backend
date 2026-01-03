package com.nicky.controlBilling.application.config;

import com.nicky.controlBilling.domain.exceptions.UserNotFoundException;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity.UserDbo;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.repository.UserRepositoryJpa;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositoryJpa userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDbo user = this.userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found"));

        List<GrantedAuthority> userRole = List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole())
        );

        return new User(
                user.getEmail(),
                user.getPassword(),
                userRole
        );
    }
}
