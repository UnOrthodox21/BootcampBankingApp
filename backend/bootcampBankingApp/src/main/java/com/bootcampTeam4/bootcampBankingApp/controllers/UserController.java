package com.bootcampTeam4.bootcampBankingApp.controllers;


import com.bootcampTeam4.bootcampBankingApp.classes.User;
import com.bootcampTeam4.bootcampBankingApp.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return usersRepository.save(user);
    }

}
