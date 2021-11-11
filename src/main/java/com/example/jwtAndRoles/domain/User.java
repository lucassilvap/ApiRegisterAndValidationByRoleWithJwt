package com.example.jwtAndRoles.domain;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
public class User {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;

private String name;

private String username;

private String password;

@ManyToMany(fetch = FetchType.EAGER)
private Collection<Role> role = new ArrayList<Role>();


}
