package udemyspring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import udemyspring.entities.Empresa;
import udemyspring.repositories.EmpresaRepository;
import udemyspring.service.ExemploService;
import udemyspring.utils.SenhaUtils;

@SpringBootApplication
public class SpringUdemyApplication {

	@Value("${paginacao.qtd_por_pagina}")
	private int quantidadeElementosPorPagina;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private ExemploService exemploService;

	public static void main(String[] args) {
		SpringApplication.run(SpringUdemyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			exemploService.exemplo();
			
			System.out.println("### Quantidade de elementos por página = "+ this.quantidadeElementosPorPagina);
			
			String senhaEncoded = SenhaUtils.gerarBCrypt("12345");
			System.out.println("Senha gerada : "+senhaEncoded);
			
			senhaEncoded = SenhaUtils.gerarBCrypt("12345");
			System.out.println("Senha gerada novamente : "+senhaEncoded);
			
			System.out.println("Senha válida : "+ SenhaUtils.senhaValida("12345", senhaEncoded));
			
			Empresa empresa = new Empresa();
			empresa.setRazaoSocial("Kazale IT");
			empresa.setCnpj("74645215000104");
			
			this.empresaRepository.save(empresa);
			long empresaId = empresa.getId();

			List<Empresa> empresas = empresaRepository.findAll();
			empresas.forEach(System.out::println);
			
			Empresa empresaDb = empresaRepository.findById(empresaId).get();
			System.out.println("Empresa por ID: " + empresaDb);
			
			empresaDb.setRazaoSocial("Kazale IT Web");
			this.empresaRepository.save(empresaDb);

			Empresa empresaCnpj = empresaRepository.findByCnpj("74645215000104");
			System.out.println("Empresa por CNPJ: " + empresaCnpj);
			
			this.empresaRepository.deleteById(empresaId);
			empresas = empresaRepository.findAll();
			System.out.println("Empresas: " + empresas.size());
		};
	}

}
