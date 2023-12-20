package com.example.pathfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pathfinder.models.Role;
import com.example.pathfinder.models.enums.UserRoles;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findRoleByName(UserRoles roleName); 
}
