package ar.com.todo1.restcontrollers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/getproducts")
	public List<Product> searchProducts(Authentication authentication) throws StoreException {
		return	iProductService.searchProducts();
	}
	
	@PostMapping("/search")
	public Optional<Product> searchProduct(@Valid Integer idProduct, Model model) throws StoreException {
		return	iProductService.searchProduct(idProduct);
	}

	
}
