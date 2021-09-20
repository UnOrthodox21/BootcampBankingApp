package com.bootcampTeam4.bootcampBankingApp.services;

import com.bootcampTeam4.bootcampBankingApp.models.UserDetails;
import com.bootcampTeam4.bootcampBankingApp.repositories.UserDetailsRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

//    public void deleteUserDetails(Long userDetailsId) {
//        boolean exists = userDetailsRepository.existsById(userDetailsId);
//        if (!exists) {
//            throw new IllegalStateException(("User with id " + userDetailsId + " does not exist."));
//        }
//        userDetailsRepository.deleteById(userDetailsId);
//    }


    public void changeUserDetailsData(UserDetails userDetails, String username){

        UserDetails userDetailsToPut = loadUserByUsername(username);

        if(userDetails.getEmail() != ""){
            userDetailsToPut.setEmail(userDetails.getEmail());
        }

        if(userDetails.getUsername() != ""){
            userDetailsToPut.setUsername(userDetails.getUsername());
        }

        if(userDetails.getFirstName() != ""){
            userDetailsToPut.setFirstName(userDetails.getFirstName());
        }

        if(userDetails.getLastName() != ""){
            userDetailsToPut.setLastName(userDetails.getLastName());
        }

        if(userDetails.getPhone() != 0){
            userDetailsToPut.setPhone(userDetails.getPhone());
        }

        if(userDetails.getAddress() != ""){
            userDetailsToPut.setAddress(userDetails.getAddress());
        }

        if(userDetails.getPassword() != ""){
            userDetailsToPut.setPassword(userDetails.getPassword());
        }

        if(userDetails.getRoles() != ""){
            userDetailsToPut.setRoles(userDetails.getRoles());
        }

        if(userDetails.getJwt() != ""){
            userDetailsToPut.setJwt(userDetails.getJwt());
        }
//
//        if(userDetails.getAuthorities().isEmpty() == false) {
//            userDetailsToPut.setAuthorities(userDetails.getAuthorities());
//        }
//
//        if(userDetails.isEnabled() == false || userDetails.isEnabled() == true) {
//            userDetailsToPut.setEnabled(userDetails.isEnabled());
//        }

        userDetailsRepository.save(userDetailsToPut);
    }

}
