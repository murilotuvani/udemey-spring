package udemyspring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udemyspring.dtos.EmpresaDTO;
import udemyspring.entities.Empresa;
import udemyspring.reponses.Response;
import udemyspring.repositories.EmpresaRepository;


/**
 * PostMan -> POST http://localhost:8080/api/empresas
 * Headers -> Content-Type application/json
 * Body
 * Raw -> { "razaoSocial": "ACME LTDA", "cnpj" :  "88.888.888/0001-88"}
 * 
 * curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"razaoSocial":"ACME LTDA","cnpj":"88.888.888/0001-88"}' \
    http://localhost:8080/api/empresas
 * @author murilo
 *
 */
@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {
	
	@Autowired
	EmpresaRepository empresaRepository;
	
	@GetMapping(value = "/{nome}")
	public String exemplo(@PathVariable("nome") String nome) {
		return "Olá " + nome;
	}
	
	@PostMapping
	public ResponseEntity<Response<EmpresaDTO>> cadastrar(@Valid @RequestBody EmpresaDTO empresaDto, BindingResult bindingResult) {
		Response<EmpresaDTO> response = new Response<>();
		// Se houver erros segundo o JPA Validation entao ...
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		} else {
			Empresa empresa = new Empresa();
			empresa.setCnpj(empresaDto.getCnpj());
			empresa.setRazaoSocial(empresaDto.getRazaoSocial());
			empresaRepository.save(empresa);
			empresaDto.setId(empresa.getId());
			response.setData(empresaDto);
			return ResponseEntity.ok(response);
		}
	}

}
