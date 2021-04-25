package ar.com.todo1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*** @author Andres Gonzalez ***/

@Controller
public class KardexController {

	@GetMapping("/kardex")
	public String getKardex() {
		return "pages/kardex";
	}
}
