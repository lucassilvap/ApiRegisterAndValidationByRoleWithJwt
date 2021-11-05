package com.example.jwtAndRoles.controller;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.jwtAndRoles.entity.User;
import com.example.jwtAndRoles.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
  	
	public User saveUser(@RequestBody  User user) {
	  return userService.saveUser(user);	
	}

	@PostConstruct
	public void initRolesAndUsers() {
		userService.initRoleAndUser();
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/forAdmin")
	public String forAdmin() {
		return "this url is only acessible to admin";
	}
	
	@PreAuthorize("hasRole('User')")
	@GetMapping("/forUser")
	public String forUser() {
		return "this url is only acessible to user";
	}
	
	@PostMapping("RegisterUser")
	public ResponseEntity<?> registerNewUser(@RequestBody User user){
		return ResponseEntity.ok(userService.registerNewUser(user));
	}
}
	