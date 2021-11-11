package com.example.jwtAndRoles.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.jwtAndRoles.Specification.UserDescriptSpecification;
import com.example.jwtAndRoles.dao.UserDao;
import com.example.jwtAndRoles.dao.UserDescriptionDao;
import com.example.jwtAndRoles.dto.UserDescriptionDto;
import com.example.jwtAndRoles.dto.UserDto;
import com.example.jwtAndRoles.entity.User;
import com.example.jwtAndRoles.entity.UserDescription;
import com.example.jwtAndRoles.exceptions.NameNotFoundException;
import com.example.jwtAndRoles.exceptions.UserDescriptionNotFoundException;
import com.example.jwtAndRoles.exceptions.UserNameMustBeUnique;

@Service
public class UserDescriptionService {
 
	@Autowired
	private UserDescriptionDao descriptionDao;
	
	@Autowired
	private UserService userService;
	
	private UserDescription findByNameProfission(String name) {
		Optional<UserDescription> optional = 
		descriptionDao.findByProfession(name);
			return optional.orElseThrow(NameNotFoundException::new);
	}
	
	public UserDescription saveUseDescription(UserDescriptionDto description) {
	   User user = userService.findByName(description.getNameUser());
	   
	   UserDescription userDescription = new UserDescription();
	   userDescription.setProfession(description.getProfession());
	   userDescription.setSalary(description.getSalary());
	   userDescription.getUser().add(user);
	    
	   Optional<UserDescription> userDescription2 =
	   descriptionDao.findByProfession(userDescription.getProfession());
	   if(userDescription2.isPresent())
	   throw new UserNameMustBeUnique();
	   return descriptionDao.save(userDescription);
	}
	
	public UserDescription findById(Long id) {
	  Optional<UserDescription> user = descriptionDao.findById(id);
	  return user.orElseThrow(UserDescriptionNotFoundException::new);
	}
	
	public Page<UserDescription> findByFilter(Optional<Double> salary) {
	  return descriptionDao.
	  findAll(UserDescriptSpecification.spec(salary), Pageable.ofSize(10));
	}
	
}
