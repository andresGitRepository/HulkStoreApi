package ar.com.todo1.auth.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.com.todo1.auth.entities.User;
import ar.com.todo1.auth.servicies.UserServiceImpl;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "El nombre no puede estar vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "El apellido no puede estar vacio");
		ValidationUtils.rejectIfEmpty(errors, "age", "La edad no puede estar vacia");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "El email no puede estar vacio");
		if (user.getEmail().length() < 6 || user.getEmail().length() > 100 || user.getEmail().indexOf("@") <= 0) {
			errors.rejectValue("email", "Email no aceptado");
		}

		if (userServiceImpl.findByEmail(user.getEmail()).isPresent()) {
			errors.rejectValue("email", "El usuario ya existe");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Password no puede estar vacio");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Password no aceptado");
		}
	}
}
