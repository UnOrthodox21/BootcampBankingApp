package com.bootcampTeam4.bootcampBankingApp.repositories;

import com.bootcampTeam4.bootcampBankingApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM User p WHERE u.id = ?1");
    Optional<User> findUserById(Long id);

    //    @Query("SELECT u FROM User p WHERE u.id = ?1");
    Optional<User> findUserByUserName(String userName);
}
