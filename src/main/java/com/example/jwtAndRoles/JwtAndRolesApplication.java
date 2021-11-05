 package com.example.jwtAndRoles;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class JwtAndRolesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAndRolesApplication.class, args);
	}
	
	
	/*
	@Bean
	CommandLineRunner run (UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			
			userService.saveUser(
			new User(1,"John Travolta", "john","1234",
			new ArrayList<>()));
			
			userService.saveUser(
					new User(2,"Will Smith", "will","1234",
					new ArrayList<>()));
			
			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("john", "ROLE_MANAGER");
			userService.addRoleToUser("john", "ROLE_ADMIN");
			userService.addRoleToUser("will", "ROLE_USER");
		};
		}
		*/
}
 