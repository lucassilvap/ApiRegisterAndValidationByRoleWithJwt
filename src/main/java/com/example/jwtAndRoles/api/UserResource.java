package com.example.jwtAndRoles.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtAndRoles.service.UserService;

@RestController
@RequestMapping("/api")
public class UserResource {
	
  @Autowired
  private UserService userService;
  
  

}
