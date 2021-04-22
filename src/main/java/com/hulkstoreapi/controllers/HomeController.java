package com.hulkstoreapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hulkstoreapi.auth.servicies.UserServiceImpl;

/*** @author Andres Gonzalez ***/

@Controller
public class HomeController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping("/home")
	public String getHome(Authentication authentication, Model model) {
		model.addAttribute("user", userServiceImpl.findByEmail(authentication.getName()).get());
		return "pages/home";
	}

}
