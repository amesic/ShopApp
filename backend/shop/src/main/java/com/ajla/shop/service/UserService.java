package com.ajla.shop.service;

import com.ajla.shop.model.Role;
import com.ajla.shop.model.User;
import com.ajla.shop.repository.RoleRepository;
import com.ajla.shop.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;


@Service
public class UserService implements IUserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    //we need qualifier when have more implementations of userRepo
    //for example Smth1 implements userRepo, Smth2 implements userRepo, and then we have two beans
    @Autowired
    public UserService(
            final UserRepository userRepository, RoleRepository roleRepository) {
        Objects.requireNonNull(roleRepository, "roleRepository must not be null.");
        Objects.requireNonNull(userRepository, "userRepository must not be null.");
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveDataFromUser(final User user) throws Throwable {
        final User userWithEmail = findByEmail(user.getEmail());
        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (userWithEmail != null && !passwordEncoder.matches("", userWithEmail.getPassword())) {
            throw new Throwable("You have already created an account with this email manually!");
        } else if (userWithEmail != null && passwordEncoder.matches("", userWithEmail.getPassword())) {
            throw new Throwable("User with this email already exist!");
        }
        Role role_user = roleRepository.findRoleById((long) 1);
        user.setRole(role_user);
        if (user.getPassword() != null && user.getPassword().length() >= 8
                && user.getEmail() != null && user.getUserName() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else if (user.getPassword() == null && user.getEmail() != null && user.getUserName() != null) {
            user.setPassword(passwordEncoder.encode(""));
            userRepository.save(user);
        } else throw new Throwable("Data not valid!");
    }

    //for auth looking for user from database
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName())));
    }
}
