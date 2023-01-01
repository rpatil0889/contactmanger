package com.contactmanager.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.contactmanager.entities.User;
import com.contactmanager.helpers.Messege;
import com.contactmanager.repositories.UserRepository;

@Controller
public class WebController {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepository userRepository;

	@GetMapping(path = "/")
	public String home(Model m) {
		m.addAttribute("title", "home");
		return "home";
	}

	@GetMapping(path = "/about")
	public String about(Model m) {
		m.addAttribute("title", "About-SmarterContact");
		return "about";
	}

	@GetMapping(path = "/login")
	public String login(Model m) {
		m.addAttribute("title", "Login-SmarterContact");
		return "login";
	}

	@GetMapping(path = "/register")
	public String register(Model m) {
		m.addAttribute("title", "Register-SmarterContact");
		User u = new User();
		m.addAttribute("user", u);
		return "register";
	}

	@PostMapping(path = "/process")
	public String processForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model m,
			@RequestParam(value = "agreement", defaultValue = "false") Boolean agreement,
			@RequestParam(value = "confirmPassword") String confirmPassword, HttpSession session) {

		try {

			if (bindingResult.hasErrors()) {

				m.addAttribute("user", user);
				System.out.println(bindingResult);
				return "register";

			}

			if (!confirmPassword.equals(user.getPassword())) {
				m.addAttribute("user", user);
				m.addAttribute("condition",true);
				return "register";
			}
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageURL("default.png");
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			User save = userRepository.save(user);
			
			System.out.println(save);
			m.addAttribute("user", new User());
			session.setAttribute("messege", new Messege("Successfully Registered !!!", "alert-success"));
			return "register";

		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute(user);
			session.setAttribute("messege", new Messege("Something Went Wrong !!! " + e.getMessage(), "alert-danger"));
			return "register";
		}
	}
}
