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
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.todo1.auth.entities.CustomUser;
import ar.com.todo1.auth.interfaces.IUserService;
import ar.com.todo1.entities.Product;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.interfaces.IProductService;
import ar.com.todo1.models.ProductModel;
import ar.com.todo1.validators.ProductModelValidator;
import ar.com.todo1.validators.ProductValidator;
import lombok.RequiredArgsConstructor;

/*** @author Andres Gonzalez ***/

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@RequestMapping(value = "/products")
public class ProductController {
	private final IProductService IProductService;
	private final ProductValidator productValidator;
	private final ProductModelValidator productModelValidator;
	private final IUserService IUserService;

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

	@PostMapping("/new")
	public Product newProduct(@Valid Product product, Authentication authentication, Model model,
			BindingResult bindingResult) {
		productValidator.validate(product, bindingResult);
		if (bindingResult.hasErrors()) {
			List<String> dangers = bindingResult.getAllErrors().stream().map(erro -> erro.getCode())
					.collect(Collectors.toList());
			model.addAttribute("dangers", dangers);
		}

		try {
			CustomUser user = (CustomUser) authentication;
			product = IProductService.newProduct(product, user);
		} catch (StoreException exception) {
			model.addAttribute("exception", exception);
		}
		model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
		return product;
	}
	
	@PostMapping("/delete")
	public Product deleteProduct(@Valid Product product, Authentication authentication, Model model,
			BindingResult bindingResult) {
		productValidator.validate(product, bindingResult);
		if (bindingResult.hasErrors()) {
			List<String> dangers = bindingResult.getAllErrors().stream().map(erro -> erro.getCode())
					.collect(Collectors.toList());
			model.addAttribute("dangers", dangers);
		}

		try {
			CustomUser user = (CustomUser) authentication;
			product = IProductService.deleteProduct(product, user);
		} catch (StoreException exception) {
			model.addAttribute("exception", exception);
		}
		model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
		return product;
	}	

	@PostMapping("/buy")
	public Product buyProducts(@Valid ProductModel productModel, Authentication authentication, Model model,
			BindingResult bindingResult) {
		Product product=new Product();
		productModelValidator.validate(productModel, bindingResult);
		if (bindingResult.hasErrors()) {
			List<String> dangers = bindingResult.getAllErrors().stream().map(erro -> erro.getCode())
					.collect(Collectors.toList());
			model.addAttribute("dangers", dangers);
		}

		try {
			CustomUser user = (CustomUser) authentication;
			product=IProductService.buyProduct(productModel, user);
		} catch (StoreException exception) {
			model.addAttribute("exception", exception);
		}
		model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
		return product;
	}

	@PostMapping("/sale")
	public Product saleProdcuts(@Valid ProductModel productModel, Authentication authentication, Model model,
			BindingResult bindingResult) {
		Product product=new Product();
		productModelValidator.validate(productModel, bindingResult);
		if (bindingResult.hasErrors()) {
			List<String> dangers = bindingResult.getAllErrors().stream().map(erro -> erro.getCode())
					.collect(Collectors.toList());
			model.addAttribute("dangers", dangers);
		}

		try {
			CustomUser user = (CustomUser) authentication;
			product=IProductService.saleProduct(productModel, user);
		} catch (StoreException exception) {
			model.addAttribute("exception", exception);
		}
		model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
		return product;
	}

}
