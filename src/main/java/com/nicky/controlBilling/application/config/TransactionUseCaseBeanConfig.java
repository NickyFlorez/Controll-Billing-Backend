package com.nicky.controlBilling.application.config;

import com.nicky.controlBilling.domain.use_case.transaction.*;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter.TransactionRepositoryAdapter;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionUseCaseBeanConfig {
    @Bean
    FindTransactionByIdUseCase findTransactionByIdUseCase(TransactionRepositoryAdapter transactionRepositoryAdapter){
        return new FindTransactionByIdUseCase(transactionRepositoryAdapter);
    }

    @Bean
    SaveTransactionUseCase saveTransactionUseCase(TransactionRepositoryAdapter transactionRepositoryAdapter, UserRepositoryAdapter userRepositoryAdapter){
        return new SaveTransactionUseCase(transactionRepositoryAdapter, userRepositoryAdapter);
    }

    @Bean
    FindByFilterUseCase findByFilterUseCase(TransactionRepositoryAdapter transactionRepositoryAdapter){
        return new FindByFilterUseCase(transactionRepositoryAdapter);
    }
}
