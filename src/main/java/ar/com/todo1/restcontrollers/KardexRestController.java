package ar.com.todo1.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.todo1.entities.Kardex;
import ar.com.todo1.exceptions.StoreException;
import ar.com.todo1.interfaces.IKardexService;
import lombok.RequiredArgsConstructor;

/*** @author Andres Gonzalez ***/

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KardexRestController {
	private final IKardexService kardexService;

	@GetMapping("/getkardexs")
	public List<Kardex> searchKardexs(Authentication authentication) throws StoreException {
		return kardexService.searchKardexs();
	}

//	@PostMapping("/searchKardex")
	public List<Kardex> searchKardex(Integer idProduct, Model model) throws StoreException {
		return kardexService.searchKardexProduct(idProduct);
	}

}
