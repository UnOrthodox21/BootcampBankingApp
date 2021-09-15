package com.bootcampTeam4.bootcampBankingApp.services;


import com.bootcampTeam4.bootcampBankingApp.classes.BankAccount;
import com.bootcampTeam4.bootcampBankingApp.classes.TransferAmount;
import com.bootcampTeam4.bootcampBankingApp.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {


    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public void addNewBankAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> getAllBankAccounts(){
        return bankAccountRepository.findAll();
    }


    public void changeBalance(TransferAmount amount, Long id){
        List<BankAccount> bankAccountList = getAllBankAccounts();
        BankAccount bankAccount = bankAccountList.get(Math.toIntExact(id - 1));
        bankAccount.setBalance(bankAccount.getBalance()+amount.getAmount());
        bankAccountRepository.save(bankAccount);

    }

    public void changeBalanceTest(double amount, Long id){
        List<BankAccount> bankAccountList = getAllBankAccounts();
        BankAccount bankAccount = bankAccountList.get(Math.toIntExact(id - 1));
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);

    }


    public class Changes {

        private double price;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double changePrice(double priceChange){
            return price=price + priceChange;
        }
    }
}
