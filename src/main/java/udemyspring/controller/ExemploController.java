package udemyspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * curl http://localhost:8080/api/exemplo/Murilo
 * @author murilo
 *
 */
@RestController
@RequestMapping("/api/exemplo/")
public class ExemploController {

	@GetMapping(value = "/{nome}")
	public String exemplo(@PathVariable("nome") String nome) {
		return "Olá " + nome;
	}
}
