package com.bootcampTeam4.bootcampBankingApp.services;

import com.bootcampTeam4.bootcampBankingApp.models.Transaction;
import com.bootcampTeam4.bootcampBankingApp.models.TransferFromTo;
import com.bootcampTeam4.bootcampBankingApp.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {



    public TransactionRepository transactionRepository;
    public BankAccountService bankAccountService;


    public TransactionService(TransactionRepository transactionRepository, BankAccountService bankAccountService) {
        this.transactionRepository = transactionRepository;
        this.bankAccountService = bankAccountService;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllUserTransactions(String name){
        List<Transaction> allTransactions = transactionRepository.findAll();
        List<Transaction> userTransactions = new ArrayList<>();
        String nameForLookup = name;
        for(int i = 0; i<allTransactions.size(); i++){
            if(allTransactions.get(i).getUserTo().equals(nameForLookup) || allTransactions.get(i).getUserFrom().equals(nameForLookup)){
                userTransactions.add(allTransactions.get(i));
            }
        }

        return userTransactions;

    }

    public Optional<Transaction> getTransactionById(long transactionId) {
        return transactionRepository.findTransactionById(transactionId);
    }

    public void addNewTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void addNewTransferTransaction(TransferFromTo transferFromTo){
        Transaction newTransaction = new Transaction();
        newTransaction.setUserFrom(transferFromTo.getAccountNumberFrom());
        newTransaction.setUserTo(transferFromTo.getAccountNumberTo());
        newTransaction.setAmount(Math.round(transferFromTo.getAmount()*100.0)/100.0);
        newTransaction.setType("Transfer");
        transactionRepository.save(newTransaction);

    }

    public void addNewDepositTransaction(TransferFromTo transferFromTo){

        transferFromTo.setAccountNumberFrom("BANK1337");
        Transaction newTransaction = new Transaction();
        newTransaction.setUserTo(transferFromTo.getAccountNumberTo());
        newTransaction.setUserFrom(transferFromTo.getAccountNumberFrom());
        newTransaction.setAmount(Math.round(transferFromTo.getAmount()*100.0)/100.0);
        newTransaction.setType("Deposit");
        transactionRepository.save(newTransaction);

    }

    public void addNewWithdrawTransaction(TransferFromTo transferFromTo){
        transferFromTo.setAccountNumberTo("BANK1337");
        Transaction newTransaction = new Transaction();
        newTransaction.setUserFrom(transferFromTo.getAccountNumberFrom());
        newTransaction.setUserTo(transferFromTo.getAccountNumberTo());
        newTransaction.setAmount(Math.round(transferFromTo.getAmount()*100.0)/100.0);
        newTransaction.setType("Withdraw");
        transactionRepository.save(newTransaction);

    }



    public void deleteTransaction(long transactionId) {
        boolean exists = transactionRepository.existsById(transactionId);
        if (!exists) {
            throw new IllegalStateException(("Transaction with id " + transactionId + " does not exist."));
        }
        transactionRepository.deleteById(transactionId);
    }

}
