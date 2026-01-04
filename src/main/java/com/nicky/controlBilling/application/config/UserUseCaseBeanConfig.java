package com.nicky.controlBilling.application.config;

import com.nicky.controlBilling.domain.use_case.user.LoginUserUseCase;
import com.nicky.controlBilling.domain.use_case.user.RegisterUserUseCase;
import com.nicky.controlBilling.infrastructure.driven_adapters.security.credentials.SecurityAdapter;
import com.nicky.controlBilling.infrastructure.driven_adapters.security.jwt.JwtAdapter;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseBeanConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepositoryAdapter userRepositoryAdapter, SecurityAdapter securityAdapter){
        return new RegisterUserUseCase(userRepositoryAdapter, securityAdapter);
    }

    @Bean
    public LoginUserUseCase loginUserUseCase(UserRepositoryAdapter userRepositoryAdapter, JwtAdapter jwtAdapter, SecurityAdapter securityAdapter){
        return new LoginUserUseCase(userRepositoryAdapter, jwtAdapter, securityAdapter);
    }
}
