package com.bootcampTeam4.bootcampBankingApp.controllers;
import com.bootcampTeam4.bootcampBankingApp.models.BankAccount;
import com.bootcampTeam4.bootcampBankingApp.models.TransferFromTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bootcampTeam4.bootcampBankingApp.services.BankAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {


    private BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

//    @GetMapping("/str")
//    public BankAccount findByAccountNumber(@RequestBody TransferFromTo accountToFind){
//        List<BankAccount> newList = bankAccountService.getAllBankAccounts();
//        BankAccount acc = null;
//        String name = accountToFind.getInx();
//        for(int i=0;i< newList.size();i++){
//            System.out.println("name = " + name);
//            System.out.println(newList.get(i).getNumber());
//            if(newList.get(i).getNumber().equals(name)){
//
//                acc = newList.get(i);
//                break;
//            }
//
//        }
//        System.out.println(accountToFind);
//        System.out.println(acc.getBalance());
//        return acc;
//    }



//    @GetMapping(path = "{id}")
//    public Optional<BankAccount> getBankAccountById(@PathVariable("id") Long bankAccountId) {
//        return bankAccountService.getBankAccountById(bankAccountId);
//    }

    @GetMapping("/{accountNumber}")
    public BankAccount getBankAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber){
        return bankAccountService.findByAccountNumber(accountNumber);
    }



    @PostMapping
    public void addNewBankAccount(@RequestBody BankAccount bankAccount) {
        bankAccountService.addNewBankAccount(bankAccount);

    }


    @DeleteMapping(path = "{bankAccountId}")
    public void deleteBankAccount(@PathVariable("bankAccountId") String bankAccountId) {
        bankAccountService.deleteBankAccount(bankAccountId);
    }


    @PutMapping("/transfer")
    public void sendFunds(@RequestBody TransferFromTo transferFromTo){
        bankAccountService.sendFunds(transferFromTo);

    }

}
