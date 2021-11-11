package com.example.jwtAndRoles.entity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

	@Id
	private String userName;
	
	private String firstName;
	
	private String userLastName;
	
	private String userPassword;
	 
	@JsonIgnore
	@JsonIgnoreProperties
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Role> roles = new ArrayList<Role>();
	
}
