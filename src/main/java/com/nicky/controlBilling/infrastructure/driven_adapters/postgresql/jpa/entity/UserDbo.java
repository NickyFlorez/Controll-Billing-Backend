package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity;

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
    private List<TransactionDbo> incomes = new ArrayList<>();

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
                        null
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
                this.incomes.stream()
                        .map(dbo -> new Transaction(
                                dbo.getId(),
                                dbo.getTitle(),
                                dbo.getDescription(),
                                dbo.getValue(),
                                dbo.getMonth(),
                                dbo.getType(),
                                null
                        ))
                        .toList()
        );
    }
}