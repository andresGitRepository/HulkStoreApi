package ar.com.todo1.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.todo1.entities.Kardex;
import ar.com.todo1.services.KardexServiceImpl;
import lombok.RequiredArgsConstructor;

/*** @author Andres Gonzalez ***/

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/Products")
public class KardexRestController {
	private final KardexServiceImpl kardexServiceImple;

	@GetMapping("/kardex")
	public List<Kardex> getKardex() {
		return kardexServiceImple.listKardexs();
	}
}
