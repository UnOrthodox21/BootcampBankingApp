package com.bootcampTeam4.bootcampBankingApp.services;

import com.bootcampTeam4.bootcampBankingApp.models.Role;
import com.bootcampTeam4.bootcampBankingApp.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;


    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long roleId) {
        return roleRepository.findRoleById(roleId);
    }

    public void addNewRole(Role role) {
        roleRepository.save(role);
    }

    public void deleteRole(Long roleId) {
        boolean exists = roleRepository.existsById(roleId);
        if (!exists) {
            throw new IllegalStateException(("Role with id " + roleId + " does not exist."));
        }
        roleRepository.deleteById(roleId);
    }

}
