package com.demo.springmvc3.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

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
    private boolean enable;

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
}
