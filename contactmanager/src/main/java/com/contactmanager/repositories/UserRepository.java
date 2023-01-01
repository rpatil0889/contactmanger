package com.contactmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contactmanager.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "select u from User u where u.email =:email")
	public User getUserByEmail(@Param("email") String email);

}
