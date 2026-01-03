package com.nicky.controlBilling.application.config;

import com.nicky.controlBilling.domain.use_case.transaction.FindAllTransactionsByUserIdUseCase;
import com.nicky.controlBilling.domain.use_case.transaction.FindTransactionByIdUseCase;
import com.nicky.controlBilling.domain.use_case.transaction.FindTransactionsByTypeUseCase;
import com.nicky.controlBilling.domain.use_case.transaction.SaveTransactionUseCase;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter.TransactionRepositoryAdapter;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionUseCaseBeanConfig {

    @Bean
    FindAllTransactionsByUserIdUseCase findAllTransactionsByUserIdUseCase(UserRepositoryAdapter userRepositoryAdapter,
                                                                          TransactionRepositoryAdapter transactionRepositoryAdapter){
        return new FindAllTransactionsByUserIdUseCase(transactionRepositoryAdapter, userRepositoryAdapter);
    }

    @Bean
    FindTransactionByIdUseCase findTransactionByIdUseCase(TransactionRepositoryAdapter transactionRepositoryAdapter){
        return new FindTransactionByIdUseCase(transactionRepositoryAdapter);
    }

    @Bean
    SaveTransactionUseCase saveTransactionUseCase(TransactionRepositoryAdapter transactionRepositoryAdapter, UserRepositoryAdapter userRepositoryAdapter){
        return new SaveTransactionUseCase(transactionRepositoryAdapter, userRepositoryAdapter);
    }

    @Bean
    FindTransactionsByTypeUseCase findTransactionsByTypeUseCase(TransactionRepositoryAdapter transactionRepositoryAdapter){
        return new FindTransactionsByTypeUseCase(transactionRepositoryAdapter);
    }
}
