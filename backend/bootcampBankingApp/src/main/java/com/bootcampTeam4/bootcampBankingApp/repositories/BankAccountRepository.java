package com.bootcampTeam4.bootcampBankingApp.repositories;

import com.bootcampTeam4.bootcampBankingApp.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findBankAccountById(Long id);
    List<BankAccount> getBankAccountsByUserId(Long id);
}
