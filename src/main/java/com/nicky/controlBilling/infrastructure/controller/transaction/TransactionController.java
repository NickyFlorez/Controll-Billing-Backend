package com.nicky.controlBilling.infrastructure.controller.transaction;

import com.nicky.controlBilling.domain.model.*;
import com.nicky.controlBilling.domain.use_case.transaction.FindByFilterUseCase;
import com.nicky.controlBilling.domain.use_case.transaction.FindTransactionByIdUseCase;
import com.nicky.controlBilling.domain.use_case.transaction.SaveTransactionUseCase;
import com.nicky.controlBilling.infrastructure.controller.dto.request.CreateTransactionDto;
import com.nicky.controlBilling.infrastructure.controller.dto.response.ApiResponse;
import com.nicky.controlBilling.infrastructure.controller.dto.response.TransactionResponse;
import jakarta.validation.Valid;
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
    public ResponseEntity<@NonNull ApiResponse> findById(@PathVariable UUID incomeId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new ApiResponse(
                                HttpStatus.OK.name(),
                                TransactionResponse.toResponse(this.findTransactionByIdUseCase.execute(incomeId)),
                                "Find transaction by id"
                        )
                );
    }

    @GetMapping
    public ResponseEntity<@NonNull ApiResponse> find(
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

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(
                        HttpStatus.OK.name(),
                        transactions.stream().map(
                                TransactionResponse::toResponse
                        ).toList(),
                        "Find transactions filtered"
                ));
    }

    @PostMapping
    public ResponseEntity<@NonNull ApiResponse> saveIncome(@Valid @RequestBody CreateTransactionDto income) {
        Transaction transactionToSave = this.mapCreateDtoToDomain(income);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(
                        HttpStatus.CREATED.name(),
                        TransactionResponse.toResponse(this.saveTransactionUseCase.execute(transactionToSave)),
                        "Save transaction"
                ));
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
