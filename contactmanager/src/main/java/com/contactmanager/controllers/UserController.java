package com.contactmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	@RequestMapping(path = "/dashboard")
	public String dashboard() {
		
		return "user/dashboard";
	}
}
