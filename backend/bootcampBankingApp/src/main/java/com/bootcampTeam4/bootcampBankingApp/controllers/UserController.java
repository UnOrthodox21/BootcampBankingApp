package com.bootcampTeam4.bootcampBankingApp.controllers;


import com.bootcampTeam4.bootcampBankingApp.models.User;
import com.bootcampTeam4.bootcampBankingApp.repositories.UserRepository;
import com.bootcampTeam4.bootcampBankingApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{userName}")
    public User getUserByUsername(@PathVariable("userName") String userName) {
        return userService.findUserByUsername(userName);
    }

    @PostMapping
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }



    @DeleteMapping("/{userName}")
    public void deleteUser(@PathVariable("userName") String userName){
        userService.deleteUserByUsername(userName);
    }


    @PutMapping("/{userName}")
    public void replaceItem (@RequestBody User user, @PathVariable String userName){
        userService.changeUserData(user,userName);
    }
}










