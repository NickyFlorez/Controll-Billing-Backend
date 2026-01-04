package com.nicky.controlBilling.infrastructure.controller.dto;

import com.nicky.controlBilling.domain.model.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionDto {
    private final UUID id;
    private final String title;
    private final String description;
    private final BigDecimal value;
    private final Month month;
    private final TransactionType type;
    private final UserDto user;
    private final AccountDto accountDto;

    public static TransactionDto fromDomain(Transaction transaction) {
        return new TransactionDto(
                transaction.id(),
                transaction.title(),
                transaction.description(),
                transaction.value(),
                transaction.month(),
                transaction.type(),
                new UserDto(
                        transaction.user().id(),
                        null,
                        null,
                        null,
                        null,
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                new AccountDto(
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
                        getAccountDto().getId(),
                        getAccountDto().getBank(),
                        getAccountDto().getLogoUrl(),
                        getAccountDto().getNumberAccount(),
                        null,
                        new ArrayList<>()
                )
        );
    }
}
