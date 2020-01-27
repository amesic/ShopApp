package com.ajla.shop.controller;

import com.ajla.shop.config.JwtTokenUtil;
import com.ajla.shop.model.JwtResponse;
import com.ajla.shop.model.User;
import com.ajla.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    //for generate or validate token
    private final JwtTokenUtil jwtTokenUtil;
    // Using the Spring Authentication Manager, we authenticate the username and password.
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(final UserService userService,
                          final JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        Objects.requireNonNull(userService, "userService must not be null.");
        Objects.requireNonNull(jwtTokenUtil, "jwtTokenUtil must not be null.");
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(value = "/user/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody final User authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final  String userName = userService.findByEmail(authenticationRequest.getEmail()).getUserName();
        //when we have 3 arg, first is response, second is headers, and third is status
       return ResponseEntity.ok(new JwtResponse(userName, token, userDetails.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity<String> saveUserData(@RequestBody final User user) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        Boolean emailExist = userService.saveDataFromUser(user);
        if(emailExist == null) {
            return new ResponseEntity<>("Your data is not valid!", headers, HttpStatus.BAD_REQUEST);
        }
        if(!emailExist) {
            return new ResponseEntity<>(
                    "You are successfully registered " + user.getUserName() + "!",
                    headers,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                "You are already registered with " + user.getEmail() + " email!",
                headers,
                HttpStatus.BAD_REQUEST);
    }

    private void authenticate(final String email, final String password) throws Exception {
        try {
            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
