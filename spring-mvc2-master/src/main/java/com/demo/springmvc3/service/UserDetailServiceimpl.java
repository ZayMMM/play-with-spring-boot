package com.demo.springmvc3.service;

import com.demo.springmvc3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceimpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailServiceimpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        return  userRepository.findByMail( mail )
                .orElseThrow( () -> new UsernameNotFoundException( mail + "Not Found" ) );
    }
}
