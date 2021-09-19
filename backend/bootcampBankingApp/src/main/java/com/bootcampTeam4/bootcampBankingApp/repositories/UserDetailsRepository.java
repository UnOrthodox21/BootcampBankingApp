package com.bootcampTeam4.bootcampBankingApp.repositories;

import com.bootcampTeam4.bootcampBankingApp.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

//    @Query("SELECT u FROM User p WHERE u.id = ?1");
    Optional<UserDetails> findUserDetailsById(Long id);
    UserDetails findUserDetailsByUsername(String username);
}
