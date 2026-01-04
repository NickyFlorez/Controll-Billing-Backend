package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity;

import com.nicky.controlBilling.domain.model.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "transactions_table")
public class TransactionDbo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private Month month;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDbo user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountDbo account;

    public static TransactionDbo fromDomain(Transaction transaction) {
        return new TransactionDbo(
                transaction.id(),
                transaction.title(),
                transaction.description(),
                transaction.value(),
                transaction.month(),
                transaction.type(),
                new UserDbo(
                        transaction.user().id(),
                        null,
                        null,
                        null,
                        null,
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                new AccountDbo(
                        transaction.account().id(),
                        transaction.account().bank(),
                        transaction.account().logoUrl(),
                        transaction.account().numberAccount(),
                        null,
                        new ArrayList<>()
                )
        );
    }

    public Transaction toDomain() {
        return new Transaction(
                getId(),
                getTitle(),
                getDescription(),
                getValue(),
                getMonth(),
                getType(),
                new User(
                        getUser().getId(),
                        null,
                        null,
                        null,
                        null,
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                new Account(
                        getAccount().getId(),
                        getAccount().getBank(),
                        getAccount().getLogoUrl(),
                        getAccount().getNumberAccount(),
                        null,
                        new ArrayList<>()
                )
        );
    }
}
