package com.example.jwtAndRoles.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwtAndRoles.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
   Role findByName(String name);
}
