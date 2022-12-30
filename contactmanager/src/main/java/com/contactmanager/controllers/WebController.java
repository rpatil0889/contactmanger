package com.contactmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contactmanager.entities.User;

@Controller
public class WebController {

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
	public String processForm(Model m, @ModelAttribute("user") User u,
			@RequestParam(value = "retypePassword") String retypePassword,
			@RequestParam(value = "userAggrement", defaultValue = "false") boolean aggrement) {

		System.out.println(u);
		System.out.println(aggrement);
		if (!aggrement) {
			m.addAttribute("agreement", "Please accept terms & conditions");
			m.addAttribute("user", u);
			return "register";
		}
		if (!retypePassword.equals(u.getPassword())) {
			m.addAttribute("user", u);
			return "register";
		}
		return "home";
	}
}
