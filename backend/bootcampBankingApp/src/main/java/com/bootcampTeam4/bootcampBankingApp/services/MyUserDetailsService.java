package com.bootcampTeam4.bootcampBankingApp.services;

import com.bootcampTeam4.bootcampBankingApp.models.BankAccount;
import com.bootcampTeam4.bootcampBankingApp.models.UserDetails;
import com.bootcampTeam4.bootcampBankingApp.repositories.UserDetailsRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    public MyUserDetailsService(UserDetailsRepository userRepository) {
        this.userDetailsRepository = userRepository;
    }

    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsRepository.findUserDetailsByUsername(username);
    }

    public UserDetails findUserDetailsByJwt(String jwt) throws UsernameNotFoundException {
        return userDetailsRepository.findUserDetailsByJwt(jwt);
    }

    public boolean checkAdminPassword(String password){
        String adminPassword=("gillbates");
        if(password.equals(adminPassword)){
            return true;
        }
        else {
            return  false;
        }
    }

    public UserDetails findUserDetailsByUsername(String userToFind){
        List<UserDetails> newList = getAllUserDetails();
        UserDetails userDetails = null;
        String name = userToFind;
        for(int i=0;i< newList.size();i++){
            if(newList.get(i).getUsername().equals(name)){
                userDetails = newList.get(i);
                System.out.println(userDetails.getUsername());
                break;
            }
        }
        return userDetails;
    }

    public void deleteUserDetailsByUsername(String username){
        UserDetails userToDelete = loadUserByUsername(username);
        userDetailsRepository.delete(userToDelete);
    }

    public void addNewUserDetails(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }

    public void changeUserDetailsData(UserDetails userDetails, String username){
      
        UserDetails userDetailsToPut = loadUserByUsername(username);
  
        if(userDetails.getEmail() != null){
            userDetailsToPut.setEmail(userDetails.getEmail());
        }

        if(userDetails.getUsername() != null){
            userDetailsToPut.setUsername(userDetails.getUsername());
        }

        if(userDetails.getFirstName() != null){
            userDetailsToPut.setFirstName(userDetails.getFirstName());
        }

        if(userDetails.getLastName() != null){
            userDetailsToPut.setLastName(userDetails.getLastName());
        }

        if(userDetails.getPhone() != 0){
            userDetailsToPut.setPhone(userDetails.getPhone());
        }

        if(userDetails.getAddress() != null){
            userDetailsToPut.setAddress(userDetails.getAddress());
        }

        if(userDetails.getPassword() != null){
            userDetailsToPut.setPassword(userDetails.getPassword());
        }

        if(userDetails.getRoles() != null){
            userDetailsToPut.setRoles(userDetails.getRoles());
        }

        if(userDetails.getImage() != null){
            userDetailsToPut.setImage(userDetails.getImage());
        }

        if(userDetails.getJwt() != null){
            userDetailsToPut.setJwt(userDetails.getJwt());
        }

        userDetailsRepository.save(userDetailsToPut);
    }

}
