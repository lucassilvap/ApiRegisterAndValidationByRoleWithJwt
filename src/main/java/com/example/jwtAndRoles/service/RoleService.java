package com.example.jwtAndRoles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwtAndRoles.dao.RoleDao;
import com.example.jwtAndRoles.entity.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;

	public Role createRole(Role role) {
		return roleDao.save(role);
	}
}
