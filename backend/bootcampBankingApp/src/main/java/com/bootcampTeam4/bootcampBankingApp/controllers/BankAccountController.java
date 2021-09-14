package com.bootcampTeam4.bootcampBankingApp.controllers;
import com.bootcampTeam4.bootcampBankingApp.classes.BankAccount;
import com.bootcampTeam4.bootcampBankingApp.classes.TransferAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bootcampTeam4.bootcampBankingApp.services.BankAccountService;

@RestController
@RequestMapping("/api/users/test")
public class BankAccountController {


    private BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


    @PostMapping
    public void addNewBankAccount(@RequestBody BankAccount bankAccount) {
        bankAccountService.addNewBankAccount(bankAccount);

    }

    @PutMapping("/{id}")
    public void replaceItem(@RequestBody TransferAmount changes, @PathVariable Long id) {
        bankAccountService.changeBalance(changes, id);

    }
}
