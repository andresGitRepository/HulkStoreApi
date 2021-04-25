package ar.com.todo1.controllers;

import java.util.ArrayList;
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
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.interfaces.IKardexService;
import lombok.RequiredArgsConstructor;

/*** @author Andres Gonzalez ***/

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KardexController {
	private final IKardexService iKardexService;
	private final IUserService IUserService;
	
	@GetMapping("/kardex")
	public String getKardex() {
		return "pages/kardex";
	}
	
	@GetMapping("/pageSearchKardex")
	public String searchKardex() {
		return "pages/searchKardex";
	}	
	
	@PostMapping("/searchKardex")
	public List<Kardex> searchKardexs(@Valid Integer idProduct, Authentication authentication, Model model) {
		List<Kardex> kardex=new ArrayList<Kardex>();
		try {
			kardex = iKardexService.searchKardexProduct(idProduct);
		} catch (StoreException exception) {
			model.addAttribute("exception", exception);
		}
		model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
		return kardex;
	}
}
