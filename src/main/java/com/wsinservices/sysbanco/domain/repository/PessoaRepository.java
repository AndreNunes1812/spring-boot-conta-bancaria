package com.wsinservices.sysbanco.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wsinservices.sysbanco.domain.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	Pessoa findByDocumento(String documento);

}
