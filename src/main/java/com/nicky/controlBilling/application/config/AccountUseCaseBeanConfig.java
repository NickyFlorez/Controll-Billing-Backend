package com.nicky.controlBilling.application.config;

import com.nicky.controlBilling.domain.use_case.account.FindAccountByIdUseCase;
import com.nicky.controlBilling.domain.use_case.account.FindAccountsByFilterUseCase;
import com.nicky.controlBilling.domain.use_case.account.SaveAccountUseCase;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter.AccountRepositoryAdapter;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountUseCaseBeanConfig {

    @Bean
    FindAccountByIdUseCase findAccountByIdUseCase(AccountRepositoryAdapter accountRepositoryAdapter){
        return new FindAccountByIdUseCase(accountRepositoryAdapter);
    }

    @Bean
    FindAccountsByFilterUseCase findAccountsByFilterUseCase(AccountRepositoryAdapter accountRepositoryAdapter){
        return new FindAccountsByFilterUseCase(accountRepositoryAdapter);
    }

    @Bean
    SaveAccountUseCase saveAccountUseCase(AccountRepositoryAdapter accountRepositoryAdapter, UserRepositoryAdapter userRepositoryAdapter){
        return new SaveAccountUseCase(accountRepositoryAdapter, userRepositoryAdapter);
    }
}
