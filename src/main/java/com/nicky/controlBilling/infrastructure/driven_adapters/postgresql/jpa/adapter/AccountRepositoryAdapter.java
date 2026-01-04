package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter;

import com.nicky.controlBilling.domain.model.Account;
import com.nicky.controlBilling.domain.model.AccountFilter;
import com.nicky.controlBilling.domain.port.account.AccountPort;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity.AccountDbo;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.repository.AccountRepositoryJpa;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AccountRepositoryAdapter implements AccountPort {

    private final AccountRepositoryJpa accountRepositoryJpa;

    @Override
    public List<Account> findAccountsByFilter(AccountFilter filter) {

        Specification<@NonNull AccountDbo> spec = Specification
                .where(AccountJpaSpecifications.byUserId(filter.userId()))
                .and(AccountJpaSpecifications.byBank(filter.bank()));

        return this.accountRepositoryJpa.findAll(spec)
                .stream()
                .map(AccountDbo::toDomain)
                .toList();
    }

    @Override
    public Optional<Account> findAccountById(UUID id) {
        return this.accountRepositoryJpa.findById(id).map(AccountDbo::toDomain);
    }

    @Override
    public Account saveAccount(Account transaction) {
        return this.accountRepositoryJpa.save(AccountDbo.fromDomain(transaction)).toDomain();
    }
}
