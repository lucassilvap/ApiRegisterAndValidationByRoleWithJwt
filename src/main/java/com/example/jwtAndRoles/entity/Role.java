package com.example.jwtAndRoles.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Role {

	@Id
	private String roleName;
	
	private String roleDescription;
 
}

