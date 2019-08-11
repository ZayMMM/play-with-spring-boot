package com.demo.springmvc3.bootstrap;

import com.demo.springmvc3.model.Role;
import com.demo.springmvc3.model.User;
import com.demo.springmvc3.repository.RoleRepository;
import com.demo.springmvc3.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private BCryptPasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    public DatabaseLoader(BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.passwordEncoder = bCryptPasswordEncoder;
        this.roleRepository  = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role( "ROLE_ADMIN" );
        Role userRole = new Role( "ROLE_USER" );

        User adminUser = new User( "adminemail@gmail.com","zay","myint", passwordEncoder.encode( "zay" ) );
        User user = new User( "usermail@gmail.com", "thaw", "thaw", passwordEncoder.encode( "thaw" ) );

        /*roleRepository.save( adminRole );
        roleRepository.save( userRole );

        userRepository.save( adminUser );
        userRepository.save( user );*/

    }
}
