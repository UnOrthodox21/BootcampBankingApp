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

    @GetMapping(path = "{userName}")
    public UserDetails getUserDetailsByUsername(@PathVariable("userName") String userName) {
        return userDetailsService.findUserDetailsByUsername(userName);
    }

    @PostMapping
    public void addNewUserDetails(@RequestBody UserDetails userDetails) {
        userDetailsService.addNewUserDetails(userDetails);
    }

    @DeleteMapping(path = "{userDetailsId}")
    public void deleteUserDetails(@PathVariable("userDetailsId") Long userDetailsId) {
        userDetailsService.deleteUserDetails(userDetailsId);
    }

    @DeleteMapping("/{userName}")
    public void deleteUser(@PathVariable("userName") String userName){
        userDetailsService.deleteUserDetailsByUsername(userName);
    }

    @PutMapping("/{userName}")
    public void replaceItem (@RequestBody UserDetails userDetails, @PathVariable String userName){
        userDetailsService.changeUserDetailsData(userDetails, userName);
    }

}
