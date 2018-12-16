package udemyspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import udemyspring.entities.Empresa;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	Empresa findByCnpj(String cnpj);

}
