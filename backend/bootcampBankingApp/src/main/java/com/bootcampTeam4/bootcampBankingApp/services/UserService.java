package com.bootcampTeam4.bootcampBankingApp.services;

import com.bootcampTeam4.bootcampBankingApp.classes.User;
import com.bootcampTeam4.bootcampBankingApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;
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

    public void addNewUser(User user) {
        userRepository.save(user);
    }



    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException(("User with id " + userId + " does not exist."));
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String email, String firstName, String lastName, String address, int phone, String password) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalStateException("User with id " + userId + " does not exist."));


        if (email != null && email.length() > 0) {
            user.setEmail(email);
        }

        if (firstName != null && firstName.length() > 0) {
            user.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0) {
            user.setLastName(lastName);
        }

        if (address != null && address.length() > 0) {
            user.setAddress(address);
        }

        if (phone > 0) {
            user.setPhone(phone);
        }

        if (password != null && password.length() > 0) {
            user.setPassword(password);
        }
    }

}
