package ar.com.todo1.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.todo1.auth.entities.CustomUser;
import ar.com.todo1.auth.interfaces.IUserService;
import ar.com.todo1.entities.Product;
import ar.com.todo1.enums.Errors;
import ar.com.todo1.enums.KardexType;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.interfaces.IKardexService;
import ar.com.todo1.interfaces.IProductService;
import ar.com.todo1.models.ProductModel;
import ar.com.todo1.validators.ProductModelValidator;
import ar.com.todo1.validators.ProductValidator;

/*** @author Andres Gonzalez ***/

@Controller
public class ProductController {
	@Autowired
	private IProductService iProductService;
	@Autowired
	private IKardexService iKardexService;	
	@Autowired
	private ProductValidator productValidator;
	@Autowired
	private ProductModelValidator productModelValidator;
	@Autowired
	private IUserService IUserService;

	@GetMapping("/products")
	public String initialProducts() {
		return "pages/products";
	}

	@GetMapping("/buys")
	public String buyProducts() {
		return "pages/buyProducts";
	}

	@GetMapping("/sales")
	public String saleProducts() {
		return "pages/saleProducts";
	}

	@GetMapping("/searchProducts")
	public String searchProducts(Authentication authentication, Model model) {
		try {
			return "pages/searchProduct";
		} catch (Exception exception) {
			StoreException storeException = new StoreException(exception, Errors.PRODUCT_SEARCH.getCode(),
					Errors.PRODUCT_SEARCH.getDescription());
			model.addAttribute("error", storeException.getDescription());
			return "pages/searchProduct";
		}		
	}
	
	@GetMapping("/allProducts")
	public String allProducts(Authentication authentication, Model model) {
		try {
			List<Product> products = iProductService.searchProducts();
			model.addAttribute("products", products);
			model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
			return "pages/allProducts";
		} catch (Exception exception) {
			StoreException storeException = new StoreException(exception, Errors.PRODUCT_SEARCH.getCode(),
					Errors.PRODUCT_SEARCH.getDescription());
			model.addAttribute("error", storeException.getDescription());
			return "pages/allProducts";
		}
	}	

	@GetMapping("/newProducts")
	public String newProducts() {
		return "pages/newProduct";
	}

	@GetMapping("/deleteProducts")
	public String deleteProducts() {
		return "pages/deleteProduct";
	}



	@PostMapping("/new")
	public String newProduct(@Valid Product product, Authentication authentication, Model model,
			BindingResult bindingResult) {
		productValidator.validate(product, bindingResult);
		if (bindingResult.hasErrors()) {
			List<String> dangers = bindingResult.getAllErrors().stream().map(erro -> erro.getCode())
					.collect(Collectors.toList());
			model.addAttribute("dangers", dangers);
			return "pages/newProduct";
		}

		try {
			CustomUser user = (CustomUser) authentication;
			model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
			iProductService.newProduct(product, user);
			return "pages/newProduct";
		} catch (StoreException exception) {
			StoreException storeException = new StoreException(exception, Errors.PRODUCT_NEW.getCode(),
					Errors.PRODUCT_NEW.getDescription());
			model.addAttribute("error", storeException.getDescription());
			return "pages/newProduct";
		}
	}

	@PostMapping("/delete")
	public String deleteProduct(@Valid ProductModel productModel, Authentication authentication, Model model,
			BindingResult bindingResult) throws StoreException {
		Product product = iProductService.searchProduct(productModel.getIdProduct()).get();
		try {
			CustomUser user = (CustomUser) authentication;
			product = iProductService.deleteProduct(product, user);
			productModel.setCount(product.getStock());
			iKardexService.insertKardex(productModel, KardexType.BORRADO);
			return "pages/deleteProduct";
		} catch (StoreException exception) {
			model.addAttribute("exception", exception);
		}
		model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
		return "pages/deleteProduct";
	}

	@PostMapping("/buy")
	public String buyProducts(@Valid ProductModel productModel, Authentication authentication, Model model,
			BindingResult bindingResult) {
		productModelValidator.validate(productModel, bindingResult);
		if (bindingResult.hasErrors()) {
			List<String> dangers = bindingResult.getAllErrors().stream().map(erro -> erro.getCode())
					.collect(Collectors.toList());
			model.addAttribute("dangers", dangers);
			return "pages/buyProducts";
		}

		try {
			CustomUser user = (CustomUser) authentication;
			iProductService.buyProduct(productModel, user);
			model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
			return "pages/buyProducts";
		} catch (StoreException exception) {
			model.addAttribute("exception", exception);
			return "pages/buyProducts";
		}
		
	}

	@PostMapping("/sale")
	public String saleProdcuts(@Valid ProductModel productModel, Authentication authentication, Model model,
			BindingResult bindingResult) {
		productModelValidator.validate(productModel, bindingResult);
		if (bindingResult.hasErrors()) {
			List<String> dangers = bindingResult.getAllErrors().stream().map(erro -> erro.getCode())
					.collect(Collectors.toList());
			model.addAttribute("dangers", dangers);
			return "pages/saleProducts";
		}

		try {
			CustomUser user = (CustomUser) authentication;
			iProductService.saleProduct(productModel, user);
			model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
			return "pages/saleProducts";
		} catch (StoreException exception) {
			model.addAttribute("exception", exception);
			return "pages/saleProducts";
		}
	}

}
