 package com.bootcampTeam4.bootcampBankingApp.services;

import com.bootcampTeam4.bootcampBankingApp.models.BankAccount;
import com.bootcampTeam4.bootcampBankingApp.models.User;
import com.bootcampTeam4.bootcampBankingApp.repositories.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    public void changeUserData(User user,String id){
        User userToPut = findUserByUsername(id);
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

        userRepository.save(userToPut);
    }


    public User findUserByUsername(String userToFind){
        List<User> newList = getAllUsers();
        User user = null;
        String name = userToFind;
        for(int i=0;i< newList.size();i++){
            if(newList.get(i).getUserName().equals(name)){
                user = newList.get(i);
                System.out.println(user.getUserName());
                break;
            }

        }
        return user;
    }


    public void deleteUserByUsername(String username){
        User userToDelete = findUserByUsername(username);
        userRepository.delete(userToDelete);

    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }


}
