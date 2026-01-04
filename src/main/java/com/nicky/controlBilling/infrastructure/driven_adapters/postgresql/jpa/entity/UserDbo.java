package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity;

import com.nicky.controlBilling.domain.model.Account;
import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.model.Role;
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

@Entity
@Table(name = "users_table")
public class UserDbo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String fullName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TransactionDbo> transactions = new ArrayList<>();

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AccountDbo> accounts = new ArrayList<>();

    public static UserDbo fromDomain(User user) {
        return new UserDbo(
                user.id(),
                user.fullName(),
                user.email(),
                user.password(),
                user.role(),
                user.transactions() != null ? user.transactions().stream().map(domain -> new TransactionDbo(
                        domain.id(),
                        domain.title(),
                        domain.description(),
                        domain.value(),
                        domain.month(),
                        domain.type(),
                        null,
                        null
                )).toList() : new ArrayList<>(),
                user.accounts() != null ? user.accounts().stream().map(domain -> new AccountDbo(
                        domain.id(),
                        domain.bank(),
                        domain.logoUrl(),
                        domain.numberAccount(),
                        null,
                        new ArrayList<>()
                )).toList() : new ArrayList<>()
        );
    }

    public User toDomain() {
        return new User(
                this.id,
                this.fullName,
                this.email,
                this.password,
                this.role,
                this.transactions.stream()
                        .map(dbo -> new Transaction(
                                dbo.getId(),
                                dbo.getTitle(),
                                dbo.getDescription(),
                                dbo.getValue(),
                                dbo.getMonth(),
                                dbo.getType(),
                                null,
                                new Account(
                                        dbo.getAccount().getId(),
                                        dbo.getAccount().getBank(),
                                        dbo.getAccount().getLogoUrl(),
                                        dbo.getAccount().getNumberAccount(),
                                        null,
                                        new ArrayList<>()
                                )
                        ))
                        .toList(),
                this.accounts.stream()
                        .map(dbo -> new Account(
                                dbo.getId(),
                                dbo.getBank(),
                                dbo.getLogoUrl(),
                                dbo.getNumberAccount(),
                                null,
                                new ArrayList<>()
                        )).toList()
        );
    }
}