package com.nicky.controlBilling.infrastructure.controller.transaction;

import com.nicky.controlBilling.domain.exceptions.TransactionNotFoundException;
import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.model.TransactionType;
import com.nicky.controlBilling.domain.model.User;
import com.nicky.controlBilling.domain.use_case.transaction.FindAllTransactionsByUserIdUseCase;
import com.nicky.controlBilling.domain.use_case.transaction.FindTransactionByIdUseCase;
import com.nicky.controlBilling.domain.use_case.transaction.FindTransactionsByTypeUseCase;
import com.nicky.controlBilling.domain.use_case.transaction.SaveTransactionUseCase;
import com.nicky.controlBilling.infrastructure.controller.dto.CreateTransactionDto;
import com.nicky.controlBilling.infrastructure.controller.dto.TransactionDto;
import lombok.AllArgsConstructor;
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
    private final FindAllTransactionsByUserIdUseCase findAllTransactionsByUserIdUseCase;
    private final SaveTransactionUseCase saveTransactionUseCase;
    private final FindTransactionsByTypeUseCase findTransactionsByTypeUseCase;

    @GetMapping("/{incomeId}")
    public ResponseEntity<TransactionDto> findById(@PathVariable UUID incomeId) {
        return ResponseEntity.status(HttpStatus.OK).body(TransactionDto.fromDomain(this.findTransactionByIdUseCase.execute(incomeId).orElseThrow(() -> new TransactionNotFoundException("Transaction not found"))));
    }

    @GetMapping("/type/{type}/user/{userId}")
    public ResponseEntity<List<TransactionDto>> findByType(@PathVariable TransactionType type, @PathVariable UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.findTransactionsByTypeUseCase.execute(type, userId).stream().map(TransactionDto::fromDomain).toList());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionDto>> findByUserId(@PathVariable UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.findAllTransactionsByUserIdUseCase.execute(userId).stream().map(TransactionDto::fromDomain).toList());
    }

    @PostMapping
    public ResponseEntity<TransactionDto> saveIncome(@RequestBody CreateTransactionDto income) {
        Transaction transactionToSave = this.mapCreateDtoToDomain(income);
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionDto.fromDomain(this.saveTransactionUseCase.execute(transactionToSave)));
    }

    private Transaction mapCreateDtoToDomain(CreateTransactionDto income){
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
                        new ArrayList<>()
                )
        );
    }
}
