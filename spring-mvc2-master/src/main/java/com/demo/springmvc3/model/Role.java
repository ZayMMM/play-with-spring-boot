package com.demo.springmvc3.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roleName;

    @ManyToMany(mappedBy = "roleList")
    private List<User> userList = new ArrayList<>(  );

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
