package com.casestudy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;






import com.casestudy.springboot.model.User;

public interface AuthRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username); 
	
}