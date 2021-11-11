package com.example.jwtAndRoles.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.jwtAndRoles.entity.UserDescription;

@Repository
public interface UserDescriptionDao extends
CrudRepository<UserDescription, Long>, 
JpaSpecificationExecutor<UserDescription> {

	Optional<UserDescription> findByProfession(String profession);
}
