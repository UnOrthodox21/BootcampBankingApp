package com.bootcampTeam4.bootcampBankingApp.services;

import com.bootcampTeam4.bootcampBankingApp.models.BankAccount;
import com.bootcampTeam4.bootcampBankingApp.models.TransferFromTo;
import com.bootcampTeam4.bootcampBankingApp.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public void addNewBankAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccount> getBankAccountById(Long id) {
        return bankAccountRepository.findBankAccountById(id);
    }


    public void editBankAccountByAccountNumber(BankAccount bankAccount,String name){
        BankAccount bankAccountToEdit = getBankAccountByAccountNumber(name);
        bankAccountToEdit.setBalance(bankAccount.getBalance());
        bankAccountToEdit.setNumber(bankAccount.getNumber());
        bankAccountToEdit.setType(bankAccount.getType());
        bankAccountRepository.save(bankAccountToEdit);
    }



    public BankAccount getBankAccountByAccountNumber(String accountToFind){
        List<BankAccount> newList = getAllBankAccounts();
        BankAccount acc = null;
        String name = accountToFind;
        for(int i=0;i< newList.size();i++){
            if(newList.get(i).getNumber().equals(name)){
                acc = newList.get(i);
                System.out.println(acc.getNumber());
                break;
            }

        }
        return acc;
    }



    public void sendFunds(TransferFromTo transferFromTo) {
        List<BankAccount> bankAccountList = getAllBankAccounts();

        String nameFrom = transferFromTo.getAccountNumberFrom();
        String nameTo = transferFromTo.getAccountNumberTo();
        BankAccount bankAccountFrom = null;
        BankAccount bankAccountTo = null;



        for (int i = 0; i < bankAccountList.size(); i++) {
            if (bankAccountList.get(i).getNumber().equals(nameFrom)) {
                bankAccountFrom = bankAccountList.get(i);

            }
            if (bankAccountList.get(i).getNumber().equals(nameTo)) {
                bankAccountTo = bankAccountList.get(i);

            }
        }
        bankAccountFrom.setBalance(Math.round((bankAccountFrom.getBalance() - transferFromTo.getAmount()) * 100.0) / 100.0);
        bankAccountTo.setBalance(Math.round((bankAccountTo.getBalance() + transferFromTo.getAmount()) * 100.0) / 100.0);
        bankAccountRepository.save(bankAccountFrom);
        bankAccountRepository.save(bankAccountTo);
    }



    public void deleteBankAccount(String bankAccountNumber) {

        BankAccount acc = getBankAccountByAccountNumber(bankAccountNumber);
        Long id = acc.getId();
            bankAccountRepository.deleteById(id);
    }

}
