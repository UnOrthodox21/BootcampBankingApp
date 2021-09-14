package com.bootcampTeam4.bootcampBankingApp.services;

import com.bootcampTeam4.bootcampBankingApp.classes.Transaction;
import com.bootcampTeam4.bootcampBankingApp.classes.User;
import com.bootcampTeam4.bootcampBankingApp.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(long transactionId) {
        return transactionRepository.findTransactionById(transactionId);
    }

    public void addNewTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteUser(long transactionId) {
        boolean exists = transactionRepository.existsById(transactionId);
        if (!exists) {
            throw new IllegalStateException(("User with id " + transactionId + " does not exist."));
        }
        transactionRepository.deleteById(transactionId);
    }

}
