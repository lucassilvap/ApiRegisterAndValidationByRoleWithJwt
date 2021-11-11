package com.example.jwtAndRoles.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NameNotFoundException extends ResponseStatusException {

	public NameNotFoundException() {
		super(HttpStatus.BAD_GATEWAY, "name not found !!");
		// TODO Auto-generated constructor stub
	}

}
