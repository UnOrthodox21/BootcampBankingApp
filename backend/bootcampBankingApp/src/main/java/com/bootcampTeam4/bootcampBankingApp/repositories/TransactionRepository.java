package com.bootcampTeam4.bootcampBankingApp.repositories;

import com.bootcampTeam4.bootcampBankingApp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    //    @Query("SELECT t FROM Transaction t WHERE t.id = ?1");
    Optional<Transaction> findTransactionById(Long id);

}
