package ar.com.todo1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.todo1.entities.Kardex;
import ar.com.todo1.entities.Product;
import ar.com.todo1.services.KardexServiceImpl;
import ar.com.todo1.services.ProductServiceImpl;
import lombok.RequiredArgsConstructor;

/*** @author Andres Gonzalez ***/

@RestController
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
	private final ProductServiceImpl productServiceImple;
	private final KardexServiceImpl kardexServiceImple;
	
	@GetMapping("/products")
	public List<Product> getProducts() {
		return productServiceImple.listProduct();
//		return "pages/home";
	}

	@GetMapping("/products-sale")
	public String saleProdcuts() {
		return "pages/home";
	}

	@GetMapping("/products-buy")
	public String newProdcuts() {
		return "pages/home";
	}
	
	@GetMapping("/products-kardex")
	public List<Kardex> getKardex() {
		return kardexServiceImple.listKardexs();
//		return "pages/home";
	}
	

	
}
