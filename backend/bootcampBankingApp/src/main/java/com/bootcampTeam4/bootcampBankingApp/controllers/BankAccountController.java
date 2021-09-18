package com.bootcampTeam4.bootcampBankingApp.controllers;
import com.bootcampTeam4.bootcampBankingApp.models.BankAccount;
import com.bootcampTeam4.bootcampBankingApp.models.Transaction;
import com.bootcampTeam4.bootcampBankingApp.models.TransferFromTo;
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

    @Autowired
    public BankAccountController(BankAccountService bankAccountService, TransactionService transactionService) {
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }



    @GetMapping("/{accountNumber}")
    public BankAccount getBankAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber){
        return bankAccountService.findByAccountNumber(accountNumber);
    }



    @PostMapping
    public void addNewBankAccount(@RequestBody BankAccount bankAccount) {
        bankAccountService.addNewBankAccount(bankAccount);

    }


    @DeleteMapping(path = "{accountNumber}")
    public void deleteBankAccount(@PathVariable("accountNumber") String accountNumber) {
        bankAccountService.deleteBankAccount(accountNumber);
    }

    @PutMapping("/edit/{accountNumber}")
    public void editBankAccount(@RequestBody BankAccount bankAccount, @PathVariable("accountNumber") String accountNumber){
        bankAccountService.editBankAccountByAccountNumber(bankAccount, accountNumber);

    }


    @PutMapping("/transfer")
    public void sendFunds(@RequestBody TransferFromTo transferFromTo) {

        transactionService.addNewTransferTransaction(transferFromTo);
        bankAccountService.sendFunds(transferFromTo);

    }

    @PutMapping("/deposit")
    public void depositFunds(@RequestBody TransferFromTo transferFromTo){

        transactionService.addNewDepositTransaction(transferFromTo);
        bankAccountService.sendFunds(transferFromTo);

    }

    @PutMapping("/withdraw")
    public void withdrawFunds(@RequestBody TransferFromTo transferFromTo){

        transactionService.addNewWithdrawTransaction(transferFromTo);
        bankAccountService.sendFunds(transferFromTo);

    }

}
