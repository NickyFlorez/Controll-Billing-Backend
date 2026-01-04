package com.nicky.controlBilling.infrastructure.controller.account;

import com.nicky.controlBilling.domain.model.Account;
import com.nicky.controlBilling.domain.model.AccountFilter;
import com.nicky.controlBilling.domain.model.User;
import com.nicky.controlBilling.domain.use_case.account.FindAccountByIdUseCase;
import com.nicky.controlBilling.domain.use_case.account.FindAccountsByFilterUseCase;
import com.nicky.controlBilling.domain.use_case.account.SaveAccountUseCase;
import com.nicky.controlBilling.infrastructure.controller.dto.request.CreateAccountDto;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("accounts")
public class AccountController {

    private final FindAccountsByFilterUseCase findAccountsByFilterUseCase;
    private final FindAccountByIdUseCase findAccountByIdUseCase;
    private final SaveAccountUseCase saveAccountUseCase;

    @GetMapping
    public ResponseEntity<@NonNull List<Account>> find(
            @RequestParam UUID userId,
            @RequestParam(required = false) String bank
    ){
        AccountFilter accountFilter = new AccountFilter(
                userId,
                bank
        );

        List<Account> accounts = this.findAccountsByFilterUseCase.execute(accountFilter);

        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<@NonNull Account> findById(@PathVariable UUID accountId){
        return ResponseEntity.status(HttpStatus.OK).body(this.findAccountByIdUseCase.execute(accountId));
    }

    @PostMapping
    public ResponseEntity<@NonNull Account> saveAccount(@RequestBody CreateAccountDto account){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.saveAccountUseCase.execute(mapCreateToDomain(account)));
    }

    private Account mapCreateToDomain(CreateAccountDto dto){
        return new Account(
                null,
                dto.bank(),
                dto.logoUrl(),
                dto.numberAccount(),
                new User(
                        dto.userId(),
                        null,
                        null,
                        null,
                        null,
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                new ArrayList<>()
        );
    }
}
