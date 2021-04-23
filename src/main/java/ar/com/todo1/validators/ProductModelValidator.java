package ar.com.todo1.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.com.todo1.entities.Product;

/*** @author Andres Gonzalez ***/

@Component
public class ProductModelValidator implements Validator {
	@Override
	public boolean supports(Class<?> aClass) {
		return Product.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "idProduct", "Tiene que indicar producto.");
		ValidationUtils.rejectIfEmpty(errors, "count", "La cantidad no puede ser vacio.");
	}
}
