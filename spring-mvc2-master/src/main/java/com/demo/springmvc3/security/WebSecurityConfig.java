package com.demo.springmvc3.security;

import com.demo.springmvc3.service.UserDetailServiceimpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserDetailServiceimpl userDetailServiceimpl;

    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailServiceimpl userDetailServiceimpl) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailServiceimpl = userDetailServiceimpl;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/products").permitAll()
                .antMatchers("/category").hasRole("ADMIN")
                .antMatchers("/product").hasRole("ADMIN")
                //.anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage( "/login" )
                .usernameParameter( "mail" )
                .and()
                .logout()
                .and()
                .rememberMe()
                .and()
                .csrf()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService( userDetailServiceimpl)
                .passwordEncoder( bCryptPasswordEncoder );
    }
}
