package com.contactmanager.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.contactmanager.entities.User;
import com.contactmanager.repositories.UserRepository;

@Controller

public class UserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping(path = "user/dashboard")
	public String dashboard(Model m, Principal principal ) {
		m.addAttribute("title","User-Dashboard");
		String email = principal.getName();	
		User user = userRepository.getUserByEmail(email);		
		m.addAttribute("user", user);
		return "user/dashboard";
	}
}
