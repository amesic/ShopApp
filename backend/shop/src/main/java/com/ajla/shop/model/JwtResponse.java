package com.ajla.shop.model;

import java.io.Serializable;
import java.util.Objects;

//This class is required for creating a response containing the JWT to be returned to the user.
public class JwtResponse implements Serializable {
    private final String jwtToken;
    private final String username;
    private final String email;
    private final Object role;

    public JwtResponse(final String username,
                       final String jwtToken,
                       final String email,
                       final Object role) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return this.jwtToken;
    }
    public String getUsername() {
        return this.username;
    }
    public String getEmail() {
        return email;
    }
    public Object getRole() {
        return role;
    }
}
