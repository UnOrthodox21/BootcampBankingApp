package com.bootcampTeam4.bootcampBankingApp.repositories;

import com.bootcampTeam4.bootcampBankingApp.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

}
