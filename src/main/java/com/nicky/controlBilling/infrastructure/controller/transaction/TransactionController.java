package com.nicky.controlBilling.infrastructure.controller.transaction;

import com.nicky.controlBilling.domain.exceptions.TransactionNotFoundException;
import com.nicky.controlBilling.domain.model.*;
import com.nicky.controlBilling.domain.use_case.transaction.*;
import com.nicky.controlBilling.infrastructure.controller.dto.request.CreateTransactionDto;
import com.nicky.controlBilling.infrastructure.controller.dto.TransactionDto;
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
@RequestMapping("transactions")
public class TransactionController {

    private final FindTransactionByIdUseCase findTransactionByIdUseCase;
    private final SaveTransactionUseCase saveTransactionUseCase;
    private final FindByFilterUseCase findByFilterUseCase;

    @GetMapping("/{incomeId}")
    public ResponseEntity<@NonNull TransactionDto> findById(@PathVariable UUID incomeId) {
        return ResponseEntity.status(HttpStatus.OK).body(TransactionDto.fromDomain(this.findTransactionByIdUseCase.execute(incomeId).orElseThrow(() -> new TransactionNotFoundException("Transaction not found"))));
    }

    @GetMapping
    public ResponseEntity<@NonNull List<TransactionDto>> find(
            @RequestParam UUID userId,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) Month month
    ) {
        TransactionFilter filter = new TransactionFilter(
                userId,
                type,
                month
        );

        List<Transaction> transactions =
                this.findByFilterUseCase.execute(filter);

        return ResponseEntity.ok(
                transactions.stream()
                        .map(TransactionDto::fromDomain)
                        .toList()
        );
    }

    @PostMapping
    public ResponseEntity<@NonNull TransactionDto> saveIncome(@RequestBody CreateTransactionDto income) {
        Transaction transactionToSave = this.mapCreateDtoToDomain(income);
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionDto.fromDomain(this.saveTransactionUseCase.execute(transactionToSave)));
    }

    private Transaction mapCreateDtoToDomain(CreateTransactionDto income) {
        return new Transaction(
                null,
                income.title(),
                income.description(),
                income.value(),
                income.month(),
                income.type(),
                new User(
                        income.userId(),
                        null,
                        null,
                        null,
                        null,
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                new Account(
                        income.accountId(),
                        null,
                        null,
                        null,
                        null,
                        new ArrayList<>()
                )
        );
    }
}
