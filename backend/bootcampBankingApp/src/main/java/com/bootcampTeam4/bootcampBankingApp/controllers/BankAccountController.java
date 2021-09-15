package com.bootcampTeam4.bootcampBankingApp.controllers;
import com.bootcampTeam4.bootcampBankingApp.classes.BankAccount;
import com.bootcampTeam4.bootcampBankingApp.classes.TransferAmount;
import com.bootcampTeam4.bootcampBankingApp.classes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bootcampTeam4.bootcampBankingApp.services.BankAccountService;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "{id}")
    public Optional<BankAccount> getBankAccountById(@PathVariable("id") Long bankAccountId) {
        return bankAccountService.getBankAccountById(bankAccountId);
    }

    @PostMapping
    public void addNewBankAccount(@RequestBody BankAccount bankAccount) {
        bankAccountService.addNewBankAccount(bankAccount);

    }

    @PutMapping("/{id}")
    public void replaceItem(@RequestBody TransferAmount changes, @PathVariable Long id) {
        bankAccountService.changeBalance(changes, id);

    }

    @PutMapping("/test/{id}")
    @ResponseBody
    public void replaceItemTest(@RequestParam double changes, @PathVariable Long id) {
        bankAccountService.changeBalanceTest(changes, id);



    }

}
