package com.example.jwtAndRoles.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserDescriptionNotFoundException extends ResponseStatusException{

	public UserDescriptionNotFoundException() {
		super(HttpStatus.BAD_GATEWAY, "user description not found !!");
		// TODO Auto-generated constructor stub
	}

	
}
