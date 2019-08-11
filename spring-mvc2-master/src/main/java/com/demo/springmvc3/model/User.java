package com.demo.springmvc3.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String mail;

    private String password;

    @Transient
    private String confirmPassword;

    @Transient
    private boolean enable = true;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roleList = new ArrayList<>(  );

    public User() {
    }

    public User(String firstName, String lastName, String mail, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }

    public void addRole(Role role){
        this.roleList.add( role );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList.stream().map( r -> new SimpleGrantedAuthority( r.getRoleName() ))
        .collect( Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enable;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enable;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enable;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
