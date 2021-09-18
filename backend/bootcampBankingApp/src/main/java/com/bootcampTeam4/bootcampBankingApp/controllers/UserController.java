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

    @GetMapping(path = "{userId}")
    public User getUserById(@PathVariable("userId") String userId) {
        return userService.findUserByUsername(userId);
    }

    @PostMapping
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }



    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable("username") String username){
        userService.deleteUserByUsername(username);
    }

    @PutMapping("/{id}")
    public void replaceItem (@RequestBody User user, @PathVariable String id){
        userService.changeUserData(user,id);

    }
}
