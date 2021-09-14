package com.bootcampTeam4.bootcampBankingApp.repositories;


import com.bootcampTeam4.bootcampBankingApp.classes.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {


}
