package com.bootcampTeam4.bootcampBankingApp.controllers;


import com.bootcampTeam4.bootcampBankingApp.models.UserDetails;
import com.bootcampTeam4.bootcampBankingApp.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserDetailsController {

    private MyUserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public List<UserDetails> getAllUsers() {
        return userDetailsService.getAllUserDetails();
    }

    @GetMapping(path = "{userDetailsId}")
    public Optional<UserDetails> getUserDetailsById(@PathVariable("userDetailsId") Long userDetailsId) {
        return userDetailsService.getUserDetailsById(userDetailsId);
    }

    @PostMapping
    public void addNewUserDetails(@RequestBody UserDetails userDetails) {
        userDetailsService.addNewUserDetails(userDetails);
    }

    @DeleteMapping(path = "{userDetailsId}")
    public void deleteUserDetails(@PathVariable("userDetailsId") Long userDetailsId) {
        userDetailsService.deleteUserDetails(userDetailsId);
    }

    @PutMapping(path = "{userDetailsId}")
    public void updateUser(
            @PathVariable("userDetailsId") Long userDetailsId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) int phone,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String roles,
            @RequestParam(required = false) boolean active) {
                userDetailsService.updateUserDetails(userDetailsId, userName, email, firstName, lastName, address, phone, password, roles, active);
    }
}
