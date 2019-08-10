package com.demo.springmvc3.bootstrap;

import com.demo.springmvc3.model.Role;
import com.demo.springmvc3.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private BCryptPasswordEncoder passwordEncoder;

    public DatabaseLoader(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role( "ROLE_ADMIN" );
        Role userRole = new Role( "ROLE_USER" );

        User adminUser = new User( "adminemail@gmail.com","zay","myint", passwordEncoder.encode( "zay" ) );
        User user = new User( "usermail@gmail.com", "thaw", "thaw", passwordEncoder.encode( "thaw" ) );

        //

    }
}
