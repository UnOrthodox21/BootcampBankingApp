package com.bootcampTeam4.bootcampBankingApp.controllers;


import com.bootcampTeam4.bootcampBankingApp.models.Role;
import com.bootcampTeam4.bootcampBankingApp.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getAllRoles() {
       return roleService.getAllRoles();
    }

    @GetMapping(path="{roleId}")
    public Optional<Role> getRoleById(@PathVariable("roleId") Long roleId) {
        return roleService.getRoleById(roleId);
    }

    @PostMapping
    public void addNewRole(@RequestBody Role role) {
        roleService.addNewRole(role);
    }

    @DeleteMapping(path="{roleId}")
    public void deleteRole(@PathVariable("roleId") Long roleId) {
        roleService.deleteRole(roleId);
    }

}
