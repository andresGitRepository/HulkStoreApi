package ar.com.todo1.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.com.todo1.entities.Product;

/*** @author Andres Gonzalez ***/

@Component
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Product.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"La descripcion del producto no puede estar vacio");
		ValidationUtils.rejectIfEmpty(errors, "stock", "El stock no puede estar vacio");
	}

}