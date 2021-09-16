package com.bootcampTeam4.bootcampBankingApp.controllers;

import com.bootcampTeam4.bootcampBankingApp.classes.Transaction;
import com.bootcampTeam4.bootcampBankingApp.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping(path = "{transactionId}")
    public Optional<Transaction> getUser(@PathVariable("transactionId") Long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @PostMapping
    public void addNewTransaction(@RequestBody Transaction transaction) {
        transactionService.addNewTransaction(transaction);
    }

    @DeleteMapping(path = "{transactionId}")
    public void deleteUser(@PathVariable("transactionId") Long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }

}
