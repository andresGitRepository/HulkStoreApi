package ar.com.todo1.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.todo1.auth.interfaces.IUserService;
import ar.com.todo1.entities.Kardex;
import ar.com.todo1.enums.Errors;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.interfaces.IKardexService;
import ar.com.todo1.models.ProductModel;

/*** @author Andres Gonzalez ***/

@Controller
public class KardexController {
	@Autowired
	private IKardexService iKardexService;
	@Autowired
	private IUserService IUserService;

	@GetMapping("/kardex")
	public String getKardex() {
		return "pages/kardex";
	}

	@GetMapping("/pageSearchKardex")
	public String searchKardex() {
		return "pages/searchKardex";
	}

	@GetMapping("/allKardexs")
	public String allProducts(Authentication authentication, Model model) {
		try {
			List<Kardex> kardexs = iKardexService.searchKardexs();
			model.addAttribute("kardexs", kardexs);
			model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
			return "pages/allKardexs";
		} catch (Exception exception) {
			StoreException storeException = new StoreException(exception, Errors.KARDEX_SEARCH.getCode(),
					Errors.KARDEX_SEARCH.getDescription());
			model.addAttribute("error", storeException.getDescription());
			return "pages/allKardexs";
		}
	}

	@PostMapping("/searchKardexs")
	public String searchProducts(@Valid ProductModel productModel, Authentication authentication, Model model) {
		try {
			List<Kardex> kardexs = iKardexService.searchKardexProduct(productModel.getIdProduct());
			model.addAttribute("kardexs", kardexs);
			model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());

			return "pages/searchKardex";
		} catch (Exception exception) {
			StoreException storeException = new StoreException(exception, Errors.PRODUCT_SEARCH.getCode(),
					Errors.PRODUCT_SEARCH.getDescription());
			model.addAttribute("error", storeException.getDescription());
			return "pages/searchKardex";
		}
	}

}
