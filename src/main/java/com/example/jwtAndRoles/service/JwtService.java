package com.example.jwtAndRoles.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.jwtAndRoles.dao.RoleDao;
import com.example.jwtAndRoles.dao.UserDao;
import com.example.jwtAndRoles.entity.JwtRequest;
import com.example.jwtAndRoles.entity.JwtResponse;
import com.example.jwtAndRoles.entity.User;
import com.example.jwtAndRoles.ultil.JwtUltil;


@Service
public class JwtService implements UserDetailsService{

	@Autowired
	public UserDao userDao;
	
	@Autowired
	private JwtUltil jwtUltil;
	
	@Autowired
	private RoleDao role;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
	 String userName = jwtRequest.getUserName();
	 String password = jwtRequest.getUserPassword();
	 authenticate(userName, password);
	 final UserDetails userDetails = loadUserByUsername(userName);
	 String token = jwtUltil.generateToken(userDetails);
	 User user = userDao.findById(userName).get();
	 return new JwtResponse(user, token);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findById(username).get();
		if (user!= null) {
			return new 
					org.springframework.security.core.
					userdetails.User(user.getUserName(), 
					 user.getUserPassword(),
					 getAuthoritites(user));
		}else {
			throw new UsernameNotFoundException("Username is not valid");
		}
	}
		
	private List<SimpleGrantedAuthority> getAuthoritites(User user) {
    	List<SimpleGrantedAuthority> authoritities = 
    			new ArrayList<SimpleGrantedAuthority>(); 	
    	
    	user.getRoles().forEach(role ->{
    	authoritities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
    	});
    
    	return authoritities;
    }	
	 
	private void authenticate(String userName, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));	
		}catch(DisabledException e ) {
			throw new Exception("User is disabled");
		}catch(BadCredentialsException e) {
			throw new Exception("Bad credentials for user");
		}
	}
}
