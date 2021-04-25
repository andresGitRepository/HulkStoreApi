package ar.com.todo1.restcontrollers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.todo1.auth.interfaces.IUserService;
import ar.com.todo1.entities.Kardex;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.services.KardexServiceImpl;
import lombok.RequiredArgsConstructor;

/*** @author Andres Gonzalez ***/

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KardexRestController {
	private final KardexServiceImpl kardexServiceImple;
	private final IUserService IUserService;

	@GetMapping("/allkardexs")
	public List<Kardex> listKardex(Authentication authentication, Model model) {
		List<Kardex> kardexs = new ArrayList<Kardex>();
		try {
			kardexs = kardexServiceImple.searchKardexs();
		} catch (StoreException exception) {
			model.addAttribute("exception", exception);
		}
		model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
		return kardexs;
	}

}
