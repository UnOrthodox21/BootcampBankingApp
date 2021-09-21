package com.bootcampTeam4.bootcampBankingApp.controllers;


import com.bootcampTeam4.bootcampBankingApp.auth.AuthenticationRequest;
import com.bootcampTeam4.bootcampBankingApp.auth.AuthenticationResponse;
import com.bootcampTeam4.bootcampBankingApp.auth.JwtUtil;
import com.bootcampTeam4.bootcampBankingApp.models.BankAccount;
import com.bootcampTeam4.bootcampBankingApp.models.UserDetails;
import com.bootcampTeam4.bootcampBankingApp.services.BankAccountService;
import com.bootcampTeam4.bootcampBankingApp.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/users")
public class UserDetailsController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    public UserDetailsController(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public List<UserDetails> getAllUsers() {
        return userDetailsService.getAllUserDetails();
    }

    @GetMapping(path = "{username}")
    public UserDetails getUserDetailsByUsername(@PathVariable("username") String username) {
        return userDetailsService.loadUserByUsername(username);
    }

    @GetMapping(path = "/getUserByJwt/{jwt}")
    public UserDetails getUserDetailsByJwt(@PathVariable("jwt") String jwt) {
        return userDetailsService.findUserDetailsByJwt(jwt);
    }

    @PostMapping
    public void addNewUserDetails(@RequestBody UserDetails userDetails) {
        userDetailsService.addNewUserDetails(userDetails);
    }


    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable("username") String username){
        userDetailsService.deleteUserDetailsByUsername(username);
    }

    @PutMapping(value="/{username}")
    public void replaceItem (@RequestBody UserDetails userDetails, @PathVariable String username){
        userDetailsService.changeUserDetailsData(userDetails, username);
    }

    @PostMapping(path = "/register")
    public void registerNewUser(@RequestBody UserDetails userDetails) {
        userDetailsService.addNewUserDetails(userDetails);
        String newAccountNumber = "RKBNK" + Math.round(Math.floor(Math.random() * (99999 - 10000 + 1)) + 10000);
        BankAccount newBankAccount = new BankAccount(newAccountNumber, "Primary", 0, userDetails.getId());
        bankAccountService.addNewBankAccount(newBankAccount);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
