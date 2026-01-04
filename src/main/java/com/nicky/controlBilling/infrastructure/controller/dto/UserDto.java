package com.nicky.controlBilling.infrastructure.controller.dto;

import com.nicky.controlBilling.domain.model.Account;
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
    private final List<AccountDto> accounts;

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
                                null,
                                null
                        )
                ).toList() : new ArrayList<>(),
                this.getAccounts() != null ? this.getAccounts().stream().map(
                        dto -> new Account(
                                dto.getId(),
                                dto.getBank(),
                                dto.getLogoUrl(),
                                dto.getNumberAccount(),
                                null,
                                new ArrayList<>()
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
                                null,
                                null
                        )
                ).toList() : new ArrayList<>(),
                user.accounts() != null ? user.accounts().stream().map(
                        account -> new AccountDto(
                                account.id(),
                                account.bank(),
                                account.logoUrl(),
                                account.numberAccount(),
                                null,
                                new ArrayList<>()
                        )
                ).toList() : new ArrayList<>()
        );
    }
}
