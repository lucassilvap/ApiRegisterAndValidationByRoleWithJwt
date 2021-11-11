package com.example.jwtAndRoles.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

	public UserNotFoundException() {
		super(HttpStatus.BAD_GATEWAY, "user not found!!");
		// TODO Auto-generated constructor stub
	}
 
	
}
