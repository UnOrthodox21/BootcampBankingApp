package com.bootcampTeam4.bootcampBankingApp.controllers;

import com.bootcampTeam4.bootcampBankingApp.auth.AuthenticationRequest;
import com.bootcampTeam4.bootcampBankingApp.auth.AuthenticationResponse;
import com.bootcampTeam4.bootcampBankingApp.auth.JwtUtil;
import com.bootcampTeam4.bootcampBankingApp.services.MyUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class UserDetailsControllerUnitTest {

    @Mock
    private AuthenticationManager mockAuthManager;

    @Mock
    private MyUserDetailsService mockUserDetailsService;

    @Mock
    private JwtUtil mockJwtTokenUtil;

    @InjectMocks
    private UserDetailsController controller;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

// @PostMapping("/authenticate")
// public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//
//     try {
//         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//     } catch (BadCredentialsException e) {
//         throw new Exception("Incorrect username or password", e);
//     }
//
//     final org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService
//             .loadUserByUsername(authenticationRequest.getUsername());
//
//     final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//     return ResponseEntity.ok(new AuthenticationResponse(jwt));
// }

    @Test
    public void testCreateAuthTokenPositive(){
        //Given
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();

        when(mockJwtTokenUtil.generateToken(any())).thenReturn("TOKEN123");

        //When
        try {
            ResponseEntity<?> actual = controller.createAuthenticationToken(authenticationRequest);
            AuthenticationResponse response = (AuthenticationResponse) actual.getBody();
            //Then
            Assertions.assertEquals("TOKEN123",response.getJwt());
        }catch (Exception ex){
            Assertions.fail("Exception should not be thrown",ex);
        }
    }

    @Test
    public void testBadCredentialsException(){
        //Given
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        when(mockAuthManager.authenticate(any())).thenThrow(new BadCredentialsException("blabla"));
        when(mockJwtTokenUtil.generateToken(any())).thenReturn("TOKEN123");

        //When
        try {
            controller.createAuthenticationToken(authenticationRequest); //Should throw
            Assertions.fail("Exception should be thrown");
        }catch (Exception ex){
            //Then
            Assertions.assertEquals("Incorrect username or password",ex.getMessage());
        }
    }





}
