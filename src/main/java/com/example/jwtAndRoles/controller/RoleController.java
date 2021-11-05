package com.example.jwtAndRoles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtAndRoles.entity.Role;
import com.example.jwtAndRoles.service.RoleService;

@RestController
public class RoleController {
  
	 @Autowired
	 private RoleService roleService;
	
	 @PostMapping("createNewRole")
	 public Role createNewRole(@RequestBody Role role) {
		 return roleService.createRole(role);
	 }
	 
}
