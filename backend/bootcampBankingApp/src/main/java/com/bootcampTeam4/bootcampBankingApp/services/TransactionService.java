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



    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;

    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }


    //edit
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
        if(transferFromTo.getDescription() != null){
            newTransaction.setDescription(transferFromTo.getDescription());
        }
        else{
            newTransaction.setDescription("");
        }
        if(transferFromTo.getRecipientName() != null){
            newTransaction.setRecipientName(transferFromTo.getRecipientName());
        }
        else{
            newTransaction.setRecipientName("");
        }

        newTransaction.setUserFrom(transferFromTo.getAccountNumberFrom());
        newTransaction.setUserTo(transferFromTo.getAccountNumberTo());
        newTransaction.setAmount(Math.round(transferFromTo.getAmount()*100.0)/100.0);
        newTransaction.setType("Transfer");
        transactionRepository.save(newTransaction);

    }

    public void addNewDepositTransaction(TransferFromTo transferFromTo){

        transferFromTo.setAccountNumberFrom("BANK1337");
        Transaction newTransaction = new Transaction();
        if(transferFromTo.getDescription() != null){
            newTransaction.setDescription(transferFromTo.getDescription());
        }
        else{
            newTransaction.setDescription("Deposit to " + transferFromTo.getAccountNumberTo());
        }
        if(transferFromTo.getRecipientName() != null){
            newTransaction.setRecipientName(transferFromTo.getRecipientName());
        }
        else{
            newTransaction.setRecipientName(transferFromTo.getAccountNumberTo() + " Account");
        }
        newTransaction.setUserTo(transferFromTo.getAccountNumberTo());
        newTransaction.setUserFrom(transferFromTo.getAccountNumberFrom());
        newTransaction.setAmount(Math.round(transferFromTo.getAmount()*100.0)/100.0);
        newTransaction.setType("Deposit");
        transactionRepository.save(newTransaction);

    }

    public void addNewWithdrawTransaction(TransferFromTo transferFromTo){
        Transaction newTransaction = new Transaction();

        newTransaction.setUserFrom(transferFromTo.getAccountNumberFrom());
        if(transferFromTo.getAccountNumberTo() != null){
            newTransaction.setUserTo(transferFromTo.getAccountNumberTo());
        }
        else{
            newTransaction.setUserTo("");
        }
        if(transferFromTo.getDescription() != null){
            newTransaction.setDescription(transferFromTo.getDescription());
        }
        else{
            newTransaction.setDescription("Withdraw from " + transferFromTo.getAccountNumberFrom());
        }
        if(transferFromTo.getRecipientName() != null){
            newTransaction.setRecipientName(transferFromTo.getRecipientName());
        }
        else{
            newTransaction.setRecipientName("");
        }

        newTransaction.setAmount(Math.round(transferFromTo.getAmount()*100.0)/100.0);
        newTransaction.setType("Withdraw");
        transactionRepository.save(newTransaction);

    }

    public void addNewFailedTransaction(TransferFromTo transferFromTo){
        Transaction newTransaction = new Transaction();
        if(transferFromTo.getAccountNumberFrom() != null){
            newTransaction.setUserFrom(transferFromTo.getAccountNumberFrom());
        }
        else{
            newTransaction.setUserFrom("");
        }
        if(transferFromTo.getAccountNumberTo() != null){
            newTransaction.setUserTo(transferFromTo.getAccountNumberTo());
        }
        else{
            newTransaction.setUserTo("");
        }
        if(transferFromTo.getDescription() != null){
            newTransaction.setDescription(transferFromTo.getDescription());
        }
        else{
            newTransaction.setDescription("");
        }
        if(transferFromTo.getRecipientName() != null){
            newTransaction.setRecipientName(transferFromTo.getRecipientName());
        }
        else{
            newTransaction.setRecipientName("");
        }
        newTransaction.setAmount(Math.round(transferFromTo.getAmount()*100.0)/100.0);
        newTransaction.setType("Failed - not enough funds");
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
