package com.example.jwtAndRoles.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class UserDescription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String profession;
	
	private double salary;
	
	@OneToMany
	private List<User> user;
	
	public UserDescription() {
		this.user = new ArrayList<User>();
	}
}
