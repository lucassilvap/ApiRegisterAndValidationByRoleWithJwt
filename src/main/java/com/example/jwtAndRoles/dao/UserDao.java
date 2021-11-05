package com.example.jwtAndRoles.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.jwtAndRoles.entity.User;

@Repository
public interface UserDao extends CrudRepository<User,String> {

}
