package ar.com.todo1.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.todo1.auth.interfaces.IUserService;
import ar.com.todo1.entities.Kardex;
import ar.com.todo1.services.KardexServiceImpl;
import lombok.RequiredArgsConstructor;

/*** @author Andres Gonzalez ***/

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KardexRestController {
	private final KardexServiceImpl kardexServiceImple;
	private final IUserService IUserService;

	@GetMapping("/all-kardex")
	public List<Kardex> getKardex(Authentication authentication, Model model) {
		model.addAttribute("user", IUserService.findByEmail(authentication.getName()).get());
		return kardexServiceImple.listKardexs();
	}

}
