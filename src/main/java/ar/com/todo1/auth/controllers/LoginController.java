package com.hulkstoreapi.auth.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*** @author Andres Gonzalez ***/

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) Optional<String> error,
			@RequestParam(value = "logout", required = false) Optional<String> logout, Model model,
			RedirectAttributes flash, HttpServletRequest request) {

		if (error.isPresent()) {
			if (request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION") instanceof BadCredentialsException)
				model.addAttribute("error",
						((BadCredentialsException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION"))
								.getMessage());
			else
				model.addAttribute("error",
						"Compruebe el usuario y contraseña. Si el problema persiste, contacte con soporte del sistema.");
		}

		if (logout.isPresent()) {
			model.addAttribute("success", "Sesión cerrada.");
		}

		return "initial/login";
	}

}