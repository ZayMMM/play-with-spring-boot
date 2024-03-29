package com.demo.springmvc3.repository;

import com.demo.springmvc3.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByRoleName(String name);
}
