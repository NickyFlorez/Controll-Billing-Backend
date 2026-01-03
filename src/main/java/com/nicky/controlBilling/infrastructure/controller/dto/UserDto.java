package com.nicky.controlBilling.infrastructure.controller.dto;

import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.model.Role;
import com.nicky.controlBilling.domain.model.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Builder

public class UserDto {
    private final UUID id;
    private final String fullName;
    private final String email;
    private final String password;
    private final Role role;
    private final List<TransactionDto> incomes;

    public User toDomain(){
        return new User(
                this.getId(),
                this.getFullName(),
                this.getEmail(),
                this.getPassword(),
                this.getRole(),
                this.getIncomes() != null ? this.getIncomes().stream().map(
                        dto -> new Transaction(
                                dto.getId(),
                                dto.getTitle(),
                                dto.getDescription(),
                                dto.getValue(),
                                dto.getMonth(),
                                dto.getType(),
                                null
                        )
                ).toList() : new ArrayList<>()
        );
    }

    public static UserDto fromDomain(User user){
        return new UserDto(
                user.id(),
                user.fullName(),
                user.email(),
                user.password(),
                user.role(),
                user.transactions() != null ? user.transactions().stream().map(
                        income -> new TransactionDto(
                                income.id(),
                                income.title(),
                                income.description(),
                                income.value(),
                                income.month(),
                                income.type(),
                                null
                        )
                ).toList() : new ArrayList<>()
        );
    }
}
