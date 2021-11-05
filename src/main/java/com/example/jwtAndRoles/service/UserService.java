package com.example.jwtAndRoles.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwtAndRoles.dao.RoleDao;
import com.example.jwtAndRoles.dao.UserDao;
import com.example.jwtAndRoles.entity.Role;
import com.example.jwtAndRoles.entity.User;
import com.example.jwtAndRoles.exceptions.UserNameMustBeUnique;

@Service
public class UserService {
	
  @Autowired
  private RoleDao roleDao;
	
  @Autowired
  private UserDao dao;
  
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  public User saveUser (User user) {
	  return dao.save(user);
  }
  
  public User registerNewUser(User user) {
     Optional<Role> role = roleDao.findById("User");
	 boolean result = (dao.findById(user.getUserName()).isEmpty());
	 if(!result) throw new UserNameMustBeUnique();
			  
	 List<Role> roles = new ArrayList<Role>();
	 roles.add(role.get());
	 user.setRoles(roles);
	 user.setUserPassword(getEncodedPassword(user.getUserPassword()));
	 return dao.save(user);
  }
   
  public void initRoleAndUser() {
	  
	  Role adminRole = new Role();
	  adminRole.setRoleName("Admin");
	  adminRole.setRoleDescription("Admin role");
	  roleDao.save(adminRole);
	  
	  Role userRole = new Role();
	  userRole.setRoleName("User");
	  userRole.setRoleDescription("Default role for a new created record");
	  roleDao.save(userRole);
	  
	  User adminUser = new User();
	  adminUser.setFirstName("admin");
	  adminUser.setUserLastName("admin");
	  adminUser.setUserName("admin123");
	  adminUser.setUserPassword(getEncodedPassword("admin@pass"));
	  adminUser.getRoles().add(adminRole);
	  dao.save(adminUser);
	  
	  User user = new User();
	  user.setFirstName("raj");
	  user.setUserLastName("sharma");
	  user.setUserName("raj123");
	  user.setUserPassword(getEncodedPassword("raj@pass"));  
	  user.getRoles().add(userRole);
	  dao.save(user);
  }
  
  public String getEncodedPassword(String password) {
	return passwordEncoder.encode(password);  
  }
}
