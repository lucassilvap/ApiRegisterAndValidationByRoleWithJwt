package com.example.jwtAndRoles.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProfissionNameMustBeUnique extends ResponseStatusException {

	public ProfissionNameMustBeUnique() {
		super(HttpStatus.BAD_REQUEST, "name of profission must be unique!!");
	}
}
