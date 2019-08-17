package com.demo.springmvc3.service;

import com.demo.springmvc3.model.Role;
import com.demo.springmvc3.model.User;
import com.demo.springmvc3.repository.RoleRepository;
import com.demo.springmvc3.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceimpl implements UserDetailsService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetailServiceimpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        return  userRepository.findByMail( mail )
                .orElseThrow( () -> new UsernameNotFoundException( mail + "Not Found" ) );
    }

    public User register(User user){
        Role role = roleRepository.findRoleByRoleName( "ROLE_ADMIN" );

        user.setPassword( bCryptPasswordEncoder.encode( user.getPassword() ) );
        user.addRole( role );
        role.getUserList().add( user );

        return userRepository.save( user );
    }
}
