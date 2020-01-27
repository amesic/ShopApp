package com.ajla.shop.seeders;

import com.ajla.shop.model.User;
import com.ajla.shop.model.Role;

import com.ajla.shop.repository.UserRepository;
import com.ajla.shop.repository.RoleRepository;

import com.stripe.exception.StripeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.Objects;


@Component
public class DatabaseSeeder {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    @Value("${stripe.keys.secret}")
    private String API_SECRET_KEY;

    //dependency injection
    @Autowired
    public DatabaseSeeder(final UserRepository userRepository,
                          final RoleRepository roleRepository) {
        Objects.requireNonNull(userRepository, "userRepository must not be null.");
        Objects.requireNonNull(roleRepository, "roleRepository must not be null.");
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) throws StripeException {
        seedRoles();
        seedUsers();
    }
    private void seedRoles() {
        Role role = roleRepository.findRoleById((long) 1);
        if (role == null) {
            Role r1 = new Role();
            r1.setName("user");
            Role r2 = new Role();
            r2.setName("admin");

            roleRepository.saveAll(Arrays.asList(r1, r2));
            logger.info("Role table seeded");

        } else {
            logger.trace("Role Seeding Not Required");
        }
    }

    private void seedUsers() {
        User user = userRepository.findUserById((long) 1);
        if (user == null) {
            final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            final Role userRole = roleRepository.findRoleById((long) 1);
            final Role adminRole = roleRepository.findRoleById((long) 2);


            User u = new User();
            u.setUserName("Huso Husic");
            u.setPassword(passwordEncoder.encode("12345678"));
            u.setImage("https://myrealdomain.com/images/male-images-7.jpg");
            u.setEmail("huso@gmail.com");
            u.setRole(userRole);

            User u1 = new User();
            u1.setUserName("Mujo Mujic");
            u1.setPassword(passwordEncoder.encode("12345678"));
            u1.setEmail("mujo@gmail.com");
            u1.setImage("https://assets.nrdc.org/sites/default/files/styles/headshot/public/brendan-guy-450.jpg?itok=MK85ttfr");
            u1.setRole(userRole);

            User u2 = new User();
            u2.setUserName("Fata Fatic");
            u2.setPassword(passwordEncoder.encode("12345678"));
            u2.setEmail("fata@gmail.com");
            u2.setImage("https://images.fastcompany.net/image/upload/w_596,c_limit,q_auto:best,f_auto/wp-cms/uploads/2019/09/i-1-inside-bumble-ceo-whitney-wolfe-herds-mission-to-build-the-female-internet-FA1019BUMB002.jpg");
            u2.setRole(userRole);

            User u3 = new User();
            u3.setUserName("Suljo Suljic");
            u3.setPassword(passwordEncoder.encode("12345678"));
            u3.setEmail("suljo@gmail.com");
            u3.setImage("https://cdn-prod.medicalnewstoday.com/content/images/hero/266/266749/266749_1100.jpg");
            u3.setRole(userRole);

            userRepository.saveAll(Arrays.asList(u, u1, u2, u3));
            logger.info("User table seeded");
        } else {
            logger.trace("User Seeding Not Required");
        }

    }
}
