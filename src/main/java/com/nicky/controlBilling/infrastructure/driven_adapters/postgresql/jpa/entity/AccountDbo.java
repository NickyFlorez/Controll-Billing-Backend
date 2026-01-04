package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity;
import com.nicky.controlBilling.domain.model.Account;
import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "accounts_table")
public class AccountDbo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String bank;
    private String logoUrl;
    private String numberAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDbo user;

    @OneToMany(
            mappedBy = "account",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TransactionDbo> transactions;

    public static AccountDbo fromDomain(Account account){
        return new AccountDbo(
                account.id(),
                account.bank(),
                account.logoUrl(),
                account.numberAccount(),
                new UserDbo(
                        account.user().id(),
                        null,
                        null,
                        null,
                        null,
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                account.transactions() != null ? account.transactions().stream().map(domain -> new TransactionDbo(
                        domain.id(),
                        domain.title(),
                        domain.description(),
                        domain.value(),
                        domain.month(),
                        domain.type(),
                        null,
                        null
                )).toList() : new ArrayList<>()
        );
    }

    public Account toDomain(){
        return new Account(
                getId(),
                getBank(),
                getLogoUrl(),
                getNumberAccount(),
                new User(
                        getUser().getId(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        new ArrayList<>()
                ),
                getTransactions() != null ? getTransactions().stream().map(dbo -> new Transaction(
                        dbo.getId(),
                        dbo.getTitle(),
                        dbo.getDescription(),
                        dbo.getValue(),
                        dbo.getMonth(),
                        dbo.getType(),
                        null,
                        null
                )).toList() : new ArrayList<>()
        );
    }
}
