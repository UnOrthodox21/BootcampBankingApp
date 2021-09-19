package com.bootcampTeam4.bootcampBankingApp.services;

import com.bootcampTeam4.bootcampBankingApp.models.UserDetails;
import com.bootcampTeam4.bootcampBankingApp.repositories.UserDetailsRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Optional<UserDetails> getUserDetailsById(Long userDetailsId) {
        return userDetailsRepository.findUserDetailsById(userDetailsId);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsRepository.findUserDetailsByUsername(username);
    }

    public void addNewUserDetails(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }

    public void deleteUserDetails(Long userDetailsId) {
        boolean exists = userDetailsRepository.existsById(userDetailsId);
        if (!exists) {
            throw new IllegalStateException(("User with id " + userDetailsId + " does not exist."));
        }
        userDetailsRepository.deleteById(userDetailsId);
    }

    @Transactional
    public void updateUserDetails(Long userDetailsId, String userName, String email, String firstName, String lastName, String address, int phone, String password, String roles, boolean active) {
        UserDetails userDetails = userDetailsRepository.findById(userDetailsId).orElseThrow(() ->
                new IllegalStateException("User with id " + userDetailsId + " does not exist."));

        if (userName != null && userName.length() > 0) {
            userDetails.setUsername(userName);
        }


        if (email != null && email.length() > 0) {
            userDetails.setEmail(email);
        }

        if (firstName != null && firstName.length() > 0) {
            userDetails.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0) {
            userDetails.setLastName(lastName);
        }

        if (address != null && address.length() > 0) {
            userDetails.setAddress(address);
        }

        if (phone > 0) {
            userDetails.setPhone(phone);
        }

        if (password != null && password.length() > 0) {
            userDetails.setPassword(password);
        }


        if (roles != null && roles.length() > 0) {
            userDetails.setRoles(roles);
        }

//        if (active != null && active.length() > 0) {
//            user.setActive(active);
//        }
    }


}
