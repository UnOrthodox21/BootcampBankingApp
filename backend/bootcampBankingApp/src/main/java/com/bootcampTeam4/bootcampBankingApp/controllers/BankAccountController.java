package com.bootcampTeam4.bootcampBankingApp.controllers;
import com.bootcampTeam4.bootcampBankingApp.models.BankAccount;
import com.bootcampTeam4.bootcampBankingApp.models.Transaction;
import com.bootcampTeam4.bootcampBankingApp.models.TransferFromTo;
import com.bootcampTeam4.bootcampBankingApp.models.UserDetails;
import com.bootcampTeam4.bootcampBankingApp.repositories.UserDetailsRepository;
import com.bootcampTeam4.bootcampBankingApp.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bootcampTeam4.bootcampBankingApp.services.BankAccountService;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    //new
    @Autowired
    UserDetailsRepository userDetailsRepository;

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }



    @GetMapping("/{accountNumber}")
    public BankAccount getBankAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber){
        return bankAccountService.getBankAccountByAccountNumber(accountNumber);
    }

    //new
    @GetMapping(path = "/get/{id}")
    public List<BankAccount> getAllAccFromUser(@PathVariable("id") Long id){
        List<BankAccount> list = getAllBankAccounts();
        List<BankAccount> newList = new ArrayList<>();
        for(int i = 0; i< list.size();i++){
            System.out.println("*******************" + i);
            System.out.println("id == " + id);
            System.out.println(list.get(i).getUserDetails().getId());
            if(list.get(i).getUserDetails().getId() == id){
               newList.add(list.get(i));
            }
        }
        return newList;
    }





    //new
    @PutMapping("/{bankAccId}/add/{userId}")
    BankAccount addBankAccountToUser(
            @PathVariable Long bankAccId,
            @PathVariable Long userId
    ){
        BankAccount bankAccount = bankAccountService.getBankAccountById(bankAccId).get();
        UserDetails userDetails = userDetailsRepository.findUserDetailsById(userId).get();
        bankAccount.assignUser(userDetails);
        bankAccountService.addNewBankAccount(bankAccount);
        return bankAccount;

    }




    @PostMapping
    public void addNewBankAccount(@RequestBody BankAccount bankAccount) {
        bankAccountService.addNewBankAccount(bankAccount);

    }


    @DeleteMapping(path = "{accountNumber}")
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
