package com.nicky.controlBilling.infrastructure.controller.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountDto {
    private final UUID id;
    private final String bank;
    private final String logoUrl;
    private final String numberAccount;
    private final UserDto user;
    private final List<TransactionDto> transactions;
}
