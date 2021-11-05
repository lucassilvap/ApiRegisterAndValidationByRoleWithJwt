package com.example.jwtAndRoles.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.jwtAndRoles.service.JwtService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
   @Autowired
   private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;	
   
   @Autowired
   private JwtRequestFilter JwtRequestFilter;
   
   @Autowired
   private UserDetailsService userDetailService;
  
   @Autowired
   private JwtService jwtService;
   
   @Bean	
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
   }
   
   @Override
   protected void configure(HttpSecurity httpSecurity) throws Exception {
	    httpSecurity.cors();
	    httpSecurity.csrf().disable()
	    .authorizeRequests()
	    .antMatchers("/authenticate","/RegisterUser").permitAll()
	    .antMatchers(org.springframework.http.HttpHeaders.ALLOW)
	    .permitAll()
	    .anyRequest()
	    .authenticated()
	    .and()
	    .exceptionHandling()
	    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
	    .and()
	    .sessionManagement()
	    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    
	    httpSecurity.
	    addFilterBefore(
	    		JwtRequestFilter,
	    		UsernamePasswordAuthenticationFilter.class);
	    
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
   }
   
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception{   
   managerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
   }
}
