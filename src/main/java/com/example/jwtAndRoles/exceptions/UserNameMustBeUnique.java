package com.example.jwtAndRoles.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNameMustBeUnique extends ResponseStatusException {

	public UserNameMustBeUnique() {
		super(HttpStatus.BAD_REQUEST, "user name must be unique !!");
		// TODO Auto-generated constructor stub
	}

}
