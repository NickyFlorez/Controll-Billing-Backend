package com.nicky.controlBilling.application.config;

import com.nicky.controlBilling.domain.use_case.user.LoginUserUseCase;
import com.nicky.controlBilling.domain.use_case.user.RegisterUserUseCase;
import com.nicky.controlBilling.infrastructure.driven_adapters.jwt.adapter.JwtAdapter;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseBeanConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepositoryAdapter userRepositoryAdapter){
        return new RegisterUserUseCase(userRepositoryAdapter);
    }

    @Bean
    public LoginUserUseCase loginUserUseCase(UserRepositoryAdapter userRepositoryAdapter, JwtAdapter jwtAdapter){
        return new LoginUserUseCase(userRepositoryAdapter, jwtAdapter);
    }
}
