package ar.com.todo1.auth.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.todo1.auth.entities.User;
import ar.com.todo1.auth.interfaces.IUserService;
import ar.com.todo1.auth.validators.UserValidator;
import ar.com.todo1.enums.Errors;
import ar.com.todo1.exceptions.StoreException;

/*** @author Andres Gonzalez ***/

@Controller
public class LoginController {
	@Autowired
	private IUserService userService;
	@Autowired
	private UserValidator userValidator;

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

	@GetMapping(path = "/signup")
	public String singUp(Model model) {
		return "initial/signup";
	}

	@PostMapping(path = "/createUser")
	public String createUser(User user, Model model, BindingResult bindingResult) throws StoreException {
		try {
			userValidator.validate(user, bindingResult);
			if (bindingResult.hasErrors()) {
				List<String> dangers = bindingResult.getAllErrors().stream().map(erro -> erro.getCode())
						.collect(Collectors.toList());
				model.addAttribute("dangers", dangers);
				return "initial/signup";
			}
			userService.saveUser(user);
			model.addAttribute("accept", new ResponseEntity<Void>(HttpStatus.CREATED));
			return "initial/signup";
		} catch (Exception exception) {
			StoreException storeException = new StoreException(exception, Errors.USER_SAVE.getCode(),
					Errors.USER_SAVE.getDescription());
			model.addAttribute("error", storeException.getDescription());
			return "initial/signup";
		}
	}

}