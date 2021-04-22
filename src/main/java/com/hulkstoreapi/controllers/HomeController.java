package com.hulkstoreapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*** @author Andres Gonzalez ***/

@Controller
public class HomeController {

	@GetMapping("/home")
	public String getHome() {
		return "pages/home";
	}

}
