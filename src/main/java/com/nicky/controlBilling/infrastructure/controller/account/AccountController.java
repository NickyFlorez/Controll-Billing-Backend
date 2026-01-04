package com.nicky.controlBilling.infrastructure.controller.account;

import com.nicky.controlBilling.domain.model.Account;
import com.nicky.controlBilling.domain.model.AccountFilter;
import com.nicky.controlBilling.domain.model.User;
import com.nicky.controlBilling.domain.use_case.account.FindAccountByIdUseCase;
import com.nicky.controlBilling.domain.use_case.account.FindAccountsByFilterUseCase;
import com.nicky.controlBilling.domain.use_case.account.SaveAccountUseCase;
import com.nicky.controlBilling.infrastructure.controller.dto.request.CreateAccountDto;
import com.nicky.controlBilling.infrastructure.controller.dto.response.AccountResponse;
import com.nicky.controlBilling.infrastructure.controller.dto.response.ApiResponse;
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
    public ResponseEntity<@NonNull ApiResponse> find(
            @RequestParam UUID userId,
            @RequestParam(required = false) String bank
    ){
        AccountFilter accountFilter = new AccountFilter(
                userId,
                bank
        );

        List<Account> accounts = this.findAccountsByFilterUseCase.execute(accountFilter);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        HttpStatus.OK.name(),
                        accounts.stream().map(
                                AccountResponse::toResponse
                        ),
                        "Find Accounts filtered"
                )
        );
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<@NonNull ApiResponse> findById(@PathVariable UUID accountId) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.name(),
                AccountResponse.toResponse(this.findAccountByIdUseCase.execute(accountId)),
                "Find account By id"
        ));
    }

    @PostMapping
    public ResponseEntity<@NonNull ApiResponse> saveAccount(@RequestBody CreateAccountDto account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(
                        HttpStatus.OK.name(),
                        AccountResponse.toResponse(this.saveAccountUseCase.execute(mapCreateToDomain(account))),
                        "Save account"
                )
        );
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
