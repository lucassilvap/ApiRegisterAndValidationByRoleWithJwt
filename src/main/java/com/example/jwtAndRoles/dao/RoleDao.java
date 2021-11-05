package com.example.jwtAndRoles.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.jwtAndRoles.entity.Role;


@Repository
public interface RoleDao extends CrudRepository<Role, String>{

}
