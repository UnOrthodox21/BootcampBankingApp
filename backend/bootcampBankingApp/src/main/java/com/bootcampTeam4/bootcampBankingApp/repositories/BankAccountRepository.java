package com.bootcampTeam4.bootcampBankingApp.repositories;

import com.bootcampTeam4.bootcampBankingApp.classes.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    //    @Query("SELECT u FROM User p WHERE u.id = ?1");
    Optional<BankAccount> findBankAccountById(Long id);

}
