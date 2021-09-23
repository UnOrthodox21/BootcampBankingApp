package com.bootcampTeam4.bootcampBankingApp.controllers;
import com.bootcampTeam4.bootcampBankingApp.models.BankAccount;
import com.bootcampTeam4.bootcampBankingApp.models.Transaction;
import com.bootcampTeam4.bootcampBankingApp.models.TransferFromTo;
import com.bootcampTeam4.bootcampBankingApp.models.UserDetails;
import com.bootcampTeam4.bootcampBankingApp.services.MyUserDetailsService;
import com.bootcampTeam4.bootcampBankingApp.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bootcampTeam4.bootcampBankingApp.services.BankAccountService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {


    private BankAccountService bankAccountService;
    private TransactionService transactionService;
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService, TransactionService transactionService, MyUserDetailsService myUserDetailsService) {
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
        this.myUserDetailsService = myUserDetailsService;
    }

    @GetMapping("/getAllAccounts/{password}")
    public List<BankAccount> getAllBankAccounts(@PathVariable("password") String password) {
        if (myUserDetailsService.checkAdminPassword(password)) {
            return bankAccountService.getAllBankAccounts();
        }
        return null;
    }

    @GetMapping("/{accountNumber}")
    public BankAccount getBankAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber){
        return bankAccountService.getBankAccountByAccountNumber(accountNumber);
    }

    @GetMapping("/getByUserId/{userId}")
    public List<BankAccount> getBankAccountByAccountNumber(@PathVariable("userId") Long userId){
        return bankAccountService.getBankAccountsByUserId(userId);
    }

    @PostMapping
    public void addNewBankAccount(@RequestBody BankAccount bankAccount) {
        bankAccountService.addNewBankAccount(bankAccount);
    }

    @PostMapping("/createForUser/{userId}")
    public void createNewBankAccountForUser(@PathVariable("userId") Long userId) {
        String newAccountNumber = "RKBNK" + Math.round(Math.floor(Math.random() * (99999999 - 10000000 + 1)) + 10000000);
        BankAccount newBankAccount = new BankAccount(newAccountNumber, "Secondary", 0, userId);
        bankAccountService.addNewBankAccount(newBankAccount);
    }

    @DeleteMapping("/{accountNumber}")
    public void deleteBankAccount(@PathVariable("accountNumber") String accountNumber) {
            bankAccountService.deleteBankAccount(accountNumber);
    }

    @PutMapping("/{accountNumber}")
    public void editBankAccount(@RequestBody BankAccount bankAccount, @PathVariable("accountNumber") String accountNumber){
        bankAccountService.editBankAccountByAccountNumber(bankAccount, accountNumber);

    }


    @PutMapping("/transfer")
    public void sendFunds(@RequestBody TransferFromTo transferFromTo) {
        boolean success = bankAccountService.sendFunds(transferFromTo);
        if(success){
            transactionService.addNewTransferTransaction(transferFromTo);
        }
        else{
            transactionService.addNewFailedTransaction(transferFromTo);
        }
    }

    @PutMapping("/deposit")
    public void depositFunds(@RequestBody TransferFromTo transferFromTo){
        boolean success = bankAccountService.depositFunds(transferFromTo);
        if(success){
            transactionService.addNewDepositTransaction(transferFromTo);
        }
        else{
            transactionService.addNewFailedTransaction(transferFromTo);
        }

    }

    @PutMapping("/withdraw")
    public void withdrawFunds(@RequestBody TransferFromTo transferFromTo){
        boolean success = bankAccountService.withdrawFunds(transferFromTo);
        if(success){
            transactionService.addNewWithdrawTransaction(transferFromTo);
        }
        else{
            transactionService.addNewFailedTransaction(transferFromTo);
        }

    }

}
