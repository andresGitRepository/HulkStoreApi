package ar.com.todo1.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.todo1.entities.Product;
import ar.com.todo1.interfaces.IProductService;
import lombok.RequiredArgsConstructor;

/*** @author Andres Gonzalez ***/

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/Products")
public class ProductRestController {
	
	private final IProductService IProductService;
	
	@GetMapping("/all")
	public List<Product> getProducts() {
		return IProductService.listProduct();
	}
	
}
