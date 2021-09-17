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
    public Optional<User> getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/{id}")
    public User replaceItem (@RequestBody User user, @PathVariable Long id){
        List<User> userList = userRepository.findAll();
        System.out.println(userList);
        User userToPut = userList.get(Math.toIntExact(id - 1));

        if(user.getEmail() != ""){
            userToPut.setEmail(user.getEmail());
        }

        if(user.getFirstName() != ""){
            userToPut.setFirstName(user.getFirstName());
        }

        if(user.getLastName() != ""){
            userToPut.setLastName(user.getLastName());
        }

        if(user.getPhone() != 0){
            userToPut.setPhone(user.getPhone());
        }

        if(user.getAddress() !=""){
            userToPut.setAddress(user.getAddress());
        }

        if(user.getPassword() != ""){
            userToPut.setPassword(user.getPassword());
        }

        return userRepository.save(userToPut);
    }
}
