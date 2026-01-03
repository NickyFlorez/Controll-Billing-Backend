package com.nicky.controlBilling.infrastructure.controller.dto;

import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.model.Month;
import com.nicky.controlBilling.domain.model.TransactionType;
import com.nicky.controlBilling.domain.model.User;
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

    public static TransactionDto fromDomain(Transaction transaction){
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
                        new ArrayList<>()
                )
        );
    }

    public Transaction toDomain(){
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
                        new ArrayList<>()
                )
        );
    }
}
