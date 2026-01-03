package com.nicky.controlBilling.domain.use_case.transaction;

import com.nicky.controlBilling.domain.exceptions.UserNotFoundException;
import com.nicky.controlBilling.domain.model.Transaction;
import com.nicky.controlBilling.domain.port.transaction.TransactionPort;
import com.nicky.controlBilling.domain.port.user.UserPort;

import java.util.List;
import java.util.UUID;

public class FindAllTransactionsByUserIdUseCase {

    private final TransactionPort transactionPort;
    private final UserPort userPort;

    public FindAllTransactionsByUserIdUseCase(TransactionPort transactionPort, UserPort userPort) {
        this.transactionPort = transactionPort;
        this.userPort = userPort;
    }

    public List<Transaction> execute(UUID userId){
        this.userPort.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return this.transactionPort.findAllTransactionsByUserId(userId);
    }
}
