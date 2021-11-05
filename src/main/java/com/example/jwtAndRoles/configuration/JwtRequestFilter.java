package com.example.jwtAndRoles.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.jwtAndRoles.service.JwtService;
import com.example.jwtAndRoles.ultil.JwtUltil;

import io.jsonwebtoken.ExpiredJwtException;
import javassist.compiler.SymbolTable;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUltil jwtUltil;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	final String header = request.getHeader("Authorization");
	String jwtToken = null;
	String userName  = null;
	
	 if(header != null && header.startsWith("Bearer ")) {
		 jwtToken = header.substring(7);
	   	 
	 try {		 
		 userName = jwtUltil.getUserNameFromToken(jwtToken);
		  
	 } catch (IllegalArgumentException e) {
		System.out.println("Unable to get jwt token");
	 } catch(ExpiredJwtException e) {
		System.out.println("Jwt is expired");
	 }
	 
   } else {
	  System.out.println("JWT token does not start with Bearer"); 
   }
	 
	 if(userName != null &&
			 SecurityContextHolder.getContext()
			 .getAuthentication()== null) {
		    UserDetails userDetails = 
		    jwtService.loadUserByUsername(userName);
		    
		    if(jwtUltil.validateToken(jwtToken, userDetails)) {
		    	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, 
		    			null, userDetails.getAuthorities());
		    	
		    	
		    	usernamePasswordAuthenticationToken
		    	.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		    	SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		    }
	 }
	   filterChain.doFilter(request, response);
		 
  }
}
