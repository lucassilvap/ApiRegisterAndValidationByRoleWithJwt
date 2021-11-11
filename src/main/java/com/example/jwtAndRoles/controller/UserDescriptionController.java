package com.example.jwtAndRoles.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.jwtAndRoles.dto.UserDescriptionDto;
import com.example.jwtAndRoles.service.UserDescriptionService;

@RestController
@RequestMapping("userDescription")
public class UserDescriptionController {
	
	
	@Autowired
	private UserDescriptionService user;
    
	@PreAuthorize("hasRole('User')")
	@GetMapping("{id}")
	public ResponseEntity<?> userDescription(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(user.findById(id));
	}
	
	@PreAuthorize("hasRole('User')")
	@PostMapping
	public ResponseEntity<?> saveDescription(@RequestBody UserDescriptionDto  descriptionDto){
		return ResponseEntity.ok(user.saveUseDescription(descriptionDto));
	}
	
	@PreAuthorize("hasRole('User')")
	@GetMapping("filter")
	public ResponseEntity<?> userDescription(@RequestParam(name = "salary",
	required = false) Optional<Double> salary) {
		return ResponseEntity.ok(user.findByFilter(salary));
	}
		
}
