package com.ajla.shop.controller;

import com.ajla.shop.model.User;
import com.ajla.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Objects;

@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    private final UserService userService;

    @Autowired
    public AuthorizationController(final UserService userService) {
        Objects.requireNonNull(userService, "userService must not be null.");
        this.userService = userService;
    }

    @GetMapping("/user/info")
    public ResponseEntity<User> getUserInformation(@RequestParam("email") final String email) {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }
}
