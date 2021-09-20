package com.bootcampTeam4.bootcampBankingApp.repositories;


import com.bootcampTeam4.bootcampBankingApp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>  {

    //  @Query("SELECT r FROM Role r WHERE r.id = ?1");
    Optional<Role> findRoleById(Long id);

}
