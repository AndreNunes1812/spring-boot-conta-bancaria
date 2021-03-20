package com.wsinservices.sysbanco.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsinservices.sysbanco.domain.model.ContaCorrente;
import com.wsinservices.sysbanco.domain.model.Pessoa;
import com.wsinservices.sysbanco.domain.repository.ContaCorrenteRepository;

@RestController
@RequestMapping("/contas")
public class ContaCorrenteController {
	
	@Autowired
	public ContaCorrenteRepository contaRepository;
	
	@GetMapping(produces = "application/json")
	public List<ContaCorrente> listar() {
		return contaRepository.findAll();		
	}	

}
