package com.example.jwtAndRoles.Specification;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import com.example.jwtAndRoles.entity.UserDescription;

public abstract class UserDescriptSpecification {
  
	private static Specification<UserDescription> bySalary(double salary){
		return (root, query, criteriaBuilder) 
	    -> criteriaBuilder.equal(root.get("salary"), salary);
	}
	
	public static Specification<UserDescription> spec(Optional<Double> salary){
		 Specification<UserDescription> specification = null;
		 if(salary.isPresent())
		 specification =  bySalary(salary.get());
		 return specification;
	}
}
