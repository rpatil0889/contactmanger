package com.contactmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contactmanager.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
