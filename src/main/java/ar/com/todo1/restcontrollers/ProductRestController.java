package ar.com.todo1.restcontrollers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.todo1.auth.interfaces.IUserService;
import ar.com.todo1.entities.Product;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.interfaces.IProductService;
import lombok.RequiredArgsConstructor;

/*** @author Andres Gonzalez ***/

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/Products")
public class ProductRestController {
	private final IProductService iProductService;
	private final IUserService IUserService;

	@GetMapping("/allproducts")
	public List<Product> searchProducts(Authentication authentication, Model model) {
		List<Product> product = new ArrayList<Product>();
		try {
			product = iProductService.searchProducts();
		} catch (StoreException exception) {
			model.addAttribute("exception", exception);
		}
		model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
		return product;
	}

}
